package com.example.comp4521_holdthatthought

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.comp4521_holdthatthought.ui.theme.COMP4521HoldThatThoughtTheme
import com.example.comp4521_holdthatthought.ui.theme.AppViewModel
import com.example.comp4521_holdthatthought.navigation.Screen
import com.example.comp4521_holdthatthought.screens.HomeScreen
import com.example.comp4521_holdthatthought.screens.Node4_4573Screen
import com.example.comp4521_holdthatthought.screens.Node4_4106Screen
import com.example.comp4521_holdthatthought.screens.Node4_3891Screen
import com.example.comp4521_holdthatthought.screens.Node13_1155Screen
import com.example.comp4521_holdthatthought.screens.Node5_812Screen
import com.example.comp4521_holdthatthought.screens.Node6_947Screen
import com.example.comp4521_holdthatthought.screens.Node8_1053Screen
import com.example.comp4521_holdthatthought.screens.Node9_1287Screen
import com.example.comp4521_holdthatthought.screens.Node9_1333Screen
import com.example.comp4521_holdthatthought.screens.Node8_968Screen
import com.example.comp4521_holdthatthought.screens.AIQuestionScreen
import com.example.comp4521_holdthatthought.screens.AIResultScreen
import com.example.comp4521_holdthatthought.screens.RegisterScreen
import com.example.comp4521_holdthatthought.screens.ShareReceiverScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {
    // StateFlow to hold shared content - survives configuration changes
    private val _sharedText = MutableStateFlow<String?>(null)
    val sharedText: StateFlow<String?> = _sharedText.asStateFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Handle share intent on initial launch
        handleShareIntent(intent)

        setContent {
            val appViewModel: AppViewModel = viewModel()
            COMP4521HoldThatThoughtTheme {
                App(
                    viewModel = appViewModel,
                    sharedText = sharedText,
                    onShareHandled = { _sharedText.value = null }
                )
            }
        }
    }

    // Called when activity is already running and receives new intent (singleTop)
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent) // Update the intent
        handleShareIntent(intent)
    }

    private fun handleShareIntent(intent: Intent?) {
        if (intent?.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
                _sharedText.value = text
            }
            // Clear the intent action to prevent reprocessing on configuration changes
            intent.action = ""
        }
    }
}

@Composable
fun App(
    viewModel: AppViewModel,
    sharedText: StateFlow<String?> = MutableStateFlow(null),
    onShareHandled: () -> Unit = {}
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val currentScreen: Screen = Screen.fromRoute(currentDestination?.route)

    // Collect shared text state
    val sharedContent by sharedText.collectAsState()

    // Navigate to ShareReceiver when shared content arrives
    LaunchedEffect(sharedContent) {
        sharedContent?.let {
            // Navigate to share receiver, clearing the back stack issue
            navController.navigate(Screen.ShareReceiver.route) {
                // Don't add to back stack if we're coming from outside the app
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Node4_3891.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(onNavigate = { navController.navigate(it.route) }) }
            composable(Screen.Node4_4573.route) { Node4_4573Screen(onPrimary = { navController.navigate(Screen.Home.route) }, onSecondary = { navController.navigate(Screen.Register.route) }, onSkip = { navController.navigate(Screen.Home.route) }) }
            composable(Screen.Node4_4106.route) { Node4_4106Screen(onPrimary = { navController.navigate(Screen.Node4_4573.route) }, onSecondary = { navController.navigate(Screen.Register.route) }, onSkip = { navController.navigate(Screen.Home.route) }) }
            composable(Screen.Node4_3891.route) { Node4_3891Screen(onPrimary = { navController.navigate(Screen.Node4_4106.route) }, onSecondary = { navController.navigate(Screen.Register.route) }, onSkip = { navController.navigate(Screen.Home.route) }) }
            composable(Screen.Node13_1155.route) { Node13_1155Screen() }
            composable(Screen.Node5_812.route) { Node5_812Screen(onItemClick = { navController.navigate(Screen.Node6_947.route) }) }
            composable(Screen.Node6_947.route) {
                Node6_947Screen(
                    onBack = { navController.navigateUp() },
                    onAIQuestions = { navController.navigate(Screen.AIQuestion.route) }
                )
            }
            composable(Screen.Node8_1053.route) { Node8_1053Screen() }
            composable(Screen.Node9_1287.route) { Node9_1287Screen() }
            composable(Screen.Node9_1333.route) { Node9_1333Screen(onBack = { navController.navigateUp() }) }
            composable(Screen.Node8_968.route) { Node8_968Screen() }
            composable(Screen.AIQuestion.route) { AIQuestionScreen(onSubmit = { navController.navigate(Screen.AIResult.route) }, onCancel = { navController.navigateUp() }) }
            composable(Screen.AIResult.route) { AIResultScreen(onNext = { navController.navigateUp() }, onCancel = { navController.navigateUp() }) }
            composable(Screen.Register.route) { RegisterScreen(onDone = { navController.navigate(Screen.Home.route) }, onBack = { navController.navigateUp() }) }
            composable(Screen.ShareReceiver.route) {
                ShareReceiverScreen(
                    viewModel = viewModel,
                    sharedText = sharedContent ?: "",
                    onSave = { title, url, notes ->
                        onShareHandled()
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    onCancel = {
                        onShareHandled()
                        // If we have a back stack, go back; otherwise go home
                        if (!navController.navigateUp()) {
                            navController.navigate(Screen.Home.route)
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    COMP4521HoldThatThoughtTheme { App() }
}
