package com.example.dispidition.presentation.screens.person

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.person.PersonsRegistryViewModel
import com.example.dispidition.R
import com.example.domain.model.person.registry.RegistryPerson
import com.example.ui.registry.RegistryUI
import main.java.com.example.ui.create.CreateUI

class PersonsRegistryScreen (val createUI: CreateUI,
                             val registryUI: RegistryUI,
                             val navController: NavHostController) {

    @Composable
    fun Init(vm: PersonsRegistryViewModel = hiltViewModel()) {
        vm.fetchPersons()
        Show(vm);
    }


    @Composable
    fun Show(vm: PersonsRegistryViewModel) {
        
        val persons = vm.persons.observeAsState().value

        if (persons != null) {

            registryUI.RegistryContainer {
                Column {
                    for (person in persons){
                        registryUI.RegistryCard(onClick = {navController.navigate("person/${person.id}")},
                            avatarResource = R.drawable.personavatar
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Info(person)
                            }
                        }
                    }
                }
            }

            createUI.CreateIcon(R.drawable.addicon) {navController.navigate("createPerson") }
        }
    }

    @Composable
    fun Info(person: RegistryPerson){
        Row {
            Text(text = person.lastName.orEmpty() + " " + person.firstName.orEmpty())
        }
        Row {
            Text(text = person.position.orEmpty())
        }
    }
}