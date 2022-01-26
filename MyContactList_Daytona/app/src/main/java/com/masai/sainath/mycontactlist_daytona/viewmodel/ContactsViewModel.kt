package com.masai.sainath.mycontactlist_daytona.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.masai.sainath.mycontactlist_daytona.database.ContactsDatabase
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity
import com.masai.sainath.mycontactlist_daytona.repo.ContactsRepo

class ContactsViewModel(application: Application): AndroidViewModel(application) {



    private val repository: ContactsRepo

    init {
        val dao= ContactsDatabase.getDatabase(application).getContactsDao()
        repository= ContactsRepo(dao)
    }

    fun addContacts(contacts:ContactEntity){
        repository.inserNotes(contacts)
    }
    fun getContacts(): LiveData<List<ContactEntity>> = repository.getAllContacts()




    fun deleteContacts(contacts:ContactEntity){
        repository.deleteNotes(contacts)
    }
    fun updateContacts(contacts:ContactEntity){
        repository.updateNotes(contacts)

    }
}