package com.example.ui.registry

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class RegistryUI {
    @Composable
    fun RegistryContainer(content: @Composable LazyItemScope.() -> Unit) {
        LazyColumn(
            Modifier
                .padding(bottom = 50.dp)
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
        ) {
            item() {
                content()
            }
        }
    }

    @Composable
    fun RegistryCard(
        onClick: () -> Unit,
        @DrawableRes avatarResource: Int? = null,
        info: (@Composable () -> Unit),


        ) {
        Card(
            modifier = Modifier
                .padding(vertical = 15.dp),
            elevation = CardDefaults.cardElevation(
                15.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
            onClick = { onClick() }

        ) {

            Row(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (avatarResource != null) {
                    Avatar(avatarResource)
                }

                Info { info() }

                SideActions()

            }
        }
    }

    @Composable
    fun Avatar(@DrawableRes resource: Int) {
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Image(
                painter = painterResource(resource),
                "avatar",
                Modifier.size(90.dp)
            )
        }
    }

    @Composable
    fun SideActions() {
        IconButton(
            onClick = {},
            Modifier.size(30.dp, 30.dp)
        ) {
            Icon(Icons.Filled.MoreVert, "Действия")
        }
    }

    @Composable
    fun Info(content: @Composable ColumnScope.() -> Unit) {
        Column {
            content()
        }
    }
}
