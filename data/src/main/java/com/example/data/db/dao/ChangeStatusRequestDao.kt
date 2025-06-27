package com.example.data.db.dao

import androidx.room.*
import com.example.data.db.entity.ChangeStatusRequestRoom

@Dao
interface ChangeStatusRequestDao {

    // Метод для вставки нового запроса
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(request: ChangeStatusRequestRoom)

    // Выборка последнего запроса конкретного пользователя
    @Query("SELECT * FROM change_status_requests WHERE userId=:userId ORDER BY id DESC LIMIT 1")
    fun findLastByUser(userId: Long): ChangeStatusRequestRoom?
}