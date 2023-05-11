package com.example.pertemuan6_sqlite

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.pertemuan6_sqlite.databinding.ActivityMainBinding
import com.example.pertemuan6_sqlite.databinding.ActivityUpdateBinding
import com.example.pertemuan6_sqlite.databinding.ListItemBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nama = intent.getStringExtra("nama")!!
        val nim = intent.getStringExtra("nim")!!
        val email = intent.getStringExtra("email")!!
        val password = intent.getStringExtra("password")!!

        val mahasiswa = Mahasiswa(email, nama, nim, password)

        binding.inputEmail.setText(mahasiswa.email)
        binding.inputNIM.setText(mahasiswa.nim)
        binding.inputNama.setText(mahasiswa.nama)
        binding.inputPass.setText(mahasiswa.password)

        binding.btnDelete.setOnClickListener() {
            val db = Mahasiswa_Helper(this)
            db.delData(mahasiswa.email)
            setResult(Activity.RESULT_OK)
            finish()
        }

        binding.btnUpdate.setOnClickListener() {
            val updateMahasiswa = Mahasiswa(
                binding.inputEmail.text.toString(),
                binding.inputNama.text.toString(),
                binding.inputNIM.text.toString(),
                binding.inputPass.text.toString()
            )

            val db = Mahasiswa_Helper(this)
            db.updateData(updateMahasiswa)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}