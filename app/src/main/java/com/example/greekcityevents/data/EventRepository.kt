package com.example.greekcityevents.data

import com.example.greekcityevents.model.Category
import com.example.greekcityevents.model.City
import com.example.greekcityevents.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class EventRepository {

    fun getSampleEventsForHomescreen(): List<Event> = listOf(
        Event(
            id = "1",
            title = "Athens Music Festival",
            description = "Annual music festival featuring top Greek artists",
            city = City.ATHENS,
            category = Category.MUSIC,
            imageUrl = "https://images.unsplash.com/photo-1470229722913-7c0e2dbbafd3",
            date = "2024-05-15",
            viewCount = 1500,
            location = "Technopolis"
        ),
        Event(
            id = "2",
            title = "Thessaloniki Food Festival",
            description = "Traditional Greek cuisine celebration",
            city = City.THESSALONIKI,
            category = Category.FOOD,
            imageUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836",
            date = "2024-06-20",
            viewCount = 1200,
            location = "Aristotelous Square"
        ),
        Event(
            id = "3",
            title = "Patras Carnival",
            description = "Biggest carnival celebration in Greece",
            city = City.PATRA,
            category = Category.ENTERTAINMENT,
            imageUrl = "https://images.unsplash.com/photo-1674821120341-5565be3a1386?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            date = "2024-03-30",
            viewCount = 2000,
            location = "City Center"
        ),
        Event(
            id = "4",
            title = "Larissa Cultural Festival",
            description = "A celebration of art and culture in the heart of Thessaly",
            city = City.LARISSA,
            category = Category.CULTURE,
            imageUrl = "https://images.unsplash.com/photo-1533929736458-ca588d08c8be",
            date = "2024-04-10",
            viewCount = 800,
            location = "Larissa Castle"
        )
    )
} 