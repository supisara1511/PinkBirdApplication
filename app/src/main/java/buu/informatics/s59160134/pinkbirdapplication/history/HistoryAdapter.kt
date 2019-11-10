package buu.informatics.s59160134.pinkbirdapplication.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.database.Period


class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    var data = listOf<Period>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    private fun ViewHolder.bind(item: Period) {
        startDate.text = "DATE START : " + item.periodStart.toString()
        endDate.text = "DATE STOP : " + item.periodEnd.toString()
    }

    override fun getItemCount() = data.size

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val startDate: TextView = itemView.findViewById(R.id.startDate)
        val endDate: TextView = itemView.findViewById(R.id.endDate)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_history_item, parent, false)
                return ViewHolder(view)
            }
        }
    }
}
