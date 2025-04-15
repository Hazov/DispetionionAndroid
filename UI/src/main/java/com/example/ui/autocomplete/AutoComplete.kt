package com.example.ui.autocomplete

import androidx.annotation.Nullable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import kotlin.collections.contains
import kotlin.collections.isNotEmpty
import kotlin.jvm.java
import kotlin.text.isNotEmpty
import kotlin.text.orEmpty

class AutoComplete {
    @Composable
    inline fun <reified T : AutoCompletable>  AutoCompleteTextField(ac: AutoCompleteModel<T>, crossinline createInstance: (params: String) -> Any = {}, textFieldName: String, placeholder: String) {
        val focusManager = LocalFocusManager.current
        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            Text(modifier = Modifier.padding(bottom = 5.dp), text = textFieldName)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Black, shape = RectangleShape)
                    .onFocusEvent({ event ->
                        ac.isExpandedCargoAutoComplete.value = event.isFocused
                    }),
                value = if (!ac.isExpandedCargoAutoComplete.value) ac.choice?.getText()
                    .orEmpty() else ac.dropDownSearchLine.value,
                onValueChange = {
                    ac.dropDownSearchLine.value = it
                    ac.isExpandedCargoAutoComplete.value = true
                },
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White),


                label = { Text(text = placeholder) })
        }
        Box {
            DropdownMenu(
                expanded = ac.isExpandedCargoAutoComplete.value &&
                        (ac.dropDownSearchLine.value.isNotEmpty() || ac.listModels.isNotEmpty()),
                onDismissRequest = {
                    closeAndClearData(ac, focusManager)
                },
                properties = PopupProperties(focusable = false)
            ) {
                Column {
                    if (T::class.java.interfaces.contains(Instantiatable::class.java)
                        && ac.dropDownSearchLine.value.isNotEmpty()) {
                        DropdownMenuItem(
                            onClick = {
                                val newCreateTripCargoView = createInstance(ac.dropDownSearchLine.value)
                                ac.choice = newCreateTripCargoView as T
                                ac.listModels.add(newCreateTripCargoView)
                                closeAndClearData(ac, focusManager)
                            },
                            text = { Text(text = ac.dropDownSearchLine.value) }
                        )
                    }
                    for (model in ac.listModels) {
                        DropdownMenuItem(
                            onClick = {
                                ac.dropDownSearchLine.value = ""
                                ac.choice = model as T?
                                closeAndClearData(ac, focusManager)
                            },
                            text = { Text(model.getText()) }
                        )
                    }

                }
            }
        }

    }

    fun <T : AutoCompletable> closeAndClearData(
        ac: AutoCompleteModel<T>,
        focusManager: FocusManager
    ) {
        ac.isExpandedCargoAutoComplete.value = false
        ac.dropDownSearchLine.value = ""
        focusManager.clearFocus()
    }

}