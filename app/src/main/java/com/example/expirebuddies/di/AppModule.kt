package com.example.expirebuddies.di

import android.content.Context
import androidx.room.Room
import com.example.expirebuddies.ExpireBuddiesApp
import com.example.expirebuddies.model.database.FoodDatabase
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation
import com.example.expirebuddies.model.usecases.*
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideLocalDatabase(@ApplicationContext context: Context):FoodDatabase{
        return Room.databaseBuilder(context,FoodDatabase::class.java,FoodDatabase.DATABASE_NAME).build()
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
            addFood = AddFood(repositoryImplementation),
            getFood = GetFood(repositoryImplementation)
        )
    }



}