package com.example.dispidition.presentation.screens.person

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.app.global.GlobalSettings
import com.example.dispidition.presentation.viewmodel.person.PersonDetailsViewModel
import com.example.ui.details.DetailsUI
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class PersonDetailsScreen (val detailsUI: DetailsUI, val globalSettings: GlobalSettings, val navController: NavHostController) {
    val formatter = DateTimeFormatter.ofPattern("dd.MM")

    @Composable
    fun Init(id: Long?, vm: PersonDetailsViewModel = hiltViewModel()) {
        if(!globalSettings.authenticated.value){
            navController.navigate("login")
        }
        if(id != null){
            vm.fetchPerson(id)
            Show(vm);
        }
    }

    @Composable
    fun Show(vm: PersonDetailsViewModel) {
        
        val person = vm.person.observeAsState().value

        detailsUI.DetailsContainer{
            detailsUI.Header("Информация о пользователе")
            detailsUI.DetailsCard{
                detailsUI.DetailsPairRow("Фамилия", person?.lastName.orEmpty())
                detailsUI.DetailsPairRow("Имя", person?.firstName.orEmpty())
                detailsUI.DetailsPairRow("Отчество", person?.middleName.orEmpty())
                detailsUI.DetailsPairRow("Email", person?.email.orEmpty())
                detailsUI.DetailsPairRow("Дата регистрации",
                    formatter.format(
                        person?.registrationDate?.toInstant()?.atOffset(ZoneOffset.UTC)
                            ?.toLocalDateTime()
                    )
                    )
                detailsUI.DetailsPairRow("Компания", person?.company?.name.orEmpty())
                detailsUI.DetailsPairRow("Должность", person?.position.orEmpty())
                detailsUI.DetailsPairRow("Статус", if(person?.isFired == true) "Уволен" else "Активен")
            }
        }

    }
}