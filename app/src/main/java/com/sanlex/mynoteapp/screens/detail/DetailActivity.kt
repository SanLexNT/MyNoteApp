package com.sanlex.mynoteapp.screens.detail

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sanlex.mynoteapp.R
import com.sanlex.mynoteapp.model.Note
import com.sanlex.mynoteapp.screens.list.MainActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var editTextTitle: TextInputEditText
    private lateinit var textInputTitle: TextInputLayout
    private lateinit var editTextDescription: TextInputEditText

    private lateinit var radioButtonRed: RadioButton
    private lateinit var radioButtonBlue: RadioButton
    private lateinit var radioButtonGreen: RadioButton
    private lateinit var radioButtonPurple: RadioButton
    private lateinit var radioButtonOrange: RadioButton

    private lateinit var note: Note
    private var noteColorId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.main)))
        supportActionBar?.title = getString(R.string.update_note)

        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextDescription = findViewById(R.id.editTextDescription)
        textInputTitle = findViewById(R.id.textInputTitle)

        radioButtonRed = findViewById(R.id.radioButtonRed)
        radioButtonBlue = findViewById(R.id.radioButtonBlue)
        radioButtonGreen = findViewById(R.id.radioButtonGreen)
        radioButtonPurple = findViewById(R.id.radioButtonPurple)
        radioButtonOrange = findViewById(R.id.radioButtonOrange)

        setupUI()
        setupEditTextTitle()
        setupRadioButtonClicks()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.confirm_item -> updateNote()
            R.id.delete_item -> detailViewModel.deleteNote(note)
        }

        val intent = Intent(this@DetailActivity, MainActivity::class.java)
        startActivity(intent)
        finish()

        return true
    }

    private fun setupUI() {
        val bundle = intent.getBundleExtra("bundle")
        note = bundle?.getSerializable("note", Note::class.java)!!
        noteColorId = note.colorId
        editTextTitle.setText(note.title)
        editTextDescription.setText(note.description)

        when (noteColorId) {
            resources.getColor(R.color.red, null) -> radioButtonRed.isChecked = true
            resources.getColor(R.color.blue, null) -> radioButtonBlue.isChecked = true
            resources.getColor(R.color.green, null) -> radioButtonGreen.isChecked = true
            resources.getColor(R.color.purple, null) -> radioButtonPurple.isChecked = true
            resources.getColor(R.color.orange, null) -> radioButtonOrange.isChecked = true
        }

    }

    private fun setupEditTextTitle() {
        editTextTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count <= 0) {
                    textInputTitle.error = getString(R.string.fill_field_error)
                } else {
                    textInputTitle.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun setupRadioButtonClicks() {
        radioButtonRed.setOnClickListener {
            noteColorId = resources.getColor(R.color.red, null)
        }
        radioButtonBlue.setOnClickListener {
            noteColorId = resources.getColor(R.color.blue, null)
        }
        radioButtonGreen.setOnClickListener {
            noteColorId = resources.getColor(R.color.green, null)
        }
        radioButtonPurple.setOnClickListener {
            noteColorId = resources.getColor(R.color.purple, null)
        }
        radioButtonOrange.setOnClickListener {
            noteColorId = resources.getColor(R.color.orange, null)
        }
    }

    private fun updateNote() {
        val title = editTextTitle.text.toString()
        if (title.isEmpty() || title.isBlank()) {
            Toast.makeText(
                applicationContext,
                getString(R.string.all_fields_filled_warning),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            note.title = title
            note.description = editTextDescription.text.toString()
            note.colorId = noteColorId
            detailViewModel.updateNote(note)
        }
    }

}