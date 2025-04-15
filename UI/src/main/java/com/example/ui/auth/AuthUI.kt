package com.example.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AuthUI {
    @Composable
    fun AuthContainer(content: @Composable ColumnScope.() -> Unit){
        Row(Modifier.fillMaxSize().padding(horizontal = 30.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Column {
                content()
            }
        }
    }

    @Composable
    fun AuthCard(content: @Composable ColumnScope.() -> Unit){
        Card(
            modifier = Modifier
                .fillMaxWidth().padding(bottom = 15.dp),
            elevation = CardDefaults.cardElevation(
                3.dp
            ),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }

    @Composable
    fun Header(text: String){
        Row {
            Text(text = text, fontSize = 24.sp)
        }
    }



}