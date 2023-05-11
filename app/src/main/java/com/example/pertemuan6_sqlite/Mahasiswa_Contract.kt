package com.example.pertemuan6_sqlite

import android.provider.BaseColumns

object Mahasiswa_Contract {
    class MhsEntry : BaseColumns {
        companion object{
            const val TABLE_NAME = "mahasiswa"
            const val COLUMN_EMAIL = "email"
            const val COLUMN_NAMA = "nama"
            const val COLUMN_NIM = "nim"
            const val COLUMN_PASS = "password"
        }

    }
}