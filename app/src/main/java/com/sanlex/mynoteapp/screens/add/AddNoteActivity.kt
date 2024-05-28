package com.sanlex.mynoteapp.screens.add

import android.content.Intent
import android.graphics.drawable.ColorDrawable
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

class AddNoteActivity : AppCompatActivity() {
    private lateinit var addNoteViewModel: AddNoteViewModel
    private lateinit var editTextTitle: TextInputEditText
    private lateinit var textInputTitle: TextInputLayout
    private lateinit var editTextDescription: TextInputEditText

    private lateinit var radioButtonRed: RadioButton
    private lateinit var radioButtonBlue: RadioButton
    private lateinit var radioButtonGreen: RadioButton
    private lateinit var radioButtonPurple: RadioButton
    private lateinit var radioButtonOrange: RadioButton

    private var noteColorId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.main)))
        supportActionBar?.title = getString(R.string.add_new_note)
        addNoteViewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]

        noteColorId = resources.getColor(R.color.red,null)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextDescription = findViewById(R.id.editTextDescription)
        textInputTitle = findViewById(R.id.textInputTitle)

        radioButtonRed = findViewById(R.id.radioButtonRed)
        radioButtonBlue = findViewById(R.id.radioButtonBlue)
        radioButtonGreen = findViewById(R.id.radioButtonGreen)
        radioButtonPurple = findViewById(R.id.radioButtonPurple)
        radioButtonOrange = findViewById(R.id.radioButtonOrange)

        setupEditTextTitle()
        setupRadioButtonClicks()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.confirm_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.confirm_item){
            addNote()
        }
        return true
    }

    private fun addNote(){
        val title = editTextTitle.text.toString()
        val description  = editTextDescription.text.toString()
        if(title.isEmpty() || title.isBlank()){
            Toast.makeText(applicationContext, getString(R.string.all_fields_filled_warning), Toast.LENGTH_SHORT).show()
        } else{
            val note = Note(title = title, description = description, colorId = noteColorId)
            addNoteViewModel.addNote(note)
            val intent = Intent(this@AddNoteActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun setupEditTextTitle(){
        editTextTitle.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count <= 0){
                    textInputTitle.error = getString(R.string.fill_field_error)
                } else{
                    textInputTitle.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun setupRadioButtonClicks() {
        radioButtonRed.setOnClickListener {
            noteColorId = resources.getColor(R.color.red,null)
        }
        radioButtonBlue.setOnClickListener {
            noteColorId = resources.getColor(R.color.blue,null)
        }
        radioButtonGreen.setOnClickListener {
            noteColorId = resources.getColor(R.color.green,null)
        }
        radioButtonPurple.setOnClickListener {
            noteColorId = resources.getColor(R.color.purple,null)
        }
        radioButtonOrange.setOnClickListener {
            noteColorId = resources.getColor(R.color.orange,null)
        }
    }
}