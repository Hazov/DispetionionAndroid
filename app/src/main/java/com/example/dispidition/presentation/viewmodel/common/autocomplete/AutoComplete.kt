package com.example.dispidition.presentation.viewmodel.common.autocomplete

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.dispidition.presentation.viewmodel.trip.create_trip.CreateTripCargoView

class AutoComplete {
    @Composable
    inline fun <reified T : AutoCompletable> AutoCompleteTextField(ac: AutoCompleteModel<T>, textFieldName: String, placeholder: String) {
        val focusManager = LocalFocusManager.current
        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            Text(modifier = Modifier.padding(bottom = 5.dp), text = textFieldName)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusEvent({ event ->
                        ac.isExpandedCargoAutoComplete.value = event.isFocused
                    }),
                value = if (!ac.isExpandedCargoAutoComplete.value) ac.choice?.getText()
                    .orEmpty() else ac.dropDownSearchLine.value,
                onValueChange = {
                    ac.dropDownSearchLine.value = it
                    ac.isExpandedCargoAutoComplete.value = true
                },


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
                                val newCreateTripCargoView = ac.createInstance<CreateTripCargoView>(ac.dropDownSearchLine.value)
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