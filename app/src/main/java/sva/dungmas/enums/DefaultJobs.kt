package sva.dungmas.enums

import sva.dungmas.game.entities.Stats

enum class DefaultJobs(val stats: Stats){
    TANK(Stats(80,5,12)),
    ASSASSIN(Stats(50,15,5)),
    KNIGHT(Stats(80,8,8))
}