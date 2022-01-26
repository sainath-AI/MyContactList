package com.masai.sainath.mycontactlist_daytona.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masai.sainath.mycontactlist_daytona.dao.ContactDao
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity

@Database(entities = [ContactEntity::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun getContactsDao() : ContactDao


    companion object {

        private  var INSTANCE : ContactsDatabase?=null


        fun getDatabase(context : Context) : ContactsDatabase{


            if(INSTANCE == null){

                val builder = Room.databaseBuilder(
                    context.applicationContext,ContactsDatabase::class.java,
                    "contacts_db")

                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()

                return INSTANCE!!
            }else{

                return INSTANCE!!
            }


        }
    }

}