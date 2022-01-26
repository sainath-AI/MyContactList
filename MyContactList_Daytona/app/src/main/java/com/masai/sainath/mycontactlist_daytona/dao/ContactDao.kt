package com.masai.sainath.mycontactlist_daytona.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity


/**
 * With the help of these annotations  and 'Kapt' compiler added in my gradle file ,
 * the build System will automaticlly generate Database file Required
 * in Accordance to 'queries ' and annotations provided in DAO interface
 */
@Dao
interface ContactDao {


    /**
     * In this below Function With the help of Insert Annotation ,
     * i will be able to insert the data according to table,
     * i have created in entity class
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun AddContacts(contactEntity: ContactEntity)

    /**
     * This function observes my list of data present in Room database
     * with the help of liveData -- which is observable data holder class , it is also part architectural component
     */
    @Query("Select * from contact")
    fun getContacts(): LiveData<List<ContactEntity>>


    /**
     * this function is used to update the changes made by user Database
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun UpdateContacts(contactEntity: ContactEntity)

    /**
     * this function is used to delete the coloumn from table  Database
     */

    @Query("DELETE FROM Contact WHERE id=:id")
    fun DeleteContacts(id: Int)
}