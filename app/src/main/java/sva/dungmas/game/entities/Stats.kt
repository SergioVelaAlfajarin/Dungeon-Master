package sva.dungmas.game.entities

data class Stats(
    var vit: Int,
    var atk: Int,
    var def: Int
) {
    override fun toString(): String {
        return "Vit: $vit\nAtk: $atk\nDef: $def"
    }
}