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

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NewContacts.setOnClickListener {
            val intent = Intent(this,InsertContacts::class.java)
            startActivity(intent)
        }

        adapter= ContactListAdapter(this,ListOfContacts)
        binding.RevCart.layoutManager=LinearLayoutManager(this)
        binding.RevCart.adapter=adapter

        viewModel.getContacts().observe(this, Observer{
            if(it.isNotEmpty()){
                binding.RevCart.visibility= View.VISIBLE
                binding.NoContacts.visibility=View.INVISIBLE
            }else
            {
                binding.RevCart.visibility= View.INVISIBLE
                binding.NoContacts.visibility=View.VISIBLE
            }

            val data =it
            ListOfContacts.clear()
            ListOfContacts.addAll(data)
            adapter.notifyDataSetChanged()
        })



    }
}