package sva.dungmas.bbdd

import android.content.Context
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.ItemPart
import sva.dungmas.game.items.Storable
import sva.dungmas.recyclers.RankingEntry

class BDManager(context: Context) {

    private val helper: BDHelper
    private val itemPart: ArrayList<ItemPart> = arrayListOf()
    private val item: ArrayList<Item> = arrayListOf()

    init{
        helper = BDHelper(context)
        fillItemPart()
        fillItem()
    }



    private fun fillItemPart(){
        val cursor = helper.readableDatabase.rawQuery(
            "SELECT * FROM ${BDContract.Itempart.tableName}", null
        )
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getInt(1)
                val icon = cursor.getInt(2)
                val ip = ItemPart(id, name, icon)
                itemPart.add(ip)
            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    private fun fillItem(){
        val cursor = helper.readableDatabase.rawQuery(
            "SELECT * FROM ${BDContract.Item.tableName}", null
        )
        if (cursor.moveToFirst()) {
            do {
                val itemId = cursor.getInt(0)
                val name = cursor.getInt(1)
                val icon = cursor.getInt(2)
                val recipe = getRecipe(itemId)
                val it = Item(itemId,name,icon,recipe)
                item.add(it)
            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    private fun getRecipe(itemId: Int): HashMap<Storable, Int> {
        val hash = hashMapOf<Storable, Int>()
        val cursor = helper.readableDatabase.rawQuery(
            "SELECT ${BDContract.Recipes.itempart_id}, ${BDContract.Recipes.quantity} FROM ${BDContract.Recipes.tableName} WHERE ${BDContract.Recipes.item_id} = $itemId", null
        )
        if (cursor.moveToFirst()) {
            do {
                val itempartId = cursor.getInt(0)
                val quantity = cursor.getInt(1)
                hash[getItemsPart(itempartId)] = quantity
            } while (cursor.moveToNext())
        }
        cursor.close()
        return hash
    }

    fun getCraftableItems(): List<Item> {
        return item.toList()
    }

    fun getCraftableItems(id: Int): Item{
        return item.stream().filter{
            it.id == id
        }.findAny().get()
    }

    fun getItemsPart(): List<ItemPart>{
        return itemPart.toList()
    }

    fun getItemsPart(id: Int): ItemPart{
        return itemPart.stream().filter{
            it.id == id
        }.findAny().get()
    }


    fun getRankingPos(context: Context, position: Int): RankingEntry {
        TODO("Not yet implemented")
    }

    fun resetRanking(context: Context) {
        TODO("Not yet implemented")
    }
}