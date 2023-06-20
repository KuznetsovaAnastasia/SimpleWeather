package com.github.skytoph.simpleweather.core.presentation.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.withStyledAttributes
import com.github.skytoph.simpleweather.R

class ClickableLinkView : AppCompatTextView {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.ClickableLinkView) {
            val url: String? = getString(R.styleable.ClickableLinkView_url)
            url?.let { initClickListener(url) }
        }
    }

    private fun initClickListener(url: String) = setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent.resolveActivity(context.packageManager) != null)
            context.startActivity(intent)
    }
}