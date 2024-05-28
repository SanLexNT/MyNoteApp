package com.sanlex.mynoteapp.screens.list

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sanlex.mynoteapp.R
import com.sanlex.mynoteapp.adapter.NoteAdapter
import com.sanlex.mynoteapp.model.Note
import com.sanlex.mynoteapp.screens.add.AddNoteActivity
import com.sanlex.mynoteapp.screens.detail.DetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var rvNotes: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.main)))

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        adapter = NoteAdapter(this)

        rvNotes = findViewById(R.id.rvNotes)
        fabAdd = findViewById(R.id.fabAdd)

        viewModel.getAllNotes().observe(this) {
            it.asReversed()
            adapter.setNotes(it)
        }
        rvNotes.adapter = adapter

        fabAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivity(intent)
        }

        adapter.onClickListener = {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("note", it)
            intent.putExtra("bundle", bundle)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.clear_item){
            viewModel.clearAllNotes()
            Toast.makeText(this@MainActivity, getString(R.string.clear_notes_message), Toast.LENGTH_SHORT).show()
        }
        return true
    }

}