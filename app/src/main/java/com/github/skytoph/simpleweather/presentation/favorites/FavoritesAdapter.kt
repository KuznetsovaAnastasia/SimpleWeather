package com.github.skytoph.simpleweather.presentation.favorites

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.skytoph.simpleweather.presentation.weather.WeatherFragment
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class FavoritesAdapter @Inject constructor
    (fragment: Fragment, diffCallback: DiffUtil.ItemCallback<String>) :
    FragmentStateAdapter(fragment) {

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount(): Int = differ.currentList.size

    override fun createFragment(position: Int): Fragment =
        WeatherFragment.newInstance(differ.currentList[position], true)

    fun getItem(position: Int): String = differ.currentList[position]

    fun submitList(newList: List<String>) {
        differ.submitList(newList.toMutableList())
    }

    override fun getItemId(position: Int): Long = getItem(position).hashCode().toLong()

    override fun containsItem(itemId: Long): Boolean =
        differ.currentList.find { it.hashCode().toLong() == itemId } != null

    class StringDiffCallback @Inject constructor() : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = false
    }
}