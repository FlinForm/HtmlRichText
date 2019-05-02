package com.example.htmlrichtext

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.sufficientlysecure.htmltextview.HtmlResImageGetter

class MainActivity : AppCompatActivity() {

    private val inputManager by lazy { getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = Html.fromHtml(RICH_TEXT, Html.FROM_HTML_MODE_LEGACY)
        htmlTextView.setHtml(RICH_TEXT, HtmlResImageGetter(htmlTextView))

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                htmlTextView.visibility = VISIBLE
                textView.visibility = GONE
            } else {
                htmlTextView.visibility = GONE
                textView.visibility = VISIBLE
            }
        }

        displayButton.setOnClickListener {
            if (checkBox.isChecked) {
                htmlTextView.setHtml(editText.text.toString(), HtmlResImageGetter(htmlTextView))
            } else {
                textView.text = Html.fromHtml(editText.text.toString(), Html.FROM_HTML_MODE_COMPACT)
            }
            inputManager.hideSoftInputFromWindow(editText.windowToken, 0)
        }
        clearButton.setOnClickListener { editText.text.clear() }

    }

    private companion object {
        private const val RICH_TEXT =
            "<span style=\"background-color: #FFFF00\">This text is highlighted in yellow.</span>"
    }
}
