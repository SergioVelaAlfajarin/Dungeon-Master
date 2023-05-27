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
        "INSERT INTO ${BDContract.Itempart.tableName}(${BaseColumns._ID}, ${BDContract.Itempart.itempart_nameRes}, ${BDContract.Itempart.itempart_iconRes}) VALUES (1,${ R.string.itemName_iron},${ R.drawable.iron1});",
        "INSERT INTO ${BDContract.Itempart.tableName}(${BaseColumns._ID}, ${BDContract.Itempart.itempart_nameRes}, ${BDContract.Itempart.itempart_iconRes}) VALUES (2,${ R.string.itemName_leaves},${ R.drawable.leaves1});",
        "INSERT INTO ${BDContract.Itempart.tableName}(${BaseColumns._ID}, ${BDContract.Itempart.itempart_nameRes}, ${BDContract.Itempart.itempart_iconRes}) VALUES (3,${ R.string.itemName_rock},${ R.drawable.rock1});",
        "INSERT INTO ${BDContract.Itempart.tableName}(${BaseColumns._ID}, ${BDContract.Itempart.itempart_nameRes}, ${BDContract.Itempart.itempart_iconRes}) VALUES (4,${ R.string.itemName_wood},${ R.drawable.wood1});",
        "INSERT INTO ${BDContract.Item.tableName}(${BaseColumns._ID}, ${BDContract.Item.item_nameRes}, ${BDContract.Item.item_iconRes}) VALUES (1,${ R.string.itemName_iron2},${ R.drawable.iron2});",
        "INSERT INTO ${BDContract.Item.tableName}(${BaseColumns._ID}, ${BDContract.Item.item_nameRes}, ${BDContract.Item.item_iconRes}) VALUES (2,${ R.string.itemName_leaves2},${ R.drawable.leaves2});",
        "INSERT INTO ${BDContract.Item.tableName}(${BaseColumns._ID}, ${BDContract.Item.item_nameRes}, ${BDContract.Item.item_iconRes}) VALUES (3,${ R.string.itemName_rock2},${ R.drawable.rock2});",
        "INSERT INTO ${BDContract.Item.tableName}(${BaseColumns._ID}, ${BDContract.Item.item_nameRes}, ${BDContract.Item.item_iconRes}) VALUES (4,${ R.string.itemName_wood2},${ R.drawable.wood2});",
        "INSERT INTO ${BDContract.Recipes.tableName} (${BaseColumns._ID}, ${BDContract.Recipes.itempart_id},${BDContract.Recipes.item_id},${BDContract.Recipes.quantity}) VALUES (1, 1,1,2);",
        "INSERT INTO ${BDContract.Recipes.tableName} (${BaseColumns._ID}, ${BDContract.Recipes.itempart_id},${BDContract.Recipes.item_id},${BDContract.Recipes.quantity}) VALUES (2, 2,1,3);",
        "INSERT INTO ${BDContract.Recipes.tableName} (${BaseColumns._ID}, ${BDContract.Recipes.itempart_id},${BDContract.Recipes.item_id},${BDContract.Recipes.quantity}) VALUES (3, 2,2,2);",
        "INSERT INTO ${BDContract.Recipes.tableName} (${BaseColumns._ID}, ${BDContract.Recipes.itempart_id},${BDContract.Recipes.item_id},${BDContract.Recipes.quantity}) VALUES (4, 3,2,3);",
        "INSERT INTO ${BDContract.Recipes.tableName} (${BaseColumns._ID}, ${BDContract.Recipes.itempart_id},${BDContract.Recipes.item_id},${BDContract.Recipes.quantity}) VALUES (5, 3,3,2);",
        "INSERT INTO ${BDContract.Recipes.tableName} (${BaseColumns._ID}, ${BDContract.Recipes.itempart_id},${BDContract.Recipes.item_id},${BDContract.Recipes.quantity}) VALUES (6, 4,3,3);",
        "INSERT INTO ${BDContract.Recipes.tableName} (${BaseColumns._ID}, ${BDContract.Recipes.itempart_id},${BDContract.Recipes.item_id},${BDContract.Recipes.quantity}) VALUES (7, 4,4,2);",
        "INSERT INTO ${BDContract.Recipes.tableName} (${BaseColumns._ID}, ${BDContract.Recipes.itempart_id},${BDContract.Recipes.item_id},${BDContract.Recipes.quantity}) VALUES (8, 1,4,3);",
    )

    object Item: BaseColumns{
        const val tableName = "item"
        const val item_nameRes = "item_nameRes"
        const val item_iconRes = "item_iconRes"

        val drop = "DROP TABLE IF EXISTS ${BDContract.Item.tableName};"
        val create = """
            CREATE TABLE ${BDContract.Item.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${BDContract.Item.item_nameRes} INTEGER ,
            	${BDContract.Item.item_iconRes} INTEGER 
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }

    object Itempart: BaseColumns{
        const val tableName = "itempart"
        const val itempart_nameRes = "itempart_nameRes"
        const val itempart_iconRes = "itempart_iconRes"

        val drop = "DROP TABLE IF EXISTS ${BDContract.Itempart.tableName};"
        val create = """
            CREATE TABLE ${BDContract.Itempart.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${BDContract.Itempart.itempart_nameRes} INTEGER ,
            	${BDContract.Itempart.itempart_iconRes} INTEGER 
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }

    object Recipes: BaseColumns{
        const val tableName = "recipes"
        const val itempart_id = "itempart_id"
        const val item_id = "item_id"
        const val quantity = "quantity"

        val drop = "DROP TABLE IF EXISTS ${BDContract.Recipes.tableName};"
        val create = """
            CREATE TABLE ${BDContract.Recipes.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${BDContract.Recipes.itempart_id} INTEGER ,
            	${BDContract.Recipes.item_id} INTEGER ,
            	${BDContract.Recipes.quantity} INTEGER ,
            	foreign key (${BDContract.Recipes.item_id}) references ${BDContract.Item.tableName}(${BaseColumns._ID}) on update cascade on delete cascade,
            	foreign key (${BDContract.Recipes.itempart_id}) references ${BDContract.Itempart.tableName}(${BaseColumns._ID}) on update cascade on delete cascade
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }

    object Player: BaseColumns{
        const val tableName = "player"
        const val player_name = "player_name"
        const val vit = "vit"
        const val def = "def"
        const val atk = "atk"

        val drop = "DROP TABLE IF EXISTS ${BDContract.Player.tableName};"
        val create = """
            CREATE TABLE ${BDContract.Player.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${BDContract.Player.player_name} TEXT ,
            	${BDContract.Player.vit} INTEGER ,
            	${BDContract.Player.atk} INTEGER ,
            	${BDContract.Player.def} INTEGER 
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }

    object Ranking: BaseColumns{
        const val tableName = "ranking"
        const val player_id = "player_id"
        const val pts = "pts"

        val drop = "DROP TABLE IF EXISTS ${BDContract.Ranking.tableName};"
        val create = """
            CREATE TABLE ${BDContract.Ranking.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${BDContract.Ranking.player_id} INTEGER ,
            	${BDContract.Ranking.pts} INTEGER ,
            	foreign key (${BDContract.Ranking.player_id}) references ${BDContract.Player.tableName}(${BaseColumns._ID}) on update cascade on delete cascade
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }

    object ItempartsObtenidos: BaseColumns{
        const val tableName = "itemparts_obtenidos"
        const val itemPart_id = "itemPart_id"
        const val player_id = "player_id"
        const val quantity = "quantity"

        val drop = "DROP TABLE IF EXISTS ${BDContract.ItempartsObtenidos.tableName};"
        val create = """
            CREATE TABLE ${BDContract.ItempartsObtenidos.tableName}(
            	${BaseColumns._ID} INTEGER PRIMARY KEY,
            	${BDContract.ItempartsObtenidos.itemPart_id} INTEGER ,
            	${BDContract.ItempartsObtenidos.player_id} INTEGER ,
            	${BDContract.ItempartsObtenidos.quantity} INTEGER ,
            	foreign key (${BDContract.ItempartsObtenidos.player_id}) references ${BDContract.Player.tableName}(${BaseColumns._ID}) on update cascade on delete cascade,
            	foreign key (${BDContract.ItempartsObtenidos.itemPart_id}) references ${BDContract.Itempart.tableName}(${BaseColumns._ID}) on update cascade on delete cascade
            );
        """.trimIndent().trimIndent().replace("\n", "").replace("\t","")
    }
}