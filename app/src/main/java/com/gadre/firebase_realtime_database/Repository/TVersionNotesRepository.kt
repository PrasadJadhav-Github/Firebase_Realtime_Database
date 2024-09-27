package com.gadre.firebase_realtime_database.Repositories

import com.gadre.firebase_realtime_database.Models.TVersionNotes
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class TVersionNotesRepository {

    private val database = FirebaseDatabase.getInstance().getReference("versionNotes")

    // Function to fetch version notes
    suspend fun fetchVersionNotes(): List<TVersionNotes>? {
        return try {
            val snapshot = database.get().await()
            val tVersionNotesList : MutableList<TVersionNotes> = mutableListOf()
            val children = snapshot.children

            children.forEach {
                val tVersionInstance = it.getValue(TVersionNotes::class.java)
                if (tVersionInstance != null) {
                    tVersionNotesList.add(tVersionInstance)
                }
            }

            tVersionNotesList // Return  list
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
