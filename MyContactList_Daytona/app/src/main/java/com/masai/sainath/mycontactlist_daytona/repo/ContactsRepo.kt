package com.masai.sainath.mycontactlist_daytona.repo

import androidx.lifecycle.LiveData
import com.masai.sainath.mycontactlist_daytona.dao.ContactDao
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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