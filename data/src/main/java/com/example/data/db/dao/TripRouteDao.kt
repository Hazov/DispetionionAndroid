package com.example.data.db.dao

import androidx.room.*;

@Dao
interface TripRouteDao {
    // Сохранение нового маршрута с точками
    @Transaction
    suspend fun saveTripRoute(route: TripRoom) {
        // Сначала сохраняем маршрут
        val insertedRouteId = insertRoute(route)

        // После успешного сохранения маршрута, вставляем точки маршрута
        route.points.forEach { point ->
            point.route_id = insertedRouteId
            insertPoint(point)
        }
    }

    // Запись основного маршрута
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: TripRoom): Long

    // Запись отдельной точки маршрута
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoint(point: TripPointRoom): Long

    // Чтение маршрута с точками
    @Transaction
    @Query("SELECT * FROM trip_routes WHERE id = :routeId")
    suspend fun getTripRouteWithPoints(routeId: Long): TripRoom?

    // Удаление маршрута и всех его точек
    @Transaction
    suspend fun deleteTripRoute(route: TripRoom) {
        deletePoints(route.points)
        deleteRoute(route)
    }

    // Удаление конкретного маршрута
    @Delete
    suspend fun deleteRoute(route: TripRoom)

    // Удаление конкретных точек маршрута
    @Delete
    suspend fun deletePoints(points: List<TripPointRoom>)
}