package com.example.android.townpeople.presentaition.list

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.android.townpeople.R
import com.example.android.townpeople.data.Person
import com.example.android.townpeople.presentaition.detail.FriendsListAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

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

@BindingAdapter("app:configureWithViewModel")
fun configureWithViewModel(view: View, viewModel: PeopleViewModel) {
    when(view.id) {
        R.id.errorLayout -> {
            view.findViewById<Button>(R.id.buttonRetry).setOnClickListener {
                viewModel.getPeople()
            }
        }
        else -> { }
    }
}

@BindingAdapter("app:imageFromPerson")
fun setImageFromPerson(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView)
        .load(
            GlideUrl(
                imageUrl,
                LazyHeaders.Builder().addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.181 Mobile Safari/537.36").build()
            )
        )
        .centerCrop()
        .into(imageView)
}

@BindingAdapter("app:formatAge")
fun setAgeFormat(textView: TextView, age: Int) {
    textView.text = textView.context.getString(R.string.age, age)
}

@BindingAdapter("app:friendsFromPerson")
fun setFriendsFromPerson(viewPager2: ViewPager2, friends: List<String>) {
    viewPager2.apply {
        adapter = FriendsListAdapter(friends)

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)

        setPageTransformer { page, position ->
            val offset = position *-(2*offsetPx + pageMarginPx)
            page.translationX = offset
        }

        offscreenPageLimit = 2
    }
}

@BindingAdapter("app:professionsFromPerson")
fun setFriendsFromPerson(chipGroup: ChipGroup, professions: List<String>) {
    professions.forEach { profession ->
        chipGroup.addView(Chip(chipGroup.context).apply {
            text = profession
            setChipIconResource(R.drawable.ic_settings)
        })
    }
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