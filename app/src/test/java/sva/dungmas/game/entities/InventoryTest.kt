package sva.dungmas.game.entities
import org.junit.jupiter.api.Test
import android.content.ClipData.Item

internal class InventoryTest {
    lateinit var inventory: Inventory

    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
        inventory = Inventory()
    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {
    }

    @Test
    fun testIfItemIsCraftable(){
        val item = sva.dungmas.game.items.Item(
            1,-1,-1, hashMapOf(
                sva.dungmas.game.items.ItemPart(1,0,0) to 3,
                sva.dungmas.game.items.ItemPart(2,0,0) to 3,
                sva.dungmas.game.items.ItemPart(3,0,0) to 3,
            )
        )

        assert(!inventory.checkIfItemIsCraftable(item)) //inventario vacio

        inventory.add(sva.dungmas.game.items.ItemPart(1,0,0), 2)
        inventory.add(sva.dungmas.game.items.ItemPart(2,0,0), 2)
        inventory.add(sva.dungmas.game.items.ItemPart(3,0,0), 2)

        assert(!inventory.checkIfItemIsCraftable(item)) //not enough

        inventory.add(sva.dungmas.game.items.ItemPart(1,0,0), 2)
        inventory.add(sva.dungmas.game.items.ItemPart(2,0,0), 2)
        inventory.add(sva.dungmas.game.items.ItemPart(3,0,0), 2)

        assert(inventory.checkIfItemIsCraftable(item)) //enough
    }

    @Test
    fun testRemoveInventory(){
        inventory.add(sva.dungmas.game.items.ItemPart(1,0,0), 2)
        inventory.add(sva.dungmas.game.items.ItemPart(2,0,0), 2)
        inventory.add(sva.dungmas.game.items.ItemPart(3,0,0), 5)

        //2 de cada

        inventory.removeAll(sva.dungmas.game.items.ItemPart(1,0,0))
        assert(inventory[sva.dungmas.game.items.ItemPart(1,0,0)] == 0)

        inventory.removeOne(sva.dungmas.game.items.ItemPart(2,0,0))
        assert(inventory[sva.dungmas.game.items.ItemPart(2,0,0)] == 1)

        inventory.remove(sva.dungmas.game.items.ItemPart(3,0,0), 3)
        assert(inventory[sva.dungmas.game.items.ItemPart(3,0,0)] == 2)

        inventory.remove(sva.dungmas.game.items.ItemPart(3,0,0), 3)
        assert(inventory[sva.dungmas.game.items.ItemPart(3,0,0)] == 0)
    }

    /*
        fun removeAll(storable: Storable){
        if(inventory.containsKey(storable)){
            inventory[storable] = 0
        }
    }

    fun removeOne(storable: Storable){
        if(inventory.containsKey(storable)){
            var currrent = inventory[storable]?.minus(1) ?: 0
            if(currrent < 0){
                currrent = 0
            }
            inventory[storable] = currrent
        }
    }

    fun remove(storable: Storable, howMany: Int){
        if(inventory.containsKey(storable)){
            var currrent = inventory[storable]?.minus(howMany) ?: 0
            if(currrent < 0){
                currrent = 0
            }
            inventory[storable] = currrent
        }
    }
     */
}