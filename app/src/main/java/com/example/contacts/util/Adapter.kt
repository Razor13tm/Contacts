package com.example.contacts.util

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.data.Contact

class Adapter(private val contacts: List<Contact>, private val onItemClicked: (id: Int) -> Unit
) : RecyclerView.Adapter<Adapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.list_item), onItemClicked)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int = contacts.size

    inner class Holder(
        view: View,
        onItemClicked: (id: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.textview_name)
        private val tvSurname: TextView = view.findViewById(R.id.textview_surname)
        private val tvPhone: TextView = view.findViewById(R.id.textview_num)
        private val image: ImageView = view.findViewById(R.id.imageview_thumb)
        private var currentId: Int? = null

        init {
            view.setOnClickListener {
                currentId?.let { onItemClicked(it) }
            }
        }

        fun bind(contact: Contact) {
            currentId = contact.id
            tvName.text = contact.name
            tvSurname.text = contact.surname
            tvPhone.text = contact.phoneNumber
            image.setImageResource(contact.pic)
        }
    }
}