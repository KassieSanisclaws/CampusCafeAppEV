package com.raywenderlich.campuscafeappev.mainUI

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.Button

@Composable
fun HomeScreen(
    points: Int,
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
        Text( text = "Campus cafe" )
        Text( text = "Welcome, Students" )
        Text( text = "Points: $points" )
        Button( onClick = onMenuClick) {
            Text( text = "View Menu" )
        }
        Button(
            onClick = onProfileClick,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Student Profile")
        }



    }
}