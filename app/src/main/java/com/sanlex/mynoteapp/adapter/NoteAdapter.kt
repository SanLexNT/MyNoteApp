package com.sanlex.mynoteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.sanlex.mynoteapp.R
import com.sanlex.mynoteapp.model.Note


class NoteAdapter(private val context: Context) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notes: List<Note> = emptyList()
    var onClickListener: ((note: Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item_layout, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.materialCardView.strokeColor = note.colorId
        holder.textViewTitle.text = note.title
        holder.textViewTitle.setTextColor(note.colorId)
        holder.textViewDescription.text = note.description
        holder.itemView.setOnClickListener {
            onClickListener?.invoke(note)
        }
    }

    fun setNotes(noteList: List<Note>){
        notes = noteList
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var materialCardView: MaterialCardView
        var textViewTitle: TextView
        var textViewDescription: TextView

        init {
            materialCardView = itemView.findViewById(R.id.cardView)
            textViewTitle = itemView.findViewById(R.id.item_title)
            textViewDescription = itemView.findViewById(R.id.item_description)

        }
    }
}