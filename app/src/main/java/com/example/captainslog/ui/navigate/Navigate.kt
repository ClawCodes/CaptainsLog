package com.example.captainslog.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.captainslog.data.model.AuthViewModel
import com.example.captainslog.data.model.FriendsViewModel
import com.example.captainslog.ui.login.LoginScreen
import com.example.captainslog.data.model.RecordViewModel
import com.example.captainslog.ui.friends.FriendsScreen
import com.example.captainslog.ui.record.RecordScreen

@Composable
fun AppNavigation(
    authVM: AuthViewModel,
    recordVM: RecordViewModel,
    firendVM: FriendsViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(authVM, onLoggedIn = {
                navController.navigate("record")
            })
        }
        composable("record") {
            // TODO: implement callbacks
            RecordScreen(recordVM, onGoToFriends = {
                navController.navigate("friendScreen")
            }, onGoToNotes = {
                println("IMPLEMENT GO TO NOTES!")
            })
        }
        composable ( "friendScreen" ) {
            FriendsScreen(firendVM) { }
        }
    }
}
