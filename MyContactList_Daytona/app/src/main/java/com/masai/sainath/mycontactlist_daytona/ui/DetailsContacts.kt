package com.masai.sainath.mycontactlist_daytona.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.masai.sainath.mycontactlist_daytona.R
import com.masai.sainath.mycontactlist_daytona.databinding.ActivityDetailsContactsBinding
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity
import com.masai.sainath.mycontactlist_daytona.viewmodel.ContactsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DetailsContacts : AppCompatActivity() {
    lateinit var binding: ActivityDetailsContactsBinding
    val viewModel: ContactsViewModel by viewModels()
   // lateinit var contactEntity: ContactEntity


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
            val firstName: String = binding.firstname.getText().toString()
            val lastName: String = binding.lastname.getText().toString()
            val PhNo: String = binding.phNumber.getText().toString()
            UpdateContacts(firstName, lastName, PhNo)
        }
        binding.delete.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("are you sure want to delete this item")
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, _ ->

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.deleteContacts(iid)
                }
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                dialog.dismiss()

            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
            dialog.show()
        }



    }

    private fun UpdateContacts( firstName: String,  lastName:String,  PhNo:String) {

        val updateContact = ContactEntity(firstName=firstName,lastName=lastName,PhNo=PhNo)
        updateContact.firstName = firstName
        updateContact.lastName = lastName
        updateContact.PhNo = PhNo
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.updateContacts(updateContact)
        }
        if(isCredentialsValid()) {
            val intent = Intent(this, MainActivity::class.java)
                    Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
        }


//        val first= binding.firstname.text.toString()
//        val last= binding.lastname.text.toString()
//        val phNo= binding.phNumber.text.toString()
//        val contactEntity= ContactEntity(firstName = first, lastName = last, PhNo =phNo)
//                CoroutineScope(Dispatchers.IO).launch {
//                    viewModel.updateContacts(contactEntity)
//        }
//        if(isCredentialsValid()) {
//            val intent = Intent(this, MainActivity::class.java)
//                    Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show()
//                    startActivity(intent)
//                    finish()
//        }


        }

    private fun isCredentialsValid(): Boolean {
        var isDataValid = true
        if (binding.firstname.text.toString().isEmpty()) {

            isDataValid = false
        }

        if (binding.lastname.text.toString().isEmpty()) {

            isDataValid = false

        }

        if (binding.phNumber.text.toString().isEmpty() ) {
            isDataValid = false
        }

        return isDataValid
    }

}