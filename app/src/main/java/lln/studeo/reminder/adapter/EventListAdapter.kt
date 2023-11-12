package lln.studeo.reminder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lln.studeo.reminder.R
import lln.studeo.reminder.model.EventUser

class EventListAdapter : RecyclerView.Adapter<EventListItemViewHolder>() {

    var event = emptyList<EventUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_event, parent, false)
        return EventListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventListItemViewHolder, position: Int) {
        holder.bind(event[position])
    }

    override fun getItemCount(): Int = event.size
}