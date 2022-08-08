package com.github.skytoph.simpleweather.presentation.favorites

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.skytoph.simpleweather.presentation.weather.WeatherFragment

class FavoritesAdapter(fragment: Fragment, diffCallback: DiffUtil.ItemCallback<String>) :
    FragmentStateAdapter(fragment) {

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount(): Int = differ.currentList.size
    override fun createFragment(position: Int): Fragment =
        WeatherFragment.newInstance(differ.currentList[position], true)

    fun submitList(newList: List<String>) {
        differ.submitList(newList)
    }

    class StringDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }
}