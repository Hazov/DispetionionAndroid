package com.example.ui.entites.trip

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

class TripUI {
    @Composable
    fun PointsLine(
        sortedPoints: List<TripDetailsCargoPointUIModel>,
        @DrawableRes unloadIcon: Int,
        @DrawableRes uploadIcon: Int,
    ) {
        Box(
            Modifier
                .horizontalScroll(rememberScrollState())
                .width((sortedPoints.size * 120).dp)
                .padding(vertical = 30.dp)
        ) {

            Row(
                modifier = Modifier
                    .zIndex(9000f)
                    .offset(x = 40.dp)
            ) {
                sortedPoints.forEach({ point ->
                    val backgroundColor = when (point.isCompleted) {
                        true -> Color(0xff2a711f)
                        false -> Color(0xff24ccc6)
                    }
                    val iconLoadTypeColor =
                        if (point.type.equals("UPLOAD")) Color(0xffc56d27) else Color(0xff196646)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            modifier = Modifier
                                .padding(horizontal = 25.dp)
                                .background(color = backgroundColor, shape = CircleShape)
                                .size(50.dp),
                            onClick = {}
                        ) {

                            Icon(
                                modifier = Modifier.fillMaxSize(0.5f),
                                tint = iconLoadTypeColor,
                                painter = painterResource(if (point.type.equals("UPLOAD")) uploadIcon else unloadIcon),
                                contentDescription = "Загрузка"
                            )
                        }
                        Text(textAlign = TextAlign.Center, text = point.city)
                    }


                })

            }

            Canvas(
                Modifier
                    .width((sortedPoints.size * 120).dp)
                    .height(50.dp)
                    .background(Color.Transparent)
            ) {

                val height = size.height
                val width = size.width
                drawLine(
                    start = Offset(x = 0f, y = height / 2),
                    end = Offset(x = width, y = height / 2),
                    color = Color.DarkGray,
                    strokeWidth = 5.0f,
                )
            }
        }
    }
}