package com.example.greekcityevents

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.greekcityevents.data.EventRepository
import com.example.greekcityevents.model.Category
import com.example.greekcityevents.model.City
import com.example.greekcityevents.ui.components.EventCard
import com.example.greekcityevents.ui.theme.GreekCityEventsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreekCityEventsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EventsScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {
    val context = LocalContext.current
    var expandedCityDropdown by remember { mutableStateOf(false) }
    var expandedCategoryDropdown by remember { mutableStateOf(false) }
    val selectedCity by remember { mutableStateOf<City?>(null) }
    val selectedCategory by remember { mutableStateOf<Category?>(null) }

    // Sample event list (get this from EventRepository or your local data)
    val events = EventRepository().getSampleEventsForHomescreen()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Header
            Text(
                text = "Greek City Events",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(16.dp)
            )

            // Dropdowns
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // City Dropdown
                ExposedDropdownMenuBox(
                    expanded = expandedCityDropdown,
                    onExpandedChange = { expandedCityDropdown = it },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedCity?.name?.lowercase()?.capitalize() ?: "Select City",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCityDropdown) },
                        modifier = Modifier.menuAnchor(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = expandedCityDropdown,
                        onDismissRequest = { expandedCityDropdown = false }
                    ) {
                        City.values().forEach { city ->
                            DropdownMenuItem(
                                text = { Text(city.name.lowercase().capitalize()) },
                                onClick = {
                                    expandedCityDropdown = false
                                    val intent = Intent(context, EventListActivity::class.java).apply {
                                        putExtra("selectedCity", city.name)
                                    }
                                    context.startActivity(intent)
                                }
                            )
                        }
                    }
                }

                // Category Dropdown
                ExposedDropdownMenuBox(
                    expanded = expandedCategoryDropdown,
                    onExpandedChange = { expandedCategoryDropdown = it },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedCategory?.name?.lowercase()?.capitalize() ?: "Select Category",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategoryDropdown) },
                        modifier = Modifier.menuAnchor(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = expandedCategoryDropdown,
                        onDismissRequest = { expandedCategoryDropdown = false }
                    ) {
                        Category.values().forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category.name.lowercase().capitalize()) },
                                onClick = {
                                    expandedCategoryDropdown = false
                                    val intent = Intent(context, EventListActivity::class.java).apply {
                                        putExtra("selectedCategory", category.name)
                                    }
                                    context.startActivity(intent)
                                }
                            )
                        }
                    }
                }
            }

            // Events List (Including Most Viewed Events)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Most Viewed Events Section
                item {
                    Text(
                        text = "Most Viewed Events",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                // Display all the events from the sample data
                items(events) { event ->
                    EventCard(event = event)
                }
            }
        }
    }
}
