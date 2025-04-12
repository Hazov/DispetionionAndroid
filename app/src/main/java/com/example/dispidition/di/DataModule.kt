package com.example.dispidition.di

import android.content.Context
import com.example.data.auth.AuthInterceptor
import com.example.data.auth.PermissionsManager
import com.example.data.auth.TokenManager
import com.example.data.repo.AuthRepositoryImpl
import com.example.data.repo.PersonRepositoryImpl
import com.example.data.repo.TripRepositoryImpl
import com.example.data.repo.TruckRepositoryImpl
import com.example.data.storage.AuthStorage
import com.example.data.storage.PersonStorage
import com.example.data.storage.TripStorage
import com.example.data.storage.TruckStorage
import com.example.data.storage.server.ServerAuthStorage
import com.example.data.storage.server.ServerPersonStorage
import com.example.data.storage.server.ServerTripStorage
import com.example.data.storage.server.ServerTruckStorage
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.PersonRepository
import com.example.domain.repository.TripRepository
import com.example.domain.repository.TruckRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.internal.connection.ConnectInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideTruckRepository(truckStorage: TruckStorage): TruckRepository {
        return TruckRepositoryImpl(truckStorage)
    }

    @Provides
    @Singleton
    fun provideTripRepository(tripStorage: TripStorage): TripRepository {
        return TripRepositoryImpl(tripStorage)
    }

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun providePermissionsManager(@ApplicationContext context: Context): PermissionsManager = PermissionsManager(context)

    @Provides
    @Singleton
    fun providePersonRepository(personStorage: PersonStorage): PersonRepository {
        return PersonRepositoryImpl(personStorage)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authStorage: AuthStorage, tokenManager: TokenManager, permissionsManager:PermissionsManager): AuthRepository {
        return AuthRepositoryImpl(authStorage, tokenManager, permissionsManager)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
        return client
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor {
        return AuthInterceptor(tokenManager)
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.56.1:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideServerPersonStorage(retrofit: Retrofit): PersonStorage {
        return retrofit.create(ServerPersonStorage::class.java)
    }

    @Provides
    @Singleton
    fun provideServerAuthStorage(retrofit: Retrofit): AuthStorage {
        return retrofit.create(ServerAuthStorage::class.java)
    }

    @Provides
    @Singleton
    fun provideServerTripStorage(retrofit: Retrofit): TripStorage {
        return retrofit.create(ServerTripStorage::class.java)
    }

    @Provides
    @Singleton
    fun provideServerTruckStorage(retrofit: Retrofit): TruckStorage {
        return retrofit.create(ServerTruckStorage::class.java)
    }
}