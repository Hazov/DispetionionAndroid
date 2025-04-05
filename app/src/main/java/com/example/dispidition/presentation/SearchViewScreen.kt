//package com.example.dispidition.presentation
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.SegmentedButtonDefaults.Icon
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.unit.sp
//import java.lang.reflect.Modifier
//import java.time.format.TextStyle
//
//class SearchViewScreen {
//    @Composable
//    fun SearchView(state: MutableState<TextFieldValue>) {
//        TextField(
//            value = state.value,
//            onValueChange = { value ->
//                state.value = value
//            },
//            modifier = Modifier.fillMaxWidth(),
//            textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
//            leadingIcon = {
//                Icon(
//                    Icons.Default.Search,
//                    contentDescription = "",
//                    modifier = Modifier
//                        .padding(15.dp)
//                        .size(24.dp)
//                )
//            },
//            trailingIcon = {
//                if (state.value != TextFieldValue("")) {
//                    IconButton(
//                        onClick = {
//                            state.value =
//                                TextFieldValue("") // Remove text from TextField when you press the 'X' icon
//                        }
//                    ) {
//                        Icon(
//                            Icons.Default.Close,
//                            contentDescription = "",
//                            modifier = Modifier
//                                .padding(15.dp)
//                                .size(24.dp)
//                        )
//                    }
//                }
//            },
//            singleLine = true,
//            shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
//            colors = TextFieldDefaults.textFieldColors(
//                textColor = Color.White,
//                cursorColor = Color.White,
//                leadingIconColor = Color.White,
//                trailingIconColor = Color.White,
//                backgroundColor = MaterialTheme.colors.primary,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                disabledIndicatorColor = Color.Transparent
//            )
//        )
//    }
//
//
//
//
//    @Composable
//    fun ItemList(state: MutableState<TextFieldValue>) {
//        val items by remember { mutableStateOf(listOf("Drink water", "Walk")) }
//
//        var filteredItems: List<String>
//        LazyColumn(modifier = Modifier.fillMaxWidth()) {
//            val searchedText = state.value.text
//            filteredItems = if (searchedText.isEmpty()) {
//                items
//            } else {
//                val resultList = ArrayList<String>()
//                for (item in items) {
//                    if (item.lowercase(Locale.getDefault())
//                            .contains(searchedText.lowercase(Locale.getDefault()))
//                    ) {
//                        resultList.add(item)
//                    }
//                }
//                resultList
//            }
//            items(filteredItems) { filteredItem ->
//                ItemListItem(
//                    ItemText = filteredItem,
//                    onItemClick = { /*Click event code needs to be implement*/
//                    }
//                )
//            }
//
//        }
//    }
//
//
//
//    @Composable
//    fun ItemListItem(ItemText: String, onItemClick: (String) -> Unit) {
//        Row(
//            modifier = Modifier
//                .clickable(onClick = { onItemClick(ItemText) })
//                .background(colorResource(id = R.color.purple_700))
//                .height(57.dp)
//                .fillMaxWidth()
//                .padding(PaddingValues(8.dp, 16.dp))
//        ) {
//            Text(text = ItemText, fontSize = 18.sp, color = Color.White)
//        }
//    }
//}