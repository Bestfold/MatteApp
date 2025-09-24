package com.example.matteapp.ui.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matteapp.ui.components.ButtonPad
import com.example.matteapp.ui.viewmodels.GameViewModel

@Composable
fun GameScreen() {
    val gameViewModel: GameViewModel = viewModel()



    Scaffold (modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
    )
    { innerPadding ->
        Card(
            modifier = Modifier
                .padding(innerPadding),

        ) {
            Column(
                modifier = Modifier
                    //.padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                val currentRegnestykke = gameViewModel.currentMattestykke.value

                Card(
                    modifier = Modifier.padding(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                ) {
                    Text(
                        modifier = Modifier.padding(15.dp),
                        text = "$currentRegnestykke = ?",
                        fontSize = 75.sp,
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                        //.border(5.dp, Color.Gray)
                ) {
                    Text(
                        text = gameViewModel.inputAnswer.value,
                        fontSize = 75.sp,
                    )
                    if(gameViewModel.inputAnswer.value != "") {
                        HorizontalDivider(
                            thickness = 5.dp,
                            color = Color.Gray
                        )
                    }
                    Text(
                        text = "0 / 0",
                        fontSize = 25.sp,
                    )
                }

                ButtonPad({gameViewModel.onInputNumberFromPadChange(it)},
                    {gameViewModel.onSubmitAnswer()},
                    {gameViewModel.onEraseAnswer()})
            }
        }

    }
}

fun buttonClicked() {
    Log.d("GameScreen","Knapp trykka på")
}