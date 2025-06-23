package com.example.dispidition.presentation.screens.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.person.PersonsRegistryViewModel
import com.example.dispidition.R
import com.example.dispidition.app.global.GlobalSettings
import com.example.domain.model.person.registry.RegistryPerson
import com.example.ui.registry.RegistryUI
import main.java.com.example.ui.create.CreateUI

class PersonsRegistryScreen(
    val createUI: CreateUI,
    val registryUI: RegistryUI,
    val globalSettings: GlobalSettings,
    val navController: NavHostController
) {

    @Composable
    fun Init(vm: PersonsRegistryViewModel = hiltViewModel()) {
        if(!globalSettings.authenticated.value){
            navController.navigate("login")
        }
        vm.fetchPersons()
        Show(vm);
    }


    @Composable
    fun Show(vm: PersonsRegistryViewModel) {

        val persons = vm.persons.observeAsState().value

        if (persons != null) {

            registryUI.RegistryContainer {
                Column {
                    for (person in persons) {
                        registryUI.RegistryCard(
                            onClick = { navController.navigate("person/${person.id}") },
                            avatarResource = R.drawable.personavatar
                        ) {
                            Info(person)
                        }
                    }
                }
            }

            createUI.CreateIcon(R.drawable.addicon) { navController.navigate("createPerson") }
        }
    }

    @Composable
    fun Info(person: RegistryPerson) {
        Column {
            Row {
                Text(text = person.lastName.orEmpty() + " " + person.firstName.orEmpty())
            }
            Row {
                Text(text = person.position.orEmpty())
            }
        }
    }
}