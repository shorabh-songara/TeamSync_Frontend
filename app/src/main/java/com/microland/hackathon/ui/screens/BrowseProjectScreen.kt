package com.microland.hackathon.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseProjectScreen(
    navController: NavController
) {

    var searchQuery by remember { mutableStateOf("") }

    val filters = listOf("All", "AI/ML", "Frontend", "Backend", "Fintech")
    var selectedFilter by remember { mutableStateOf("All") }

    val projects = listOf(
        ProjectUI("Fintech Payment App", "Need React + Backend + Pitch", listOf("React", "Django", "Pitch")),
        ProjectUI("AI Resume Builder", "Looking for ML + UI/UX", listOf("ML", "UI/UX")),
        ProjectUI("Campus Social App", "Need Android + Backend", listOf("Kotlin", "Node"))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Browse Projects") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }

            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // 🔍 Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search projects...") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🏷 Filter Chips
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                filters.forEach { filter ->
                    FilterChip(
                        selected = selectedFilter == filter,
                        onClick = { selectedFilter = filter },
                        label = { Text(filter) },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 📜 Project List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(projects) { project ->
                    ProjectCard(project)
                }
            }
        }
    }
}

/* ---------------- Project Card ---------------- */

@Composable
fun ProjectCard(project: ProjectUI) {

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = project.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                project.skills.forEach {
                    AssistChip(
                        onClick = {},
                        label = { Text(it) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Join logic */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Request to Join")
            }
        }
    }
}

/* ---------------- Dummy Model ---------------- */

data class ProjectUI(
    val title: String,
    val description: String,
    val skills: List<String>
)