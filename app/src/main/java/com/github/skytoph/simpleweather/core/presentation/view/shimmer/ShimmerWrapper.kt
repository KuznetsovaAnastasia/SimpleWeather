package com.github.skytoph.simpleweather.core.presentation.view.shimmer

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout

class ShimmerWrapper @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ShimmerFrameLayout(context, attrs) {

    fun show(loadingVisible: Boolean, layoutVisible: Boolean = true) {
        if (loadingVisible) {
            showShimmer(true)
        } else {
            stopShimmer()
            hideShimmer()
        }
        visibility = if (layoutVisible) View.VISIBLE else View.GONE
    }
}