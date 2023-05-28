package sva.dungmas.bbdd

import android.content.Context
import android.provider.BaseColumns
import sva.dungmas.game.entities.Player
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


    fun getRankings(): ArrayList<RankingEntry> {
        val rankings  = arrayListOf<RankingEntry>()
        val query = """
            SELECT ${BDContract.Player.tableName}.${BDContract.Player.player_name}, 
                   ${BDContract.Ranking.tableName}.${BDContract.Ranking.pts} 
            FROM ${BDContract.Ranking.tableName} 
            INNER JOIN  ${BDContract.Player.tableName} 
            ON ${BDContract.Player.tableName}.${BaseColumns._ID} =  ${BDContract.Ranking.tableName}.${BDContract.Ranking.player_id}
            ORDER BY ${BDContract.Ranking.tableName}.${BDContract.Ranking.pts} DESC, ${BDContract.Player.tableName}.${BDContract.Player.player_name}
            LIMIT 10
            """
        val cursor = helper.readableDatabase.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(0)
                val pts = cursor.getInt(1)
                rankings.add(RankingEntry(name, pts))
            } while (cursor.moveToNext())
        }
        cursor.close()
        val addRanks = 10 - rankings.size
        if(addRanks != 0){
            repeat(addRanks){
                rankings.add(RankingEntry("-", 0))
            }
        }
        return rankings
    }

    fun resetRanking() {
        val query = "DELETE FROM ${BDContract.Ranking.tableName};"
        helper.writableDatabase.execSQL(query)
    }

    fun saveRank(player: Player, points: Int) {
        val query = "INSERT INTO ${BDContract.Ranking.tableName}(${BaseColumns._ID},${BDContract.Ranking.player_id},${BDContract.Ranking.pts}) " +
                "VALUES ((SELECT COUNT(*) FROM ${BDContract.Ranking.tableName}),${player.id},$points)"
        helper.writableDatabase.execSQL(query)
    }

    fun updatePlayerStats(player: Player) {
        val query = "UPDATE ${BDContract.Player.tableName} SET ${BDContract.Player.vit} = ${player.vit},${BDContract.Player.def} = ${player.def}, ${BDContract.Player.atk} = ${player.atk} " +
                "WHERE ${BaseColumns._ID} = ${player.id}"
        helper.writableDatabase.execSQL(query)
    }

    fun savePlayer(player: Player) {
        val query = "INSERT INTO ${BDContract.Player.tableName}(${BaseColumns._ID},${BDContract.Player.player_name},${BDContract.Player.vit},${BDContract.Player.def},${BDContract.Player.atk}) " +
                "VALUES (${player.id},'${player.name}',${player.vit},${player.def},${player.atk})"
        helper.writableDatabase.execSQL(query)
    }

    fun addItemPartsObtained(player: Player, itemDropped: ItemPart) {
        //añadira items a la tabla ItemsPart Obtenidos.
    }

    fun getNextPlayerId(): Int {
        val query = "SELECT COUNT(*) FROM ${BDContract.Player.tableName};"
        val cursor = helper.readableDatabase.rawQuery(query, null)
        cursor.moveToFirst()
        val id = cursor.getInt(0) + 1
        cursor.close()
        return id
    }
}