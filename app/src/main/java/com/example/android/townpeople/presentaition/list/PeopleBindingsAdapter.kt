package com.example.android.townpeople.presentaition.list

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.android.townpeople.R

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