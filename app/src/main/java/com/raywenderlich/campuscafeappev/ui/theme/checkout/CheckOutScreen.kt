package com.raywenderlich.campuscafeappev.ui.theme.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CheckOutScreen(
    total: Double,
    pointsEarned: Int,
    onDoneClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "☕", fontSize = 48.sp)
        Text(
            text = "Order Complete!",
            modifier = Modifier.padding(top = 12.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
            )
        Text(
            text = "Thank You For Visiting Campus Cafe!",
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 16.sp
            )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Order Summary",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Total Paid: $${"%.2f".format(total)}",
                    modifier = Modifier.padding(top = 16.dp),

                    )

                Text(
                    text = "$${"%.2f".format(total)}",
                    modifier = Modifier.padding(top = 4.dp),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Points Earned: $pointsEarned",
                    modifier = Modifier.padding(top = 16.dp),
                    fontSize = 18.sp
                )
            }
        }

        Button(
            onClick = onDoneClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text(text = "Done")
          }
      }
}