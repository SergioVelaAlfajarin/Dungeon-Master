package sva.dungmas.activities.recycler

import android.content.Context
import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.enums.Codes

class ViewHolder(v: View, val context: Context)
    : RecyclerView.ViewHolder(v), View.OnCreateContextMenuListener {

    override fun onCreateContextMenu(menu: ContextMenu, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu.add(adapterPosition, Codes.CONTEXT_DEL.code, 0, context.getString(R.string.contextDel))
    }
}