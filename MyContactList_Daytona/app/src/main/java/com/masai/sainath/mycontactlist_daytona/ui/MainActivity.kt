package com.masai.sainath.mycontactlist_daytona.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.sainath.mycontactlist_daytona.R
import com.masai.sainath.mycontactlist_daytona.databinding.ActivityMainBinding
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity
import com.masai.sainath.mycontactlist_daytona.ui.adapters.ContactListAdapter
import com.masai.sainath.mycontactlist_daytona.viewmodel.ContactsViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val ListOfContacts=ArrayList<ContactEntity>()
    lateinit var adapter: ContactListAdapter
    val viewModel: ContactsViewModel by viewModels()
    var oldMyNotes= arrayListOf<ContactEntity>()


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NewContacts.setOnClickListener {
            val intent = Intent(this,InsertContacts::class.java)
            startActivity(intent)
        }



  val linearLayoutManager=LinearLayoutManager(this)
        binding.RevCart.layoutManager=linearLayoutManager


        viewModel.getContacts().observe(this, Observer{contactList ->
            oldMyNotes=contactList as ArrayList<ContactEntity>
            adapter=ContactListAdapter(this, contactList)
            binding.RevCart.adapter = adapter



            if(contactList.isNotEmpty()){
                binding.RevCart.visibility= View.VISIBLE
                binding.NoContacts.visibility=View.INVISIBLE
            }else
            {
                binding.RevCart.visibility= View.INVISIBLE
                binding.NoContacts.visibility=View.VISIBLE
            }

//            val data =contactList
            ListOfContacts.clear()
            ListOfContacts.addAll(contactList)
            adapter.notifyDataSetChanged()
        })



    }
}