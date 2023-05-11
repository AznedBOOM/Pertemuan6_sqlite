package com.example.pertemuan6_sqlite

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View.OnContextClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import com.example.pertemuan6_sqlite.databinding.ActivityInsertBinding
import com.example.pertemuan6_sqlite.databinding.ActivityMainBinding

class InsertActivity : AppCompatActivity() {
    lateinit var binding: ActivityInsertBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.setOnClickListener{
            val email = binding.inputEmail.text.toString()
            val nama = binding.inputNama.text.toString()
            val nim = binding.inputNIM.text.toString()
            val pass = binding.inputPass.text.toString()
            val db = Mahasiswa_Helper(this)
            val mahasiswa = Mahasiswa(email, nama, nim, pass)

            if(nama.isNotEmpty() && nim.isNotEmpty() && pass.isNotEmpty()){
                db.insertData(mahasiswa)
                Toast.makeText(this, "Data successfully created", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK)
                finish()
            } else{
                Toast.makeText(this, "Data can't be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}