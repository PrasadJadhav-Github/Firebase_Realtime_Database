package com.gadre.firebase_realtime_database.Activitys

import android.R
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gadre.firebase_realtime_database.Adapters.VersionNotesAdapter
import com.gadre.firebase_realtime_database.Repositories.TVersionNotesRepository
import com.gadre.firebase_realtime_database.ViewModel.TVersionNotesViewModel
import com.gadre.firebase_realtime_database.databinding.ActivityDisplayVersionListBinding


class VersionListDisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayVersionListBinding
    private lateinit var versionNotesAdapter: VersionNotesAdapter
    private lateinit var tVersionNotesViewModel: TVersionNotesViewModel
    private lateinit var tVersionNotesRepository:TVersionNotesRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityDisplayVersionListBinding.inflate(layoutInflater)
        setContentView(binding.root)



        tVersionNotesRepository = TVersionNotesRepository()
        tVersionNotesViewModel=TVersionNotesViewModel(tVersionNotesRepository )
        tVersionNotesViewModel.fetchVersionDetails()

        versionNotesAdapter = VersionNotesAdapter(emptyList())
        binding.versionListRecyclerView.adapter = versionNotesAdapter
        binding.versionListRecyclerView.layoutManager = LinearLayoutManager(this)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Version List"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        //function calls
        initObserver()
        addButtonLstener()
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

    private fun initObserver() {
        tVersionNotesViewModel.versionNotesLiveData.observe(this) { versionNotes ->
            Log.d("VersionListDisplayActivity", "Received version notes: $versionNotes")
            if (versionNotes != null) {
                versionNotesAdapter.updateVersionNotes(versionNotes)
            } else {
                Toast.makeText(this, "Unable to fetch version details!", Toast.LENGTH_LONG).show()
            }
        }
    }


    private  fun  addButtonLstener(){
        binding.addNotesButton.setOnClickListener {

        }
    }
}