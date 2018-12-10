package cz.vladad.assertj.android.api.widget

import android.widget.TextView

/**
 * Assertions for [TextView] instances.
 *
 *
 * This class is final. To extend use [AbstractTextViewAssert].
 */
class TextViewAssert(actual: TextView) :
    AbstractTextViewAssert<TextViewAssert, TextView>(actual, TextViewAssert::class.java)
