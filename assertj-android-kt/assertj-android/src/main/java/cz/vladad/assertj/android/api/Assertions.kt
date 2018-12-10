package cz.vladad.assertj.android.api

import cz.vladad.assertj.android.api.view.ViewGroupAssert


/**
 * Assertions for testing Android classes.
 */
@Suppress("unused")
object Assertions {
    @JvmStatic
    fun assertThat(actual: android.view.View): cz.vladad.assertj.android.api.view.ViewAssert {
        return cz.vladad.assertj.android.api.view.ViewAssert(actual)
    }

    @JvmStatic
    fun assertThat(actual: android.view.ViewGroup): cz.vladad.assertj.android.api.view.ViewGroupAssert {
        return cz.vladad.assertj.android.api.view.ViewGroupAssert(actual)
    }

    @JvmStatic
    fun assertThat(actual: android.widget.CheckedTextView): cz.vladad.assertj.android.api.widget.CheckedTextViewAssert {
        return cz.vladad.assertj.android.api.widget.CheckedTextViewAssert(actual)
    }

    @JvmStatic
    fun assertThat(actual: android.widget.CompoundButton): cz.vladad.assertj.android.api.widget.CompoundButtonAssert {
        return cz.vladad.assertj.android.api.widget.CompoundButtonAssert(actual)
    }

    @JvmStatic
    fun assertThat(actual: android.widget.TextView): cz.vladad.assertj.android.api.widget.TextViewAssert {
        return cz.vladad.assertj.android.api.widget.TextViewAssert(actual)
    }
}
