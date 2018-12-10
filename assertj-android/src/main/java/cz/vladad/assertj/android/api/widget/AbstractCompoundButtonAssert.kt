package cz.vladad.assertj.android.api.widget

import android.widget.CompoundButton

import org.assertj.core.api.Assertions.assertThat

@Suppress("unused")
abstract class AbstractCompoundButtonAssert<S : AbstractCompoundButtonAssert<S, A>, A : CompoundButton>(
    actual: A,
    selfType: Class<S>
) : AbstractTextViewAssert<S, A>(actual, selfType) {

    fun isChecked(): S {
        isNotNull
        assertThat(actual.isChecked)
            .overridingErrorMessage("Expected checked but was not checked.")
            .isTrue()
        return myself
    }

    fun isNotChecked(): S {
        isNotNull
        assertThat(actual.isChecked)
            .overridingErrorMessage("Expected not checked but was checked.")
            .isFalse()
        return myself
    }
}
