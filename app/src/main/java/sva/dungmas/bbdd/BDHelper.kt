package sva.dungmas.bbdd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class BDHelper(
    val context: Context
): SQLiteOpenHelper(context, name, null, version) {
    companion object{
        const val name = "DungMas.db"
        const val version = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        BDContract.creates.forEach{
            db.execSQL(it)
        }
        BDContract.inserts.forEach{
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {
        BDContract.drops.forEach {
            db.execSQL(it)
        }
        onCreate(db)
    }

    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
        db.execSQL("PRAGMA foreign_keys= ON")
    }
}