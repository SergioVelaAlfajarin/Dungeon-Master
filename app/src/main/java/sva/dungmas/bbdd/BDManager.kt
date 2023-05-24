package sva.dungmas.bbdd

import android.content.Context
import android.database.Cursor
import android.util.Log
import sva.dungmas.R
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.ItemPart
import sva.dungmas.recyclers.RankingEntry
import java.lang.Exception

private const val VERSION = 1

class BDManager(context: Context) {
    private val helper = BDHelper(context)

    fun getCraftableItems(): List<Item> {
        val items = arrayListOf<Item>()
        val cursor = helper.readableDatabase.rawQuery(
            "SELECT * FROM items", null
        )
        if (cursor.moveToFirst()) {
            do {
                //get info and build item
            } while (cursor.moveToNext())
        }
        cursor.close()
        return items.toList()
    }


    fun getCraftableItems(id: Int): Item{
        val cursor = helper.readableDatabase.rawQuery(
            "SELECT * FROM items WHERE item_id = $id", null
        )
        if (cursor.count == 1){
            if (cursor.moveToFirst()) {
                cursor.close()
                return Item(1,1,1, hashMapOf())
            }
        }
        cursor.close()
        throw Exception("Fatal error")
    }

    fun getItemsPart(): List<ItemPart>{
        val items = arrayListOf<ItemPart>()
        val cursor = helper.readableDatabase.rawQuery(
            "SELECT * FROM itemparts", null
        )
        if (cursor.moveToFirst()) {
            do {
                //get info and build item
            } while (cursor.moveToNext())
        }
        cursor.close()
        return items.toList()
    }

    fun getItemsPart(id: Int): ItemPart{
        val cursor = helper.readableDatabase.rawQuery(
            "SELECT * FROM itemparts WHERE itempart_id = $id", null
        )
        if (cursor.count == 1){
            if (cursor.moveToFirst()) {
                cursor.close()
                return ItemPart(1,1,1)
            }
        }
        cursor.close()
        throw Exception("Fatal error")
    }

    fun getRankingPos(context: Context, position: Int): RankingEntry {
        TODO("Not yet implemented")
    }

    fun resetRanking(context: Context) {
        TODO("Not yet implemented")
    }
}