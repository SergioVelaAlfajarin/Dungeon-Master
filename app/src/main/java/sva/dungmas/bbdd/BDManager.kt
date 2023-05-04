package sva.dungmas.bbdd

import android.content.Context

class BDManager(
    val context: Context,
    val name: String
) {
    private var helper: BDHelper? = null

    fun connectTo(context: Context?, name: String, version: Int){
        helper = BDHelper(
            context,
            name,
            null,
            version
        )
    }

    fun disconnet(){
        helper = null
    }
}