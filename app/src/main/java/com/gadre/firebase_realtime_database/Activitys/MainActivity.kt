package com.gadre.firebase_realtime_database.Activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gadre.firebase_realtime_database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imageViewVersionList.setOnClickListener { view ->
            val intent: Intent = Intent(this@MainActivity, VersionListDisplayActivity::class.java)
            startActivity(intent)
            Toast.makeText(this@MainActivity, "Opening  Version List ", Toast.LENGTH_SHORT).show()
        }


    }
}