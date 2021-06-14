package com.example.android.townpeople.presentaition.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.townpeople.R
import com.example.android.townpeople.data.Person
import com.example.android.townpeople.databinding.PersonItemBinding

class PeopleListAdapter(
    private val onPersonClickAction: (person: Person) -> Unit
): ListAdapter<Person, PersonViewHolder>(CounterDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder = PersonViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.person_item,
            parent,
            false
        ), onPersonClickAction
    )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class PersonViewHolder(
    private val binding: PersonItemBinding,
    private val onPersonClickAction: (person: Person) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind(newPerson: Person) {
        binding.apply {
            person = newPerson
            root.setOnClickListener { onPersonClickAction(newPerson) }
        }
    }
}

object CounterDiffCallback: DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem == newItem
}