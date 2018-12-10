package cz.vladad.assertj.android.api.widget

import android.annotation.TargetApi
import android.graphics.drawable.Drawable
import android.widget.CheckedTextView
import org.assertj.core.api.Assertions

import android.os.Build.VERSION_CODES.JELLY_BEAN
import org.assertj.core.api.Assertions.assertThat

@Suppress("unused")
class CheckedTextViewAssert(actual: CheckedTextView) :
    AbstractTextViewAssert<CheckedTextViewAssert, CheckedTextView>(actual, CheckedTextViewAssert::class.java) {

    fun isChecked(): CheckedTextViewAssert {
        isNotNull
        assertThat(actual.isChecked)
            .overridingErrorMessage("Expected checked but was not checked.")
            .isTrue()
        return this
    }

    fun isNotChecked(): CheckedTextViewAssert {
        isNotNull
        assertThat(actual.isChecked)
            .overridingErrorMessage("Expected not checked but was checked.")
            .isFalse()
        return this
    }

    @TargetApi(JELLY_BEAN)
    fun hasCheckMarkDrawable(drawable: Drawable): CheckedTextViewAssert {
        isNotNull
        val actualDrawable = actual.checkMarkDrawable
        Assertions.assertThat(actualDrawable)
            .overridingErrorMessage(
                "Expected check mark drawable <%s> but was <%s>.", drawable,
                actualDrawable
            )
            .isSameAs(drawable)
        return this
    }
}
