package com.raywenderlich.campuscafeappev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.raywenderlich.campuscafeappev.navigation.AppNavigation
import com.raywenderlich.campuscafeappev.ui.theme.CampusCafeAppEVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusCafeAppEVTheme {
                AppNavigation()
            }
        }
    }
}
