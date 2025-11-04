package com.example.captainslog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.captainslog.data.model.AuthViewModel
import com.example.captainslog.data.model.FriendsViewModel
import com.example.captainslog.data.model.RecordViewModel
import com.example.captainslog.ui.navigate.AppNavigation
import com.example.captainslog.ui.theme.CaptainslogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val authVM by viewModels<AuthViewModel>()
            val recordVM by viewModels<RecordViewModel>()
            val friendVM by viewModels<FriendsViewModel>()

            CaptainslogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        authVM = authVM,
                        recordVM = recordVM,
                        firendVM = friendVM,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}