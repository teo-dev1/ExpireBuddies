package com.example.expirebuddies.di

import android.content.Context
import androidx.room.Room
import com.example.expirebuddies.ExpireBuddiesApp
import com.example.expirebuddies.model.authentication.*
import com.example.expirebuddies.model.database.FoodDatabase
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation
import com.example.expirebuddies.model.remotedata.ApiSerivceImplementation
import com.example.expirebuddies.model.remotedata.ApiService
import com.example.expirebuddies.model.remotedata.remoterepository.IRemoteRepository
import com.example.expirebuddies.model.remotedata.remoterepository.IUserRemoteRepository
import com.example.expirebuddies.model.remotedata.remoterepository.RemoteRepository
import com.example.expirebuddies.model.remotedata.remoterepository.UserRemoteRepository
import com.example.expirebuddies.model.remotedata.remoterepository.remote_usecases.*
import com.example.expirebuddies.model.usecases.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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

    @Provides
    @Singleton
    fun provideRemoteUseCases(repo: IUserRemoteRepository):RemoteUseCases{
        return RemoteUseCases(createUser = CreateUser(repo),
            /*deleteUser = DeleteUser(/*gestor*/),
            updateUser = UpdateUser(/*gestor*/),*/
            login = Login(repo)

        )
    }

    @Provides
    @Singleton
    fun provideUserRemoteRepository(gestor: IUserGestor):IUserRemoteRepository{
        return UserRemoteRepository(gestor)
    }

    @Provides
    @Singleton
    fun provideUserGestor(authService:IAuthService,remoteSource:IUserRemoteDataSource):IUserGestor{
        return UserGestor(
            authService,
            remoteSource
        )
    }


    @Provides
    @Singleton
    fun provideAuthService(auth:FirebaseAuth): IAuthService {
        return FirebaseAuthService(auth)
    }  //creare implementazione authservice se non c'è o bind nel caso non si voglia usare l'implementazione
    @Provides
    @Singleton
    fun provideFirestoreRemoteDataSource(firestore:FirebaseFirestore):IUserRemoteDataSource{
        return FirestoreRemoteDataSource(firestore)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }


}