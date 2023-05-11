package com.example.pertemuan6_sqlite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan6_sqlite.databinding.ListItemBinding

class Mahasiswa_Adapter (private val context : Context,
                         private var listMahasiswa : ArrayList <Mahasiswa>) :
    RecyclerView.Adapter <Mahasiswa_Adapter.ViewHolder>(){
        class ViewHolder (val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.namaText.text = listMahasiswa[position].nama
        holder.binding.emailText.text = listMahasiswa[position].email
        holder.binding.cardView.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("nama", listMahasiswa[position].nama)
            intent.putExtra("nim", listMahasiswa[position].nim)
            intent.putExtra("email", listMahasiswa[position].email)
            intent.putExtra("password", listMahasiswa[position].password)
            context.startActivity(intent)
        }
    }

    fun updateData(newListMahasiswa: ArrayList<Mahasiswa>) {
        listMahasiswa = newListMahasiswa
        notifyDataSetChanged()
    }
}