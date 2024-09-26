package com.gadre.firebase_realtime_database.Models


data class TVersionNotes(
    val versionNmae: String,
    val note: List<Note>?
)

data class Note(
    val status: String
)


