package sva.dungmas.bbdd

import android.provider.BaseColumns
import sva.dungmas.R

object BDContract {
    val creates = arrayListOf(
        Item.create,
        Itempart.create,
        Recipes.create,
        Player.create,
        Ranking.create,
        ItempartsObtenidos.create
    )
    val drops = arrayListOf(
        Item.drop,
        Itempart.drop,
        Recipes.drop,
        Player.drop,
        Ranking.drop,
        ItempartsObtenidos.drop
    )
    val inserts = arrayListOf(
        "INSERT INTO ${Itempart.tableName}(${BaseColumns._ID}, ${Itempart.itempart_nameRes}, ${Itempart.itempart_iconRes}) VALUES (1,${ R.string.itemName_iron},${ R.drawable.iron1});",
        "INSERT INTO ${Itempart.tableName}(${BaseColumns._ID}, ${Itempart.itempart_nameRes}, ${Itempart.itempart_iconRes}) VALUES (2,${ R.string.itemName_leaves},${ R.drawable.leaves1});",
        "INSERT INTO ${Itempart.tableName}(${BaseColumns._ID}, ${Itempart.itempart_nameRes}, ${Itempart.itempart_iconRes}) VALUES (3,${ R.string.itemName_rock},${ R.drawable.rock1});",
        "INSERT INTO ${Itempart.tableName}(${BaseColumns._ID}, ${Itempart.itempart_nameRes}, ${Itempart.itempart_iconRes}) VALUES (4,${ R.string.itemName_wood},${ R.drawable.wood1});",
        "INSERT INTO ${Item.tableName}(${BaseColumns._ID}, ${Item.item_nameRes}, ${Item.item_iconRes}) VALUES (1,${ R.string.itemName_iron2},${ R.drawable.iron2});",
        "INSERT INTO ${Item.tableName}(${BaseColumns._ID}, ${Item.item_nameRes}, ${Item.item_iconRes}) VALUES (2,${ R.string.itemName_leaves2},${ R.drawable.leaves2});",
        "INSERT INTO ${Item.tableName}(${BaseColumns._ID}, ${Item.item_nameRes}, ${Item.item_iconRes}) VALUES (3,${ R.string.itemName_rock2},${ R.drawable.rock2});",
        "INSERT INTO ${Item.tableName}(${BaseColumns._ID}, ${Item.item_nameRes}, ${Item.item_iconRes}) VALUES (4,${ R.string.itemName_wood2},${ R.drawable.wood2});",
        "INSERT INTO ${Recipes.tableName} (${BaseColumns._ID}, ${Recipes.itempart_id},${Recipes.item_id},${Recipes.quantity}) VALUES (1, 1,1,3);",
        "INSERT INTO ${Recipes.tableName} (${BaseColumns._ID}, ${Recipes.itempart_id},${Recipes.item_id},${Recipes.quantity}) VALUES (2, 4,1,1);",
        "INSERT INTO ${Recipes.tableName} (${BaseColumns._ID}, ${Recipes.itempart_id},${Recipes.item_id},${Recipes.quantity}) VALUES (3, 2,2,3);",
        "INSERT INTO ${Recipes.tableName} (${BaseColumns._ID}, ${Recipes.itempart_id},${Recipes.item_id},${Recipes.quantity}) VALUES (4, 3,2,1);",
        "INSERT INTO ${Recipes.tableName} (${BaseColumns._ID}, ${Recipes.itempart_id},${Recipes.item_id},${Recipes.quantity}) VALUES (5, 3,3,3);",
        "INSERT INTO ${Recipes.tableName} (${BaseColumns._ID}, ${Recipes.itempart_id},${Recipes.item_id},${Recipes.quantity}) VALUES (6, 2,3,1);",
        "INSERT INTO ${Recipes.tableName} (${BaseColumns._ID}, ${Recipes.itempart_id},${Recipes.item_id},${Recipes.quantity}) VALUES (7, 4,4,3);",
        "INSERT INTO ${Recipes.tableName} (${BaseColumns._ID}, ${Recipes.itempart_id},${Recipes.item_id},${Recipes.quantity}) VALUES (8, 1,4,3);"
    )
    object Item: BaseColumns{
        const val tableName = "item"
        const val item_nameRes = "item_nameRes"
        const val item_iconRes = "item_iconRes"

        val drop = "DROP TABLE IF EXISTS ${Item.tableName};"
        val create = """
            CREATE TABLE ${Item.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${Item.item_nameRes} INTEGER ,
            	${Item.item_iconRes} INTEGER 
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }

    object Itempart: BaseColumns{
        const val tableName = "itempart"
        const val itempart_nameRes = "itempart_nameRes"
        const val itempart_iconRes = "itempart_iconRes"

        val drop = "DROP TABLE IF EXISTS ${Itempart.tableName};"
        val create = """
            CREATE TABLE ${Itempart.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${Itempart.itempart_nameRes} INTEGER ,
            	${Itempart.itempart_iconRes} INTEGER 
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }

    object Recipes: BaseColumns{
        const val tableName = "recipes"
        const val itempart_id = "itempart_id"
        const val item_id = "item_id"
        const val quantity = "quantity"

        val drop = "DROP TABLE IF EXISTS ${Recipes.tableName};"
        val create = """
            CREATE TABLE ${Recipes.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${Recipes.itempart_id} INTEGER ,
            	${Recipes.item_id} INTEGER ,
            	${Recipes.quantity} INTEGER ,
            	foreign key (${Recipes.item_id}) references ${Item.tableName}(${BaseColumns._ID}) on update cascade on delete cascade,
            	foreign key (${Recipes.itempart_id}) references ${Itempart.tableName}(${BaseColumns._ID}) on update cascade on delete cascade
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }

    object Player: BaseColumns{
        const val tableName = "player"
        const val player_name = "player_name"
        const val vit = "vit"
        const val def = "def"
        const val atk = "atk"

        val drop = "DROP TABLE IF EXISTS ${Player.tableName};"
        val create = """
            CREATE TABLE ${Player.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${Player.player_name} TEXT ,
            	${Player.vit} INTEGER ,
            	${Player.atk} INTEGER ,
            	${Player.def} INTEGER 
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }

    object Ranking: BaseColumns{
        const val tableName = "ranking"
        const val player_id = "player_id"
        const val pts = "pts"

        val drop = "DROP TABLE IF EXISTS ${Ranking.tableName};"
        val create = """
            CREATE TABLE ${Ranking.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${Ranking.player_id} INTEGER ,
            	${Ranking.pts} INTEGER ,
            	foreign key (${Ranking.player_id}) references ${Player.tableName}(${BaseColumns._ID}) on update cascade on delete cascade
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }

    object ItempartsObtenidos: BaseColumns{
        const val tableName = "itemparts_obtenidos"
        const val itemPart_id = "itemPart_id"
        const val player_id = "player_id"
        const val quantity = "quantity"

        val drop = "DROP TABLE IF EXISTS ${ItempartsObtenidos.tableName};"
        val create = """
            CREATE TABLE ${ItempartsObtenidos.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${ItempartsObtenidos.itemPart_id} INTEGER ,
            	${ItempartsObtenidos.player_id} INTEGER ,
            	${ItempartsObtenidos.quantity} INTEGER ,
            	foreign key (${ItempartsObtenidos.player_id}) references ${Player.tableName}(${BaseColumns._ID}) on update cascade on delete cascade,
            	foreign key (${ItempartsObtenidos.itemPart_id}) references ${Itempart.tableName}(${BaseColumns._ID}) on update cascade on delete cascade
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }
}