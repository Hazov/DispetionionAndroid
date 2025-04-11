package com.example.dispidition.presentation.screens.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.person.PersonDetailsViewModel
import com.example.domain.model.person.details.PersonDetailsCompany

class PersonDetailsScreen(val navController: NavHostController) {


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
        Column {
            Card(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    15.dp
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                //Фамилия
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Фамилия");
                    Text(fontSize = 17.sp, text = person?.lastName.orEmpty());
                }
                //Имя
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Имя");
                    Text(fontSize = 17.sp, text = person?.firstName.orEmpty());
                }
                //Отчество
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Отчество");
                    Text(fontSize = 17.sp, text = person?.middleName.orEmpty());
                }
                //Email
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Email")
                    Text(fontSize = 17.sp, text = person?.email.orEmpty());

                }
                //Дата регистрации
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Дата регистрации")
                    Text(fontSize = 17.sp, text = person?.registrationDate?.toString().orEmpty());
                }
                //Компания
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Компания")
                    Text(fontSize = 17.sp, text = person?.company?.name.orEmpty());
                }
                //Должность
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Должность")
                    Text(fontSize = 17.sp, text = person?.position.orEmpty());
                }
                //Статус
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Статус")
                    Text(fontSize = 17.sp, text = person?.isFired?.toString().orEmpty());
                }
            }
        }







    }
}