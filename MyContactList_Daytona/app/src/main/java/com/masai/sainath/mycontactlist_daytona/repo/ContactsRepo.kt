package com.masai.sainath.mycontactlist_daytona.repo

import androidx.lifecycle.LiveData
import com.masai.sainath.mycontactlist_daytona.dao.ContactDao
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * While implementing mvVm archietecture it is important to create a repo Class ,
 * where all business logic will be written and
 * seperating  Ui and Huge Asynchronous tasks help in not Freezing our application
 */

class ContactsRepo(val contactsDao:ContactDao) {

    fun getAllContacts(): LiveData<List<ContactEntity>> = contactsDao.getContacts()


    fun inserNotes(contacts: ContactEntity){
        contactsDao.AddContacts(contacts)
    }
    fun deleteNotes(id:Int){
        contactsDao.DeleteContacts(id)
    }
    fun updateContacts(contacts:ContactEntity) {

            contactsDao.UpdateContacts(contacts)

    }
}