package com.gadre.firebase_realtime_database.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gadre.firebase_realtime_database.Models.TVersionNotes
import com.gadre.firebase_realtime_database.R
import com.gadre.firebase_realtime_database.databinding.VersionNotesItemviewBinding

class VersionNotesAdapter(private var versionNotesList: List<TVersionNotes>) :
    RecyclerView.Adapter<VersionNotesAdapter.VersionViewHolder>() {

    class VersionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val  binding =VersionNotesItemviewBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.version_notes_itemview, parent, false)
        return VersionViewHolder(view)
    }

    override fun onBindViewHolder(holder: VersionViewHolder, position: Int) {
        val versionNote = versionNotesList[position]
        holder.binding.textViewVersionNumber.text=versionNote.versionName
        holder.binding.textViewNoteDescription.text= versionNote.note?.joinToString("\n")


    }

    fun updateVersionNotes(newVersionNotes: List<TVersionNotes>) {
        versionNotesList = newVersionNotes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return versionNotesList.size
    }


}
