package com.microland.hackathon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.microland.hackathon.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(rootNavController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TeamSync") },
                actions = {
                    IconButton(
                        onClick = {
                            rootNavController.navigate(Routes.NOTIFICATION)
                        }
                    ) {
                        Icon(Icons.Default.Notifications, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {

            // 👋 Greeting Section
            Text(
                text = "Hello 👋",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Build your perfect hackathon team today.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                "Quick Actions",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionCard(
                title = "Create Project",
                subtitle = "Start a new hackathon idea",
                icon = Icons.Default.Add,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                rootNavController.navigate(Routes.CREATE_PROJECT)
            }

            ActionCard(
                title = "Browse Projects",
                subtitle = "Join an existing team",
                icon = Icons.Default.Search,
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                rootNavController.navigate(Routes.BROWSE)
            }
//
//            ActionCard(
//                title = "Ask AI Concierge",
//                subtitle = "Let AI build your team",
//                icon = Icons.Default.SmartToy,
//                containerColor = MaterialTheme.colorScheme.tertiaryContainer
//            ) {
//                rootNavController.navigate(Routes.MATCH_RESULTS)
//            }
        }
    }
}

@Composable
fun ActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    containerColor: Color,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        elevation = cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}