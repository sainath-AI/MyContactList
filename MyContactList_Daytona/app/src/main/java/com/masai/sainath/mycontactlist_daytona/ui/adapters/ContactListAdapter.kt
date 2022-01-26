package com.masai.sainath.mycontactlist_daytona.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.sainath.mycontactlist_daytona.databinding.ContactItemsBinding
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity
import com.masai.sainath.mycontactlist_daytona.ui.DetailsContacts

class ContactListAdapter(val context: Context, var contactsLst:List<ContactEntity>):RecyclerView.Adapter<ContactListAdapter.ContactsViewHolder>() {


    class ContactsViewHolder(val binding: ContactItemsBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListAdapter.ContactsViewHolder {
        return ContactsViewHolder(
            ContactItemsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactListAdapter.ContactsViewHolder, position: Int) {
       val data=contactsLst[position]
        holder.binding.firstName.text=data.firstName
        holder.binding.LastName.text=data.lastName

        holder.binding.CapsText.text= data.firstName.get(0).toString() + data.lastName.get(0).toString()

        holder.itemView.setOnClickListener {
            val intent= Intent(context,DetailsContacts::class.java)
            intent.putExtra("id", data.id)
            intent.putExtra("firstname", data.firstName)
            intent.putExtra("lastname", data.lastName)
            intent.putExtra("phno", data.PhNo)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int =contactsLst.size
}