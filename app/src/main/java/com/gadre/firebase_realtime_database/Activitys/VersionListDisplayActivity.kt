package com.gadre.firebase_realtime_database.Activitys

import android.R
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gadre.firebase_realtime_database.Adapters.VersionNotesAdapter
import com.gadre.firebase_realtime_database.Repositories.TVersionNotesRepository
import com.gadre.firebase_realtime_database.ViewModel.TVersionNotesViewModel
import com.gadre.firebase_realtime_database.databinding.ActivityDisplayVersionListBinding


class VersionListDisplayActivity : AppCompatActivity() {
    private lateinit var activityDisplayVersionListbinding: ActivityDisplayVersionListBinding
    private lateinit var versionNotesAdapter: VersionNotesAdapter
    private lateinit var tVersionNotesViewModel: TVersionNotesViewModel
    private lateinit var tVersionNotesRepository:TVersionNotesRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDisplayVersionListbinding =
            ActivityDisplayVersionListBinding.inflate(layoutInflater)
        setContentView(activityDisplayVersionListbinding.root)



        tVersionNotesRepository = TVersionNotesRepository()
        tVersionNotesViewModel=TVersionNotesViewModel(tVersionNotesRepository )
        tVersionNotesViewModel.fetchVersionDetails()

        versionNotesAdapter = VersionNotesAdapter(arrayListOf())
        activityDisplayVersionListbinding.versionListRecyclerView.adapter = versionNotesAdapter
        activityDisplayVersionListbinding.versionListRecyclerView.layoutManager =
            LinearLayoutManager(this)

        val toolbar = activityDisplayVersionListbinding.toolbar
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Version List"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        //function calls
        initObserver()
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
            if (versionNotes != null) {
                versionNotesAdapter.updateVersionNotes(versionNotes)
            } else {
                Toast.makeText(this, "Unable to fetch version details!", Toast.LENGTH_LONG).show()
            }
        }
    }
}