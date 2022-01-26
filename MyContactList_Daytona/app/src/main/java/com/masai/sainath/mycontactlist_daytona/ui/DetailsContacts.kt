package com.masai.sainath.mycontactlist_daytona.ui

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.masai.sainath.mycontactlist_daytona.R
import com.masai.sainath.mycontactlist_daytona.databinding.ActivityDetailsContactsBinding
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity
import com.masai.sainath.mycontactlist_daytona.viewmodel.ContactsViewModel

class DetailsContacts : AppCompatActivity() {
    lateinit var binding: ActivityDetailsContactsBinding
    val viewModel: ContactsViewModel by viewModels()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val iid=intent.getIntExtra("id",0)
        val First=intent.getStringExtra("firstname")
        val Last=intent.getStringExtra("lastname")
        val PHNo=intent.getStringExtra("phno")

        binding.ContactName.text= First.toString() +" "+ Last.toString()
        binding.firstname.setText(First)
        binding.lastname.setText(Last)
        binding.phNumber.setText(PHNo)

        binding.btnSave.setOnClickListener {
            val uFirstName=binding.firstname.text.toString()
            val uLastName=binding.lastname.text.toString()
            val uPhNo=binding.phNumber.text.toString()
            UpdateContacts(uFirstName,uLastName,uPhNo)
        }







    }

    private fun UpdateContacts(uFirstName: String, uLastName: String, uPhNo: String) {
        val updateContacts = ContactEntity(uFirstName,uLastName,uPhNo)
        updateContacts.firstName = uFirstName
        updateContacts.lastName = uLastName
        updateContacts.PhNo = uPhNo
        viewModel.updateContacts(updateContacts)
        val snack = Snackbar.make(View(this@DetailsContacts),"Contacts Updated Successfully",Snackbar.LENGTH_LONG)
        snack.show()
        finish()
    }

}