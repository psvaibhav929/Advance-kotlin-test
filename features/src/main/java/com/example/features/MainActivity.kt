package com.example.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.features.constants.BundleConstant
import com.example.features.constants.Screen
import com.example.features.details.MatchDetailsScreen
import com.example.features.list.CricketMatchListScreen
import com.example.features.theme.SapientTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SapientTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController, Screen.CricketMatchListScreen.route
                    ) {
                        composable(
                            route = Screen.CricketMatchListScreen.route
                        ) {
                            CricketMatchListScreen(
                                {matchName ->
                                    navController.navigate(Screen.CricketMatchDetailScreen.route + "/$matchName")
                                }
                            )
                        }
                        composable(
                            route = Screen.CricketMatchDetailScreen.route + "/{${BundleConstant.PARAM_MATCH_NAME}}",
                            arguments = listOf(
                                navArgument(BundleConstant.PARAM_MATCH_NAME) {
                                    type = NavType.StringType
                                },

                            )
                        ) {
                            MatchDetailsScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SapientTestTheme {
        Greeting("Android")
    }
}