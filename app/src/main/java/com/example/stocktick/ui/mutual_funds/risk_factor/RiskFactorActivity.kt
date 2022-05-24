package com.example.stocktick.ui.mutual_funds.risk_factor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.R
import com.example.stocktick.databinding.ActivityMutualFundsBinding
import com.example.stocktick.ui.customviews.CustomTracker
import com.example.stocktick.ui.get_assistant.GetAssistantDialog
import com.example.stocktick.ui.mutual_funds.risk_factor.fragments.questions_fragment.Question

class RiskFactorActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMutualFundsBinding
    lateinit var customTracker: CustomTracker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMutualFundsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customTracker = binding.tracker
        customTracker.setDivisions(2)
        setSupportActionBar(binding.topAppBar)

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
        dialogBuilder.setMessage("Click yes if you want to quit Risk Factor")
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.logout, menu)
        return super.onCreateOptionsMenu(menu)
    }


}