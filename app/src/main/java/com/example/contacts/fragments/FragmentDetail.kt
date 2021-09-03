package com.example.contacts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contacts.Navigator
import com.example.contacts.data.Contacts
import com.example.contacts.databinding.FragmentDetailBinding
import com.example.contacts.util.withArguments

private const val LOCK = "master"

class FragmentDetail : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    companion object {
        fun newInstance(id: Int): FragmentDetail {
            return FragmentDetail().withArguments {
                putInt(LOCK, id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val id = requireArguments().getInt(LOCK)
        with(binding) {
            textName.setText(Contacts.properties[id].name)
            textSurname.setText(Contacts.properties[id].surname)
            textPhone.setText(Contacts.properties[id].phoneNumber)
            imageViewDet.setImageResource(Contacts.properties[id].pic)
        }

        binding.buttonSave.setOnClickListener {
            with(Contacts.properties[id]) {
                name = binding.textName.text.toString()
                surname = binding.textSurname.text.toString()
                phoneNumber = binding.textPhone.text.toString()
            }
            (activity as Navigator).navigateTo(FragmentList(), "fragmentList")
        }
        return binding.root
    }
}