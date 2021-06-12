package com.example.android.townpeople.presentaition.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android.townpeople.R
import kotlinx.android.synthetic.main.fragment_people.*

class PeopleFragment : Fragment(R.layout.fragment_people) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            findNavController().navigate(R.id.action_peopleFragment_to_peopleDetailFragment)
        }
    }
}