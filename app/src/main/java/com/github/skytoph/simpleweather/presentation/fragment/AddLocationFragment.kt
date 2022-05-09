package com.github.skytoph.simpleweather.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.databinding.FragmentAddLocationBinding

class AddLocationFragment : Fragment() {
    private lateinit var binding: FragmentAddLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddLocationBinding.inflate(inflater, container, false)

        binding.locationToolbar.inflateMenu(R.menu.location_menu)
        binding.locationToolbar.setOnMenuItemClickListener(menuItemClicked)
        setupMenu()

        val root = binding.root

        return root
    }

    private val menuItemClicked = { item:MenuItem ->
        when(item.itemId){
            R.id.action_add -> {
                val rootView = item.actionView
                val btnAdd = rootView.findViewById<Button>(R.id.menu_item_button_add)
                btnAdd.visibility = View.GONE
                val tvAdded = rootView.findViewById<TextView>(R.id.menu_item_text_view_added)
                tvAdded.visibility = View.VISIBLE
                true
            }
            else -> false
        }
    }

    private fun setupMenu() {
        val itemAdd = binding.locationToolbar.menu.findItem(R.id.action_add)
        val rootView = itemAdd.actionView
        val btnAdd = rootView.findViewById<Button>(R.id.menu_item_button_add)
        btnAdd.setOnClickListener{ menuItemClicked(itemAdd) }
    }
}