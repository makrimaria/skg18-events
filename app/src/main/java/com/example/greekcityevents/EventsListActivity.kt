package com.example.greekcityevents

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greekcityevents.api.RetrofitClient
import com.example.greekcityevents.model.Event
import com.example.greekcityevents.ui.EventAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private val eventList = mutableListOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        eventAdapter = EventAdapter(eventList)
        recyclerView.adapter = eventAdapter

        val selectedCity = intent.getStringExtra("selectedCity")
        val selectedCategory = intent.getStringExtra("selectedCategory")

        fetchEvents(selectedCity, selectedCategory)
    }

    private fun fetchEvents(city: String?, category: String?) {
        RetrofitClient.instance.getEvents(city, category).enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        eventList.clear()
                        eventList.addAll(it)
                        eventAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@EventListActivity, "Failed to load events", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                Toast.makeText(this@EventListActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("EventListActivity", "Error fetching events", t)
            }
        })
    }
}
