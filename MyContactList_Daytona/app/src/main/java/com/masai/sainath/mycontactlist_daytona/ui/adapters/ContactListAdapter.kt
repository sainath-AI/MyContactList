package com.masai.sainath.mycontactlist_daytona.ui.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.sainath.mycontactlist_daytona.R
import com.masai.sainath.mycontactlist_daytona.databinding.ContactItemsBinding
import com.masai.sainath.mycontactlist_daytona.model.ContactEntity
import com.masai.sainath.mycontactlist_daytona.ui.DetailsContacts
import android.content.Context.MODE_PRIVATE
import com.masai.sainath.mycontactlist_daytona.dao.ContactDao
import com.masai.sainath.mycontactlist_daytona.database.ContactsDatabase
import com.masai.sainath.mycontactlist_daytona.ui.MainActivity


class ContactListAdapter(val context: Context, var contactsLst:List<ContactEntity>):RecyclerView.Adapter<ContactListAdapter.ContactsViewHolder>() {



    class ContactsViewHolder(val binding: ContactItemsBinding): RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListAdapter.ContactsViewHolder {

        loadData()
        return ContactsViewHolder(
            ContactItemsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent, false
            )
        )

    }

    private fun loadData(): Boolean {
        val mPrefs: SharedPreferences = context.getSharedPreferences("favoutie", MODE_PRIVATE)

        return mPrefs.getBoolean("favoutie", true)
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

        holder.binding.star.setOnClickListener {

            holder.binding.star.setImageResource(R.drawable.filledstar)
             val boolean=true

            if (boolean){
                holder.binding.star.setOnClickListener {
                    holder.binding.star.setImageResource(R.drawable.star)

                }
            }






//            val mPrefs: SharedPreferences = context.getSharedPreferences("favoutie", MODE_PRIVATE)
//            val editor: SharedPreferences.Editor = mPrefs.edit()
//            editor.putBoolean("favroute", true)
//            editor.apply()
//            if (loadData()) {
//                holder.binding.star.setImageResource(R.drawable.filledstar)
//            }else {
//                holder.binding.star.setOnClickListener {
//                    holder.binding.star.setImageResource(R.drawable.star)
//                }
//            }
        }
    }

    override fun getItemCount(): Int =contactsLst.size
}