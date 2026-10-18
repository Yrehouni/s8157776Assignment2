package com.youness.s8157776assignment2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.youness.s8157776assignment2.R
import com.youness.s8157776assignment2.model.Entity

class EntityAdapter(
    private var items: List<Entity>,
    private val onClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    fun updateData(newItems: List<Entity>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = items[position]
        holder.bind(entity)
        holder.itemView.setOnClickListener { onClick(entity) }
    }

    override fun getItemCount(): Int = items.size

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val destinationText: TextView = itemView.findViewById(R.id.property1Text)
        private val countryText: TextView = itemView.findViewById(R.id.property2Text)

        fun bind(entity: Entity) {
            destinationText.text = entity.destination
            countryText.text = entity.country
        }
    }
}
