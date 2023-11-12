package lln.studeo.reminder.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_event.view.*
import lln.studeo.reminder.model.EventUser

class EventListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(event: EventUser) {
        itemView.tvModule.text = event.time
        itemView.tvTitleItem.text = event.title
    }
}