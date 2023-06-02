package com.github.skytoph.simpleweather.domain.search

import javax.inject.Inject

interface AddAttribution {
    fun addIfValid(list: List<SearchItemDomain>): List<SearchItemDomain>

    class Base @Inject constructor() : AddAttribution {

        override fun addIfValid(list: List<SearchItemDomain>): List<SearchItemDomain> =
            if (list.isNotError()) list.withAttribution() else list

        private fun List<SearchItemDomain>.withAttribution() =
            this.toMutableList().also { it.add(SearchItemDomain.Attribution) }

        private fun List<SearchItemDomain>.isNotError(): Boolean =
            this.getOrNull(0)?.let { it !is SearchItemDomain.Fail } ?: false
    }
}