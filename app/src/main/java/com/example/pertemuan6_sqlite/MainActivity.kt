package com.example.pertemuan6_sqlite

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.pertemuan6_sqlite.databinding.ActivityMainBinding

class   MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: Mahasiswa_Adapter
    var addRequestCode = 1
    var updateRequestCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(true)
        adapter = Mahasiswa_Adapter(this, ArrayList())
        binding.recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_add ->{
                val intent = Intent(this, InsertActivity::class.java)
                startActivityForResult(intent, addRequestCode)
                return true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == addRequestCode && resultCode == RESULT_OK) {
            val db = Mahasiswa_Helper(this)
            val mhsList = db.getData()
            adapter.updateData(mhsList)
        } else if(requestCode == updateRequestCode && resultCode == RESULT_OK) {
            val db = Mahasiswa_Helper(this)
            val mhsList = db.getData()
            adapter.updateData(mhsList)

        }
    }

    override fun onResume() {
        super.onResume()
        val db = Mahasiswa_Helper(this)
        val newMahasiswaList = db.getData()
        adapter.updateData(newMahasiswaList)
    }
}