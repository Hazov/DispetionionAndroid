package com.example.dispidition.presentation.screens.person

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.person.PersonDetailsViewModel
import com.example.ui.details.DetailsUI


class PersonDetailsScreen (val detailsUI: DetailsUI, val navController: NavHostController) {


    @Composable
    fun Init(id: Long?, vm: PersonDetailsViewModel = hiltViewModel()) {
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
                detailsUI.DetailsPairRow("Дата регистрации", person?.registrationDate?.toString().orEmpty())
                detailsUI.DetailsPairRow("Компания", person?.company?.name.orEmpty())
                detailsUI.DetailsPairRow("Должность", person?.position.orEmpty())
                detailsUI.DetailsPairRow("Статус", person?.isFired?.toString().orEmpty())
            }
        }

    }
}