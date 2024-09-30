package com.gadre.firebase_realtime_database.Repositories

import android.util.Log
import com.gadre.firebase_realtime_database.Models.TVersionNotes
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import kotlin.math.log

class TVersionNotesRepository {

    private val database = FirebaseDatabase.getInstance().getReference("TVersionNotes")

    // Function to fetch version notes
    suspend fun fetchVersionNotes(): List<TVersionNotes>? {
        return try {
            val snapshot = database.get().await()
            Log.d("TVersionNotesRepository", "data: $snapshot")
            val tVersionNotesList: MutableList<TVersionNotes> = mutableListOf()
            val children = snapshot.children

            children.forEach {
                    val versionName = it.key as String
                    Log.d("TVersionNotesRepository", "version: $versionName")
                val lisOfNotes = arrayListOf<String>()
                it.children.forEach { notesSnapshot ->
                    Log.d("TVersionNotesRepository", "notes: ${notesSnapshot.value}")
                    lisOfNotes.add(notesSnapshot.value as String);
                }
                tVersionNotesList.add(TVersionNotes(versionName,lisOfNotes))
            }

            tVersionNotesList // Return  list

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
