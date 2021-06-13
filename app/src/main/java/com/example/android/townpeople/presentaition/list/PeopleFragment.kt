package com.example.android.townpeople.presentaition.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.townpeople.R
import com.example.android.townpeople.databinding.FragmentPeopleBinding
import kotlinx.android.synthetic.main.fragment_people.*
import org.koin.android.ext.android.get

class PeopleFragment : Fragment() {

    private val viewModel: PeopleViewModel by lazy { get() }
    private val peopleListAdapter by lazy { PeopleListAdapter {
        findNavController().navigate(
            PeopleFragmentDirections.actionPeopleFragmentToPeopleDetailFragment(it)
        )
    } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = (DataBindingUtil.inflate(
        layoutInflater,
        R.layout.fragment_people,
        container,
        false
    ) as FragmentPeopleBinding).apply {
        lifecycleOwner = this@PeopleFragment
        viewmodel = viewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        peopleRecyclerView.apply{
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = peopleListAdapter
        }
    }
}