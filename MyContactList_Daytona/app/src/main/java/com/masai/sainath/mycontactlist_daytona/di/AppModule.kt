package com.masai.sainath.mycontactlist_daytona.di

import android.content.Context
import androidx.room.Room
import com.masai.sainath.mycontactlist_daytona.database.ContactsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): ContactsDatabase {
        val builder = Room.databaseBuilder(
            context, ContactsDatabase::class.java, "contacts_db"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }
}