package cz.vladad.assertj.android.api.widget

import android.widget.CompoundButton

/**
 * Assertions for [CompoundButton] instances.
 *
 * This class is final. To extend use [AbstractCompoundButtonAssert].
 */
class CompoundButtonAssert(actual: CompoundButton) :
    AbstractCompoundButtonAssert<CompoundButtonAssert, CompoundButton>(actual, CompoundButtonAssert::class.java)
