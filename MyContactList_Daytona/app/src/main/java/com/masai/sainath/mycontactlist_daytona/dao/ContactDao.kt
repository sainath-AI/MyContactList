package com.masai.sainath.mycontactlist_daytona.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun AddContacts(contactEntity: ContactEntity)

    @Query("Select * from contact")
    fun getContacts(): LiveData<List<ContactEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun UpdateContacts(contactEntity: ContactEntity)


    @Query("DELETE FROM Contact WHERE id=:id")
    fun DeleteContacts(id: Int)
}