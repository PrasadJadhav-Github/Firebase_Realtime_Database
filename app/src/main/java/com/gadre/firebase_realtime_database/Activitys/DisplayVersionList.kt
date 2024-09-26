package com.gadre.firebase_realtime_database.Activitys

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.gadre.firebase_realtime_database.databinding.ActivityDisplayVersionListBinding

class DisplayVersionList : AppCompatActivity() {
    private  lateinit var binding: ActivityDisplayVersionListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDisplayVersionListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Version List"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}