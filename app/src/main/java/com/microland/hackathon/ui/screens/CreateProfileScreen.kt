package com.microland.hackathon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.microland.hackathon.ViewModel.AuthViewModel
import com.microland.hackathon.data.models.AuthViewModelFactory
import com.microland.hackathon.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val factory = remember { AuthViewModelFactory(context) }
    val viewModel: AuthViewModel = viewModel(factory = factory)

    var name by remember { mutableStateOf("") }
    var college by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var selectedExperience by remember { mutableStateOf("Beginner") }
    var selectedRole by remember { mutableStateOf("Frontend") }

    val experienceOptions = listOf("Beginner", "Intermediate", "Advanced")
    val roleOptions = listOf("Frontend", "Backend", "AI/ML", "Designer", "Pitching")
    val skills = listOf("React", "Django", "Kotlin", "UI/UX", "Machine Learning", "Public Speaking")

    var selectedSkills by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Create Profile") })
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = college,
                onValueChange = { college = it },
                label = { Text("College") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Short Bio") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Select Skills", fontWeight = FontWeight.Bold)
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

            Spacer(modifier = Modifier.height(24.dp))

            // Experience dropdown
            var expanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedExperience,
                    onValueChange = {},
                    label = { Text("Experience Level") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    experienceOptions.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                selectedExperience = it
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Role dropdown
            var roleExpanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = roleExpanded,
                onExpandedChange = { roleExpanded = !roleExpanded }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedRole,
                    onValueChange = {},
                    label = { Text("Preferred Role") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(roleExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )

                ExposedDropdownMenu(
                    expanded = roleExpanded,
                    onDismissRequest = { roleExpanded = false }
                ) {
                    roleOptions.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                selectedRole = it
                                roleExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    viewModel.createProfile(
                        name = name,
                        college = college,
                        bio = bio,
                        skills = selectedSkills.toList(),
                        experience = selectedExperience,
                        role = selectedRole
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !viewModel.isLoading
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(strokeWidth = 2.dp)
                } else {
                    Text("Save Profile")
                }
            }

            viewModel.profileError?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }

    // Navigate only when backend success
    LaunchedEffect(viewModel.profileState) {
        viewModel.profileState?.let {
            navController.navigate(Routes.MAIN) {
                popUpTo(Routes.CREATE_PROFILE) { inclusive = true }
            }
        }
    }
}