package com.example.android.townpeople.presentaition.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.townpeople.R
import com.example.android.townpeople.databinding.FragmentPeopleDetailBinding
import kotlinx.android.synthetic.main.fragment_people_detail.*

class PeopleDetailFragment : Fragment() {

    val args: PeopleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = (DataBindingUtil.inflate(
        layoutInflater,
        R.layout.fragment_people_detail,
        container,
        false
    ) as FragmentPeopleDetailBinding).apply {
        lifecycleOwner = this@PeopleDetailFragment
        person = args.person
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }
}