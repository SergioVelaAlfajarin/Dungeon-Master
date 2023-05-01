package sva.dungmas.bbdd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDHelper(
    context: Context?, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int
): SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        //db.execSQL(createTableSql)
    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {
        //db.execSQL(dropTableSql)
        //onCreate(db)
    }
}