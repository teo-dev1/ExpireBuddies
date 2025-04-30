package com.example.expirebuddies.di

import androidx.room.Room
import com.example.expirebuddies.ExpireBuddiesApp
import com.example.expirebuddies.model.database.FoodDatabase
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation
import com.example.expirebuddies.model.usecases.AddFood
import com.example.expirebuddies.model.usecases.DeleteFood
import com.example.expirebuddies.model.usecases.FoodManipulationUseCases
import com.example.expirebuddies.model.usecases.GetFoods
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(app:ExpireBuddiesApp):FoodDatabase{
        return Room.databaseBuilder(app,FoodDatabase::class.java,FoodDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(database:FoodDatabase):LocalRepositoryImplementation{
        return LocalRepositoryImplementation(database.foodDao)
    }

    @Provides
    @Singleton
    fun provideFoodManipulationUseCases(repositoryImplementation: LocalRepositoryImplementation):FoodManipulationUseCases{
        return FoodManipulationUseCases(
            getFoods = GetFoods(repositoryImplementation),
            deleteFood = DeleteFood(repositoryImplementation),
            addFood = AddFood(repositoryImplementation)
        )
    }


    //TODO aggiungere i provide mancanti
    //TODO creare gli usecases e racchiuderli in una classe a seconda del contesto in cui vengono utilizzati

}