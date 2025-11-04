package com.example.captainslog.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.captainslog.data.model.AuthViewModel
import com.example.captainslog.data.model.FriendsViewModel
import com.example.captainslog.data.model.NotesViewModel
import com.example.captainslog.ui.login.LoginScreen
import com.example.captainslog.data.model.RecordViewModel
import com.example.captainslog.ui.friends.FriendsScreen
import com.example.captainslog.ui.notes.NotesScreen
import com.example.captainslog.ui.record.RecordScreen

@Composable
fun AppNavigation(
    authVM: AuthViewModel,
    recordVM: RecordViewModel,
    friendVM: FriendsViewModel,
    notesVM: NotesViewModel,
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
            RecordScreen(recordVM, onGoToFriends = {
                navController.navigate("friendScreen")
            }, onGoToNotes = {
                navController.navigate("notescreen")
            })
        }
        composable ( "friendScreen" ) {
            // TODO: implement onOpenProfile
            FriendsScreen(friendVM, {}) {
                navController.navigate("record")
            }
        }
        composable ("notescreen"){
            // TODO: implement onOpenNote
            NotesScreen(vm = notesVM, onOpenNote = {}){
                navController.navigate("record")
            }
        }
    }
}
