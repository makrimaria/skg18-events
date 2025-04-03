package com.example.greekcityevents.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greekcityevents.R
import com.example.greekcityevents.model.Event

class EventAdapter(private val events: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.eventTitle)
        val descriptionTextView: TextView = view.findViewById(R.id.eventDescription)
        val dateTextView: TextView = view.findViewById(R.id.eventDate)
        val locationTextView: TextView = view.findViewById(R.id.eventLocation)
        val categoryTextView: TextView = view.findViewById(R.id.eventCategory)
        val cityTextView: TextView = view.findViewById(R.id.eventCity)
        val imageView: ImageView = view.findViewById(R.id.eventImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.titleTextView.text = event.title
        holder.descriptionTextView.text = event.description
        holder.dateTextView.text = "Date: ${event.date}"
        holder.locationTextView.text = "Location: ${event.location}"
        holder.categoryTextView.text = "Category: ${event.category.name}"
        holder.cityTextView.text = "City: ${event.city.name}"

        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load(event.imageUrl)
            //.placeholder(R.drawable.placeholder) // Default image if loading fails
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = events.size
}
