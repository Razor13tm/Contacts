package com.example.contacts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.Navigator
import com.example.contacts.data.Contacts
import com.example.contacts.databinding.FragmentListBinding
import com.example.contacts.util.Adapter

class FragmentList : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val contacts = Contacts.properties

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        with(binding.contactList) {
            adapter = Adapter(contacts) { id ->
                if (context?.resources?.configuration?.smallestScreenWidthDp!! >= 600) {
                    (activity as Navigator).navigateToTabletDetail(
                        FragmentDetail.newInstance(id),
                        "detailFragment")
                } else {
                    (activity as Navigator).navigateTo(FragmentDetail.newInstance(id), "fragmentDetail")
                }
            }
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        return binding.root
    }
}