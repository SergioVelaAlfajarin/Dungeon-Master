package sva.dungmas.bbdd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/*
private const val dropTableSql = "DROP TABLE IF EXISTS ${BDContract.NOMBRE_TABLA}"
private const val createTableSql = """
    CREATE TABLE libros(
        ${BDContract.Columns._ID} integer primary key autoincrement,
        ${BDContract.Columns.CATEGORIA} text not null,
        ${BDContract.Columns.TITULO} text not null,
        ${BDContract.Columns.AUTOR} text not null,
        ${BDContract.Columns.IDIOMA} text,
        ${BDContract.Columns.FECHA_LECTURA_INI} date,
        ${BDContract.Columns.FECHA_LECTURA_FIN} date,
        ${BDContract.Columns.PRESTADO_A} text,
        ${BDContract.Columns.VALORACION} float,
        ${BDContract.Columns.FORMATO} text,
        ${BDContract.Columns.NOTAS} text
    );
"""
 */

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

    private fun sqlScript(): String{
        return """
            
            
            
        """.trimIndent()
    }
}