package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comp4521_holdthatthought.ui.theme.COMP4521HoldThatThoughtTheme
import androidx.compose.material3.Scaffold

enum class BottomTab { Home, Articles, AI, Profile }

@Composable
fun HomeScreen(onNavigate: (com.example.comp4521_holdthatthought.navigation.Screen) -> Unit) {
    val (tab, setTab) = remember { mutableStateOf(BottomTab.Home) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = tab == BottomTab.Home,
                    onClick = { setTab(BottomTab.Home) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = tab == BottomTab.Articles,
                    onClick = { setTab(BottomTab.Articles) },
                    icon = { Icon(Icons.Outlined.Article, contentDescription = "Articles") },
                    label = { Text("Articles") }
                )
                NavigationBarItem(
                    selected = tab == BottomTab.AI,
                    onClick = { setTab(BottomTab.AI) },
                    icon = { Text("AI") },
                    label = { Text("AI") }
                )
                NavigationBarItem(
                    selected = tab == BottomTab.Profile,
                    onClick = { setTab(BottomTab.Profile) },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { inner ->
        Column(modifier = Modifier.fillMaxSize().padding(inner)) {
            when (tab) {
                BottomTab.Home, BottomTab.Articles -> HomeTopBar()
                BottomTab.AI -> AITopBar()
                BottomTab.Profile -> { /* no top bar to avoid title duplication */ }
            }

            when (tab) {
                BottomTab.Home -> HomeTabContent(onNavigate)
                BottomTab.Articles -> ArticlesTabContent(onNavigate)
                BottomTab.AI -> AITabContent(onNavigate)
                BottomTab.Profile -> ProfileTabContent(onNavigate)
            }
        }
    }
}

@Composable
private fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { }) { Icon(Icons.Outlined.Search, contentDescription = "Search") }
        Text(text = "Home", style = MaterialTheme.typography.titleLarge)
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { }) { Icon(Icons.Outlined.Notifications, contentDescription = "Notifications") }
        }
    }

    // Tabs (All | Folder 1 | Folder 2) + plus
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("All", style = MaterialTheme.typography.titleMedium)
            Text("Folder 1", style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)))
            Text("Folder 2", style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)))
        }
        IconButton(onClick = { }) { Icon(Icons.Default.Add, contentDescription = "Add") }
    }
    Spacer(Modifier.height(8.dp))
}

@Composable private fun AITopBar() { Box(Modifier.fillMaxWidth().padding(16.dp)) { Text("AI Analysis", style = MaterialTheme.typography.titleMedium) } }

@Composable
private fun HomeTabContent(onNavigate: (com.example.comp4521_holdthatthought.navigation.Screen) -> Unit) {
    Node5_812Screen(onItemClick = { onNavigate(com.example.comp4521_holdthatthought.navigation.Screen.Node6_947) })
}

@Composable
private fun ArticlesTabContent(onNavigate: (com.example.comp4521_holdthatthought.navigation.Screen) -> Unit) {
    // Re-use home content for now
    Node5_812Screen(onItemClick = { onNavigate(com.example.comp4521_holdthatthought.navigation.Screen.Node6_947) })
}

@Composable
private fun AITabContent(onNavigate: (com.example.comp4521_holdthatthought.navigation.Screen) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Start a comprehension question based on the current article.", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(16.dp))
        com.example.comp4521_holdthatthought.ui.components.PrimaryButton(text = "Start", onClick = { onNavigate(com.example.comp4521_holdthatthought.navigation.Screen.AIQuestion) })
    }
}

@Composable
private fun ProfileTabContent(onNavigate: (com.example.comp4521_holdthatthought.navigation.Screen) -> Unit) {
    // Embed the My Account screen as the Profile tab
    Node9_1287Screen(onOpenSettings = { onNavigate(com.example.comp4521_holdthatthought.navigation.Screen.Node9_1333) })
}

// No preview needed
