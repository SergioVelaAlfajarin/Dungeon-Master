package sva.dungmas.enums

import sva.dungmas.game.entities.Stats

enum class DefaultJobs(val stats: Stats){
    TANK(Stats(5,2,5)),
    ASSASSIN(Stats(3,6,3)),
    KNIGHT(Stats(4,4,4))
}