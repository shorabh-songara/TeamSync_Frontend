package com.microland.hackathon.ui.screens

import androidx.compose.runtime.Composable
import com.microland.hackathon.data.MyProjectUI


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProjectScreen() {

    val myProjects = listOf(
        MyProjectUI("AI Resume Builder", "Active", 4),
//        MyProjectUI("Fintech Payment App", "Completed", 3),
//        MyProjectUI("Campus Social Network", "Active", 5)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Projects") }
            )
        },
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                onClick = { /* Navigate to create project */ },
//                icon = { Icon(Icons.Default.Add, contentDescription = null) },
//                text = { Text("New Project") }
//            )
//        }
    ) { paddingValues ->

        if (myProjects.isEmpty()) {
            EmptyProjectsUI(Modifier.padding(paddingValues))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(myProjects) { project ->
                    MyProjectCard(project)
                }
            }
        }
    }
}
@Composable
fun EmptyProjectsUI(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                imageVector = Icons.Default.Group,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "No Projects Yet",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Create your first project and build your dream team.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Composable
fun MyProjectCard(project: MyProjectUI) {

    val statusColor = when (project.status) {
        "Active" -> MaterialTheme.colorScheme.primary
        "Completed" -> MaterialTheme.colorScheme.outline
        else -> MaterialTheme.colorScheme.secondary
    }

    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            // Title + Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                AssistChip(
                    onClick = {},
                    label = { Text(project.status) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = statusColor.copy(alpha = 0.15f),
                        labelColor = statusColor
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Team info
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Group,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${project.teamSize} Members",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Navigate to details */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("View Details")
            }
        }
    }
}