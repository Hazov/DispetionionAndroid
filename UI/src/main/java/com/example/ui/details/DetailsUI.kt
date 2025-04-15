package com.example.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DetailsUI {

    @Composable
    fun DetailsContainer(content: @Composable ColumnScope.() -> Unit) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            content()
        }
    }


    @Composable
    fun DetailsCard(cardHeader: String? = null, content: @Composable ColumnScope.() -> Unit) {
        Card(
            modifier = Modifier
                .padding(vertical = 15.dp),
            elevation = CardDefaults.cardElevation(
                15.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ) {
            if (cardHeader != null) {
                Row {
                    Text(cardHeader)
                }
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                content()
            }

        }
    }

    @Composable
    fun DetailsPairRow(leftText: String, content: @Composable ColumnScope.() -> Unit) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = leftText)
            Column {
                content()
            }

        }
    }

    @Composable
    fun DetailsPairRow(leftText: String, rightText: String) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = leftText);
            Text(fontSize = 17.sp, text = rightText);
        }
    }

    @Composable
    fun Header(headerText: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(modifier = Modifier.padding(15.dp), text = headerText, fontSize = 30.sp)
        }
    }
}