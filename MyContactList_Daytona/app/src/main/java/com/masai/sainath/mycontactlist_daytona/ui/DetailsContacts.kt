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
import com.masai.sainath.mycontactlist_daytona.dao.ContactDao
import com.masai.sainath.mycontactlist_daytona.database.ContactsDatabase
import com.masai.sainath.mycontactlist_daytona.databinding.ActivityDetailsContactsBinding
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity
import com.masai.sainath.mycontactlist_daytona.viewmodel.ContactsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DetailsContacts : AppCompatActivity() {
    lateinit var binding: ActivityDetailsContactsBinding
    lateinit var contactsDatabase: ContactsDatabase
    val viewModel: ContactsViewModel by viewModels()
//    lateinit var  contactDao : ContactDao
   // lateinit var contactEntity: ContactEntity


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        contactsDatabase = ContactsDatabase.getDatabaseInstances(this)
//        contactDao = contactsDatabase.getContactsDao()


        /**
         * here im retreving data from Adapter class , where i have used itemview ,
         * an object from recyclerview used to navigate and pass data
         */
        val iid=intent.getIntExtra("id",0)
        val First=intent.getStringExtra("firstname")
        val Last=intent.getStringExtra("lastname")
        val PHNo=intent.getStringExtra("phno")

        /**
         * setting the pre populated data into the editexts, or fields
         */
        binding.ContactName.text= First.toString() +" "+ Last.toString()
        binding.firstname.setText(First)
        binding.lastname.setText(Last)
        binding.phNumber.setText(PHNo)

        binding.btnSave.setOnClickListener {

            UpdateContacts(it)
        }
        /**
         * for deleteing particular item from list , im using dialog box for confirmation and implementing delete functionality
         */
        binding.delete.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("are you sure want to delete this item")
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, _ ->

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.deleteContacts(iid)
                }
                val intent=Intent(this,MainActivity::class.java)
                Toast.makeText(this, "Contact Deleted successfully", Toast.LENGTH_SHORT).show()

                startActivity(intent)
                dialog.dismiss()

            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
            dialog.show()
        }



    }

    /**
     * upadating the data in particular item and saving it into room database
     */

    private fun UpdateContacts( it:View?) {

//        val updateContact = ContactEntity(firstName=firstName,lastName=lastName,PhNo=PhNo)
//        updateContact.firstName = firstName
//        updateContact.lastName = lastName
//        updateContact.PhNo = PhNo
//
//            viewModel.updateContacts(updateContact)
//
//
//        if(isCredentialsValid()) {
//            val intent = Intent(this, MainActivity::class.java)
//                    Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show()
//                    startActivity(intent)
//                    finish()
//        }


        val first= binding.firstname.text.toString()
        val last= binding.lastname.text.toString()
        val phNo= binding.phNumber.text.toString()

        if(isCredentialsValid()) {
            val intent = Intent(this, MainActivity::class.java)
            val contactEntity= ContactEntity(firstName = first, lastName = last, PhNo =phNo)
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.updateContacts(contactEntity)

            }
                    Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
        }


        }

    /**
     * in This below Fuction credentails are Validated according to view functionality
     * else if validation doesnt meet , Errors have been set.
     */

    private fun isCredentialsValid(): Boolean {
        var isDataValid = true
        if (binding.firstname.text.toString().isEmpty()) {
            binding.firstname.setError("enter valid first name");

            isDataValid = false
        }

        if (binding.lastname.text.toString().isEmpty()) {
            binding.lastname.setError("enter valid last name");

            isDataValid = false

        }

        if (binding.phNumber.text.toString().isEmpty()|| binding.phNumber.text?.length!=10 ) {
            binding.phNumber.setError("enter valid 10 digit Ph Number");
            isDataValid = false
        }

        return isDataValid
    }

}