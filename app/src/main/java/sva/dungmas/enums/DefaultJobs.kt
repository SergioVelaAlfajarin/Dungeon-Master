package sva.dungmas.enums

import sva.dungmas.game.entities.Stats

enum class DefaultJobs(val stats: Stats){
    TANK(Stats(50,2,5)),
    ASSASSIN(Stats(30,6,3)),
    KNIGHT(Stats(40,4,4))
}