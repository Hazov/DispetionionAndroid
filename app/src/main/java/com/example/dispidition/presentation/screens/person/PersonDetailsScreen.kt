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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.time.LocalDate

class PersonDetailsScreen(val navController: NavHostController) {
    val person  = PersonDetails("Олег", "Хромоногий", "Вячеславович", LocalDate.now(), "chromonog@mail.ru",
        PersonDetailsCompany("ООО Жопа"), "Водитель", false)
    @Composable
    fun Show() {
        Column(Modifier.padding(top = 150.dp)) {
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
                    Text(fontSize = 17.sp, text = person.lastName);
                }
                //Имя
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Имя");
                    Text(fontSize = 17.sp, text = person.firstName);
                }
                //Отчество
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Отчество");
                    Text(fontSize = 17.sp, text = person.middleName);
                }
                //Email
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Email")
                    Text(fontSize = 17.sp, text = person.email);

                }
                //Дата регистрации
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Дата регистрации")
                    Text(fontSize = 17.sp, text = person.registrationDate.toString());
                }
                //Компания
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Компания")
                    Text(fontSize = 17.sp, text = person.company.name);
                }
                //Должность
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Должность")
                    Text(fontSize = 17.sp, text = person.position);
                }
                //Статус
                Row(Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Статус")
                    Text(fontSize = 17.sp, text = person.isFired.toString());
                }
            }
        }

    }
}

data class PersonDetails(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val registrationDate: LocalDate,
    val email: String,
    val company: PersonDetailsCompany,
    val position: String,
    val isFired: Boolean
)

data class PersonDetailsCompany(val name: String)