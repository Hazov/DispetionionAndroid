package com.example.dispidition.presentation.screens.truck

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dispidition.R
import com.example.domain.model.truck.RegistryTruck

class TrucksRegistryScreen(val navController: NavHostController) {
lateinit var trucks: List<RegistryTruck>;

    @Composable
    fun Show(modifier: Modifier = Modifier) {

//        Image(
//            painter = painterResource(R.drawable.img_4),
//            "bg",
//            Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//
//
//        )
//        Column {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 50.dp)
//            ) {
//                items(trucks) { truck ->
//                    val stateColor = remember {
//                        mutableStateOf(Color.Black)
//                    }
////                    if (truck.status.equals("Активна")) {
////                        stateColor.value = Color(0xff57b44e);
////                    } else if (truck.status.equals("Ремонт")) {
////                        stateColor.value = Color(0xffff0041);
////                    } else {
////                        stateColor.value = Color(0xffb2b2b2);
////
////                    }
//                    Card(
//                        modifier = Modifier
//                            .padding(15.dp)
//                            .defaultMinSize(minHeight = 110.dp)
//                            .fillMaxWidth()
//                        ,
//                        elevation = CardDefaults.cardElevation(
//                            15.dp
//                        ),
//                        shape = RoundedCornerShape(10.dp),
//                        onClick = { navController.navigate("truck") }
//
//                    ) {
//
//                        Row(
//                            modifier.fillMaxSize(),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Start
//                        ) {
//                            Row(
//                                modifier = Modifier
//                                    .defaultMinSize(minHeight = 110.dp)
//                                    .fillMaxHeight()
//                                    .width(7.dp)
//                                    .background(stateColor.value)
//                            ) {
//
//                            }
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//                                modifier = modifier
//                                    .fillMaxSize()
//                                    .padding(5.dp),
//                                horizontalArrangement = Arrangement.SpaceBetween
//                            ) {
//                                Card(
//                                    shape = RoundedCornerShape(5.dp),
//                                    modifier = Modifier.padding(start = 10.dp)
//                                ) {
//                                    Image(
//                                        painter = painterResource(id = R.drawable.image),
//                                        "sdf",
//                                        modifier.size(90.dp)
//                                    )
//                                }
//                                Column() {
//                                    Text(
//                                        fontSize = 21.sp,
//                                        text = truck.brand + " " + truck.model,
//                                        modifier = modifier
//                                    )
//                                    Row {
//                                        Text(
//                                            text = "Статус: "
//                                        )
////                                        Text(
////                                            text = truck.status,
////                                            color = stateColor.value
////                                        )
//                                    }
//
//
//                                }
//                                IconButton(
//                                    onClick = {},
//                                    Modifier.size(30.dp, 30.dp)
//                                ) {
//                                    Icon(Icons.Filled.MoreVert, "Действия")
//                                }
//
//                            }
//                        }
//
//
//                    }
//                }
//
//            }
//            Row(Modifier.padding(bottom = 50.dp)) {
//                IconButton(onClick = {}) {
//                    Icon(painter = painterResource(R.drawable.addicon), "Добавить")
//                }
//            }
//        }
//
    }
}

