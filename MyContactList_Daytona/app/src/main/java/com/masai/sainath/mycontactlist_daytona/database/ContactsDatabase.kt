package com.masai.sainath.mycontactlist_daytona.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masai.sainath.mycontactlist_daytona.dao.ContactDao
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity


/**
 * This is abstract class used to initialize our room database
 * some of the advantages of this class is , it provides us way to write different versions for our table  inserted ,
 * so if any changes in table  made , can easily counter with version change
 */

@Database(entities = [ContactEntity::class], version = 2)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun getContactsDao() : ContactDao


    companion object{
        /**
         * the writes to this field are immediately made visible to other threads. and the reads will always see the latest changes.
         */
        @Volatile
        var INSTANCE: ContactsDatabase? = null

        fun getDatabaseInstances(context: Context): ContactsDatabase {
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            /**
             * synchronized
             *  This is to control different threads accessing the database at once, to prevent multiple instances being created
             */
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