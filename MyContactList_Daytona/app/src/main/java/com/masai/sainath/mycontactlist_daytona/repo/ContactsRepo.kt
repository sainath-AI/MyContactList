package com.masai.sainath.mycontactlist_daytona.repo

import androidx.lifecycle.LiveData
import com.masai.sainath.mycontactlist_daytona.dao.ContactDao
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity

class ContactsRepo(val contactsDao:ContactDao) {
    fun getAllNotes(): LiveData<List<ContactEntity>> = contactsDao.getContacts()


    fun inserNotes(contacts: ContactEntity){
        contactsDao.AddContacts(contacts)
    }
    fun deleteNotes(contacts:ContactEntity){
        contactsDao.DeleteContacts(contacts)
    }
    fun updateNotes(contacts:ContactEntity){
        contactsDao.UpdateContacts(contacts)
    }

}