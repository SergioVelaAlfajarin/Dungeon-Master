package sva.dungmas.bbdd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import sva.dungmas.R

const val name = "DungMas"
const val version = 1

class BDHelper(
    context: Context
): SQLiteOpenHelper(context, name, null, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlScriptCreate())
    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {
        db.execSQL(sqlScriptDrop())
        onCreate(db)
    }

    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
        db.execSQL("PRAGMA foreign_keys= ON")
    }

    private fun sqlScriptDrop(): String{
        return """
            DROP TABLE IF EXISTS item;
            DROP TABLE IF EXISTS itempart;
            DROP TABLE IF EXISTS recipes;
            DROP TABLE IF EXISTS player;
            DROP TABLE IF EXISTS ranking;
            DROP TABLE IF EXISTS itemparts_obtenidos;
        """.trimIndent()
    }

    private fun sqlScriptCreate(): String{
        return """
            CREATE TABLE item(
            	item_id INTEGER PRIMARY KEY,
            	item_nameRes INTEGER NOT NULL,
            	item_iconRes INTEGER NOT NULL
            );
            CREATE TABLE itempart(
            	itempart_id INTEGER PRIMARY KEY,
            	itempart_nameRes INTEGER NOT NULL,
            	itempart_iconRes INTEGER NOT NULL
            );
            CREATE TABLE recipes(
            	recipe_id INTEGER PRIMARY KEY,
            	itempart_id INTEGER NOT NULL,
            	item_id INTEGER NOT NULL,
            	quantity INTEGER NOT NULL,
            	foreign key (item_id) references item(item_id) on update cascade on delete cascade,
            	foreign key (itempart_id) references itempart(itempart_id) on update cascade on delete cascade
            );
            CREATE TABLE player(
            	player_id INTEGER PRIMARY KEY,
            	player_name TEXT NOT NULL,
            	vit INTEGER NOT NULL,
            	def INTEGER NOT NULL,
            	atk INTEGER NOT NULL
            );
            CREATE TABLE ranking(
            	rank_id INTEGER PRIMARY KEY,
            	player_id INTEGER NOT NULL,
            	pts INTEGER NOT NULL,
            	foreign key (player_id) references player(player_id) on update cascade on delete cascade
            );
            CREATE TABLE itemparts_obtenidos(
            	ipo_id INTEGER PRIMARY KEY,
            	itempart_id INTEGER NOT NULL,
            	player_id INTEGER NOT NULL,
            	quantity INTEGER NOT NULL,
            	foreign key (player_id) references player(player_id) on update cascade on delete cascade,
            	foreign key (itempart_id) references itempart(itempart_id) on update cascade on delete cascade
            );
            INSERT INTO itempart(itempart_id, itempart_nameres, itempart_iconres) VALUES (1,${R.string.itemName_iron},${R.drawable.iron1});
            INSERT INTO itempart(itempart_id, itempart_nameres, itempart_iconres) VALUES (2,${R.string.itemName_leaves},${R.drawable.leaves1});
            INSERT INTO itempart(itempart_id, itempart_nameres, itempart_iconres) VALUES (3,${R.string.itemName_rock},${R.drawable.rock1});
            INSERT INTO itempart(itempart_id, itempart_nameres, itempart_iconres) VALUES (4,${R.string.itemName_wood},${R.drawable.wood1});
            INSERT INTO item(item_id, item_nameres, item_iconres) VALUES (1,${R.string.itemName_iron2},${R.drawable.iron2});
            INSERT INTO item(item_id, item_nameres, item_iconres) VALUES (2,${R.string.itemName_leaves2},${R.drawable.leaves2});
            INSERT INTO item(item_id, item_nameres, item_iconres) VALUES (3,${R.string.itemName_rock2},${R.drawable.rock2});
            INSERT INTO item(item_id, item_nameres, item_iconres) VALUES (4,${R.string.itemName_wood2},${R.drawable.wood2});
            INSERT INTO recipes (recipe_id, itempart_id,item_id,quantity) VALUES (1,1,2);
            INSERT INTO recipes (recipe_id, itempart_id,item_id,quantity) VALUES (2,1,3);
            INSERT INTO recipes (recipe_id, itempart_id,item_id,quantity) VALUES (2,2,2);
            INSERT INTO recipes (recipe_id, itempart_id,item_id,quantity) VALUES (3,2,3);
            INSERT INTO recipes (recipe_id, itempart_id,item_id,quantity) VALUES (3,3,2);
            INSERT INTO recipes (recipe_id, itempart_id,item_id,quantity) VALUES (4,3,3);
            INSERT INTO recipes (recipe_id, itempart_id,item_id,quantity) VALUES (4,4,2);
            INSERT INTO recipes (recipe_id, itempart_id,item_id,quantity) VALUES (1,4,3);
        """.trimIndent()
    }
}