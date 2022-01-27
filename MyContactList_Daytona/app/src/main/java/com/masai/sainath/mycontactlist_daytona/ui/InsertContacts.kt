package com.masai.sainath.mycontactlist_daytona.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
    /**
     * Insering the data in particular item and saving it into room database
     */

    private fun createContacts(it: View?) {


        val firstName=binding.firstname.text.toString()
        val lastName=binding.lastname.text.toString()
        val PhNo=binding.phNumber.text.toString()


        if(isCredentialsValid()) {
            val intent = Intent(this, MainActivity::class.java)
            val contactEntity=ContactEntity(firstName,lastName,PhNo)
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.addContacts(contactEntity)
            }
            Toast.makeText(this, "Contact Added successfully", Toast.LENGTH_SHORT).show()

            startActivity(intent)
            finish()
        }


        /**
         * in This below Fuction credentails are Validated according to view functionality
         * else if validation doesnt meet , Errors have been set.
         */
    }
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