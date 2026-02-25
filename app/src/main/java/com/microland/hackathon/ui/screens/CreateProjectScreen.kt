package com.microland.hackathon.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.microland.hackathon.ViewModel.ProjectViewModel
import com.microland.hackathon.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectScreen(navController: NavController) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var hackathonName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("AI/ML") }
    var teamSize by remember { mutableStateOf(3) }

    val categories = listOf("AI/ML", "Fintech", "Web", "Mobile", "Blockchain")
    val skills = listOf("React", "Django", "Kotlin", "ML", "UI/UX", "Pitching")

    var selectedSkills by remember { mutableStateOf(setOf<String>()) }
    val context = LocalContext.current
    val viewModel = remember { ProjectViewModel(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Project") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
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
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Text(
                "Project Details",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 📌 Project Title
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Project Title") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 📄 Description
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Project Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🏆 Hackathon Name
            OutlinedTextField(
                value = hackathonName,
                onValueChange = { hackathonName = it },
                label = { Text("Hackathon Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 📂 Category Dropdown
            Text("Category", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            var expanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Category") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                selectedCategory = it
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 🛠 Required Skills
            Text("Required Skills", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                skills.forEach { skill ->
                    FilterChip(
                        selected = selectedSkills.contains(skill),
                        onClick = {
                            selectedSkills =
                                if (selectedSkills.contains(skill))
                                    selectedSkills - skill
                                else
                                    selectedSkills + skill
                        },
                        label = { Text(skill) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 👥 Team Size
            Text("Team Size", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { if (teamSize > 1) teamSize-- }
                ) {
                    Text("-")
                }

                Text(
                    "$teamSize Members",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                IconButton(
                    onClick = { if (teamSize < 10) teamSize++ }
                ) {
                    Text("+")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 🚀 Publish Button
            Button(
                onClick = {
                    viewModel.createProject(
                        title,
                        description,
                        hackathonName,
                        selectedCategory,
                        selectedSkills.toList(),
                        teamSize
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(
                    "Publish Project",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
    LaunchedEffect(viewModel.createState) {
        if (viewModel.createState != null) {
            navController.navigate(Routes.MAIN) {
                popUpTo(Routes.CREATE_PROJECT) { inclusive = true }
            }
        }
    }
}