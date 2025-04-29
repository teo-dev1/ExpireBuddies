package com.example.expirebuddies.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expirebuddies.ExpireBuddiesApp
import com.example.expirebuddies.model.database.FoodDao
import com.example.expirebuddies.model.database.FoodDatabase
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    //TODO aggiungere i provide mancanti
    //TODO creare gli usecases e racchiuderli in una classe a seconda del contesto in cui vengono utilizzati

}