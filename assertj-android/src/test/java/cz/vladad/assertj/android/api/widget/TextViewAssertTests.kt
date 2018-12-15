package cz.vladad.assertj.android.api.widget

import android.content.Context
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import cz.vladad.assertj.android.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TextViewTests {

    private val _context: Context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun textViewTests() {
        val textView = TextView(_context)

        assertThat(textView).doesNotContainText("notUsedText")
        assertThat(textView).hasText("")

        textView.text = "SampleText"

        assertThat(textView).startsWithText("Sample")
        assertThat(textView).hasText("SampleText")
        assertThat(textView).endsWithText("Text")
        assertThat(textView).containsText("eT")
        assertThat(textView).doesNotStartWithText("Text")
        assertThat(textView).doesNotEndWithText("Sample")
    }
}
