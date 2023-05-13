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
}