package com.example.expirebuddies.di

import android.content.Context
import androidx.room.Room
import com.example.expirebuddies.ExpireBuddiesApp
import com.example.expirebuddies.model.database.FoodDatabase
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation
import com.example.expirebuddies.model.remotedata.ApiSerivceImplementation
import com.example.expirebuddies.model.remotedata.ApiService
import com.example.expirebuddies.model.remotedata.remoterepository.RemoteRepository
import com.example.expirebuddies.model.usecases.*
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context):FoodDatabase{
        return Room.databaseBuilder(context,FoodDatabase::class.java,FoodDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(database:FoodDatabase):LocalRepositoryImplementation{
        return LocalRepositoryImplementation(database.foodDao)
    }


    //test
   @Provides
   @Singleton
   fun provideHttpClient():HttpClient{
       return HttpClient(OkHttp) {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = false  //formatta il json con spazi e indentatura più leggibile da umani
                        isLenient = true  //accetta anche json "malformati" dove può esserci qualche carattere in più o carattere sballato
                        ignoreUnknownKeys = true
                    })
                }

                // Impostazioni opzionali (timeout, logging, headers, ecc.)
                engine {
                    config {
                        retryOnConnectionFailure(true)
                        followRedirects(true)
                        connectTimeout(30, TimeUnit.SECONDS)
                    }
                }
            }
   }


    @Provides
    @Singleton
    fun provideRemoteRepository(
        remoteApi: ApiSerivceImplementation
    ): RemoteRepository {
        return RemoteRepository(remoteApi)
    }

    @Provides
    @Singleton
    fun provideFoodManipulationUseCases(repositoryImplementation: LocalRepositoryImplementation):FoodManipulationUseCases{
        return FoodManipulationUseCases(
            getFoods = GetFoods(repositoryImplementation),
            deleteFood = DeleteFood(repositoryImplementation),
            addFood = AddFood(repositoryImplementation),
            getFood = GetFood(repositoryImplementation)
        )
    }



}