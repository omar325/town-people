package com.example.android.townpeople.presentaition.list

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.townpeople.R
import com.example.android.townpeople.data.Person

@BindingAdapter("app:dataFromState")
fun setDataFromState(view: View, state: PeopleViewModelState) {
    if(state !is PeopleViewModelState.Success) return

    when(view.id) {
        R.id.peopleRecyclerView -> {
            (view as RecyclerView).adapter?.let {
                if (it is PeopleListAdapter) {
                    it.submitList(state.people)
                }
            }
        }
        else -> { }
    }
}

@BindingAdapter("app:imageFromPerson")
fun setImageFromPerson(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .into(imageView)
}

@BindingAdapter("app:visibilityFromState")
fun setVisibilityFromState(view: View, state: PeopleViewModelState) {
    view.visibility = when(view.id) {
        R.id.progressBar -> getVisibilityForProgressBar(state)
        R.id.errorLayout -> getVisibilityForErrorLayout(state)
        R.id.peopleRecyclerView -> getVisibilityForRecyclerView(state)
        else -> View.VISIBLE
    }
}

fun getVisibilityForProgressBar(state: PeopleViewModelState): Int =
    when(state) {
        PeopleViewModelState.Loading -> View.VISIBLE
        else -> View.GONE
    }

fun getVisibilityForErrorLayout(state: PeopleViewModelState): Int =
    when(state) {
        PeopleViewModelState.Error -> View.VISIBLE
        else -> View.GONE
    }

fun getVisibilityForRecyclerView(state: PeopleViewModelState): Int =
    when(state) {
        is PeopleViewModelState.Success -> View.VISIBLE
        else -> View.GONE
    }