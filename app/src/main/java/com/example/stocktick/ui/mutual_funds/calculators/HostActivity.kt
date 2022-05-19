package com.example.stocktick.ui.mutual_funds.calculators

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.ActivityHost2Binding

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHost2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHost2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.logout_button) {
            // do something here
        }
        return super.onOptionsItemSelected(item)

    }
}