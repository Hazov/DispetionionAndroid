package main.java.com.example.ui.create

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CreateUI {
    @Composable
    fun CreateButton(onClick: () -> Unit,){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1ae50d)),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp),
                onClick = {onClick()}) {
                Text("Создать")
            }
        }
    }

    @Composable
    fun FieldInCreateCard(fieldName: String, placeholder: String = fieldName, state: MutableState<String>){
        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            Text(modifier = Modifier.padding(bottom = 5.dp), text = fieldName)
            TextField(
                modifier = Modifier.fillMaxWidth().border(width = 1.dp, color = Color.Black, shape = RectangleShape),
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White),
                value = state.value,
                onValueChange = { state.value = it },
                label = { Text (text = placeholder) })
        }
    }

    @Composable
    fun CreateCard(cardHeader: String? = null, content: @Composable ColumnScope.() -> Unit){

        Card(
            modifier = Modifier
                .fillMaxWidth().padding(bottom = 15.dp),
            elevation = CardDefaults.cardElevation(
                3.dp
            ),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            if(cardHeader != null){
                Row(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), horizontalArrangement = Arrangement.Center) {
                    Text(fontSize = 18.sp, text = cardHeader)
                }
            }
            Column(
                Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
    @Composable
    fun Header(headerText: String){
        Row(modifier = Modifier.fillMaxWidth().background(color = Color.White), horizontalArrangement = Arrangement.Center) {
            Text(modifier = Modifier.padding(15.dp), text = headerText, fontSize = 30.sp)
        }
    }
    @Composable
    fun ScreenContainer(content: @Composable ColumnScope.() -> Unit){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            content()
        }
    }


    @Composable
    fun CreateIcon(@DrawableRes iconResource: Int, onClick: () -> Unit,){
        Box(contentAlignment = Alignment.BottomEnd) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(modifier = Modifier
                    .padding(end = 20.dp, bottom = 15.dp)
                    .size(50.dp), onClick = { onClick() }) {
                    Icon(
                        painter = painterResource(iconResource),
                        contentDescription = "добавить",
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color.White, shape = CircleShape)
                            .border(4.dp, Color.White, CircleShape),
                        tint = Color.Green
                    )
                }
            }
        }
    }
}