package com.example.stocktick.ui.mutual_funds.stressed_about_finance

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.ActivityHostBinding
import com.example.stocktick.ui.customviews.CustomTracker
import com.example.stocktick.ui.get_assistant.GetAssistantDialog


class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostBinding
    lateinit var customTracker: CustomTracker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        customTracker = binding.tracker
        customTracker.setDivisions(11)
        customTracker.setPosition(0)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        binding.getAssistantFab.setOnClickListener{
            GetAssistantDialog().show(
                supportFragmentManager,
                GetAssistantDialog.TAG
            )
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                confirmationDialog { onYes ->
                    if (onYes){
                        finish()
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun confirmationDialog(
        onResponse: (onYes: Boolean)->Unit
    ) {
        val dialogBuilder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialog))
        dialogBuilder.setTitle("Do you want to quit?")
        dialogBuilder.setMessage("Click yes if you want to quit financial planning calculation")
        dialogBuilder.setPositiveButton("Yes"){dialog, which->
            dialog.dismiss()
            onResponse(true)
        }
        dialogBuilder.setNegativeButton("No"){dialog, which->
            dialog.dismiss()
            onResponse(false)
        }

        dialogBuilder.show()
    }
}