package com.masai.sainath.mycontactlist_daytona.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masai.sainath.mycontactlist_daytona.dao.ContactDao
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity

@Database(entities = [ContactEntity::class], version = 2)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun getContactsDao() : ContactDao


    companion object{
        @Volatile
        var INSTANCE: ContactsDatabase? = null

        fun getDatabaseInstances(context: Context): ContactsDatabase {
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance= Room.databaseBuilder(context,
                    ContactsDatabase::class.java,
                    "Contacts").allowMainThreadQueries().build()
                INSTANCE=roomDatabaseInstance
                return  roomDatabaseInstance
            }
        }

    }

}