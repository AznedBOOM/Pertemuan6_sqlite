package com.example.pertemuan6_sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Email

class Mahasiswa_Helper(context: Context) : SQLiteOpenHelper (context, DATABASE_NAME,
    null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "mhs.db"
        private val DATABASE_VERSION = 1

        private val SQL_CREATE_ENTRIES = " CREATE TABLE " +
                "${Mahasiswa_Contract.MhsEntry.TABLE_NAME} (" +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_EMAIL} TEXT PRIMARY KEY, " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_NAMA} TEXT," +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_NIM} TEXT, " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_PASS} TEXT)"

        private val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${Mahasiswa_Contract.MhsEntry.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun getData(): ArrayList<Mahasiswa> {
        val db = writableDatabase
        val mahasiswaList = ArrayList<Mahasiswa>()

        val sql = " SELECT " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_EMAIL}, " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_NAMA}, " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_NIM}, " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_PASS} " +
                "FROM ${Mahasiswa_Contract.MhsEntry.TABLE_NAME} " +
                "ORDER BY ${Mahasiswa_Contract.MhsEntry.COLUMN_NAMA} ASC"

        val cursor = db.rawQuery(sql, null,)

        with(cursor) {
            while (moveToNext()) {
                val email =
                    getString(getColumnIndexOrThrow(Mahasiswa_Contract.MhsEntry.COLUMN_EMAIL))
                val nama = getString(getColumnIndexOrThrow(Mahasiswa_Contract.MhsEntry.COLUMN_NAMA))
                val nim = getString(getColumnIndexOrThrow(Mahasiswa_Contract.MhsEntry.COLUMN_NIM))
                val pass = getString(getColumnIndexOrThrow(Mahasiswa_Contract.MhsEntry.COLUMN_PASS))

                val mahasiswa = Mahasiswa(email, nama, nim, pass)
                mahasiswaList.add(mahasiswa)
            }
        }
        cursor.close()
        return mahasiswaList
    }

    fun insertData(mahasiswa: Mahasiswa) {
        val db = writableDatabase

        val sql = "INSERT INTO ${Mahasiswa_Contract.MhsEntry.TABLE_NAME} " +
                "(${Mahasiswa_Contract.MhsEntry.COLUMN_EMAIL}, " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_NAMA}, " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_NIM}, " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_PASS} )" +
                "VALUES ('${mahasiswa.email}', '${mahasiswa.nama}', '${mahasiswa.nim}', '${mahasiswa.password}')"

        db.execSQL(sql)
        db.close()
    }

    fun delData(email: String) {
        val db = writableDatabase
        val table = Mahasiswa_Contract.MhsEntry.TABLE_NAME
        val emailColumn = Mahasiswa_Contract.MhsEntry.COLUMN_EMAIL
        val sql = " DELETE FROM " + table + " WHERE " + emailColumn + " = '" + email + "' "
        db.execSQL(sql)
        db.close()
    }

    fun updateData(mahasiswa: Mahasiswa){
        val db = writableDatabase

        val sql = " UPDATE ${Mahasiswa_Contract.MhsEntry.TABLE_NAME} SET " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_NAMA} = '${mahasiswa.nama}', " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_NIM} = '${mahasiswa.nim}', " +
                "${Mahasiswa_Contract.MhsEntry.COLUMN_PASS} = '${mahasiswa.password}' " +
                " WHERE ${Mahasiswa_Contract.MhsEntry.COLUMN_EMAIL} = '${mahasiswa.email}' "
        db.execSQL(sql)
        db.close()

    }
}
