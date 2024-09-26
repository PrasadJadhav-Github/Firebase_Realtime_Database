package com.gadre.firebase_realtime_database.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gadre.firebase_realtime_database.Models.TVersionNotes
import com.gadre.firebase_realtime_database.Repositories.TVersionNotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TVersionNotesViewModel(
    private val  tVersionNotesRepository: TVersionNotesRepository
) :ViewModel(){

    private val versionNotes = MutableLiveData<List<TVersionNotes>?>()

    fun fetchVersionDetails(){
        CoroutineScope(Dispatchers.IO).launch {
            val notes = tVersionNotesRepository.fetchVersionNotes()

            withContext(Dispatchers.Main){
                versionNotes.value=notes
            }
        }
    }
}