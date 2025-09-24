package com.example.matteapp.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matteapp.R

@Composable
fun ButtonPad(
    numberPressed: (String) -> Unit,
    onSubmitAnswer: () -> Unit,
    onEraseAnswer: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
            //.padding(10.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    // Setter opp knappene i en 3x3 grid, med 0-knappen utenpå nederst.
        for (rad in 0..2) {
            Row(
            ) {

                for (kolonne in 1..3) {
                    // Tallet til hver knapp
                    val buttonNumber = ( rad * 3 + kolonne )
                    Button(
                        modifier = Modifier
                            .size(125.dp)
                            .padding(2.dp),
                        onClick = {

                            Log.d("ButtonPad","$buttonNumber clicked")

                            // Her kalles parameter-funksjonen til knappene
                            numberPressed(buttonNumber.toString())
                        },
                        shape = RoundedCornerShape(25.dp),
                        border = BorderStroke(5.dp, Color.Gray),
                        colors = ButtonDefaults.buttonColors(Color(-10185006)),

                    ){
                        Text(
                            modifier = Modifier,
                            fontSize = 25.sp,
                            text = "$buttonNumber")
                    }
                }

            }
        }

        Row(
        ) {
            // Erase-knapp
            Button(
                modifier = Modifier
                    .size(125.dp)
                    .padding(2.dp),
                onClick = {onEraseAnswer()},
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                border = BorderStroke(5.dp, Color.Gray),
            ) {
                Image(painter = painterResource(id = R.drawable.erase_arrow), contentDescription = "Erase")
            }

            // Siste 0-knappen
            val buttonNumber = 0
            Button(
                modifier = Modifier
                    .size(125.dp)
                    .padding(2.dp),
                onClick = {
                    Log.d("ButtonPad","$buttonNumber clicked")
                    numberPressed(buttonNumber.toString())
                },
                colors = ButtonDefaults.buttonColors(Color(-10185006)),
                shape = RoundedCornerShape(25.dp),
                border = BorderStroke(5.dp, Color.Gray),
            ){
                Text(
                    modifier = Modifier.padding(25.dp),
                    fontSize = 25.sp,
                    text = "$buttonNumber")
            }

            // Submit button
            Button(
                modifier = Modifier
                    .size(125.dp)
                    .padding(2.dp),
                onClick = {onSubmitAnswer()},
                colors = ButtonDefaults.buttonColors(Color(-13449126)),
                border = BorderStroke(5.dp, Color.Gray),
            ) {
                Image(painter = painterResource(id = R.drawable.check), contentDescription = "Checkmark")
            }
        }

    }
}