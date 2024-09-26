package com.gadre.firebase_realtime_database.Repositories

import com.gadre.firebase_realtime_database.Models.TVersionNotes
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class TVersionNotesRepository {

    private val database = FirebaseDatabase.getInstance().getReference("versionNotes")

    // Function to fetch version notes
    suspend fun fetchVersionNotes(): List<TVersionNotes>? {
      return  try {
            val snapshot = database.get().await()
            snapshot.children.mapNotNull { it.getValue(TVersionNotes::class.java) }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}