package com.masai.sainath.mycontactlist_daytona.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * this class can show a pure example for how concise kotlin is campaerd to java
 */
@Entity(tableName = "Contact")
data class ContactEntity (
    @ColumnInfo(name = "Firstname") var firstName : String,
    @ColumnInfo(name = "LastName") var lastName: String,
    @ColumnInfo(name = "PhNo")  var PhNo : String
                          )
{
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id : Int? = null
}