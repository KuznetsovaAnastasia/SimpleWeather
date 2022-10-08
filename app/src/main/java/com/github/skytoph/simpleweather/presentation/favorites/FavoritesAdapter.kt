package com.github.skytoph.simpleweather.presentation.favorites

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.skytoph.simpleweather.presentation.weather.WeatherFragment

class FavoritesAdapter constructor(parentFragment: Fragment, favorites: List<String>) :
    FragmentStateAdapter(parentFragment) {

    private val favorites: ArrayList<String> = ArrayList(favorites)

    override fun getItemCount(): Int = favorites.size

    override fun createFragment(position: Int): Fragment =
        WeatherFragment.newInstance(favorites[position], true)

    fun getItem(position: Int): String = favorites[position]

    fun submitList(newList: List<String>) {
        val diff = DiffUtil.calculateDiff(StringDiffUtil(favorites, newList))

        favorites.clear()
        favorites.addAll(newList)

        diff.dispatchUpdatesTo(this)
    }

    override fun getItemId(position: Int): Long = favorites[position].toId()

    override fun containsItem(itemId: Long): Boolean =
        favorites.any { it.toId() == itemId }

    private fun String.toId(): Long = this.hashCode().toLong()

    class StringDiffUtil constructor(
        private val oldList: List<String>,
        private val newList: List<String>,
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            areItemsTheSame(oldItemPosition, newItemPosition)

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}