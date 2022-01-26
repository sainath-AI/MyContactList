package com.masai.sainath.mycontactlist_daytona.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.masai.sainath.mycontactlist_daytona.R
import com.masai.sainath.mycontactlist_daytona.databinding.ActivityInsertContactsBinding
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity
import com.masai.sainath.mycontactlist_daytona.viewmodel.ContactsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertContacts : AppCompatActivity() {

    lateinit var binding: ActivityInsertContactsBinding
    val viewModel: ContactsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            createContacts(it)
        }

    }

    private fun createContacts(it: View?) {

        val firstName=binding.firstname.text.toString()
        val lastName=binding.lastname.text.toString()
        val PhNo=binding.phNumber.text.toString()

        val contactEntity=ContactEntity(firstName,lastName,PhNo)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.addContacts(contactEntity)
        }
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()



    }
}