package cz.vladad.assertj.android.api.view

import android.view.View

/**
 * Assertions for [View] instances.
 *
 * This class is final. To extend use [AbstractViewAssert].
 */
class ViewAssert(actual: View) : AbstractViewAssert<ViewAssert, View>(actual, ViewAssert::class.java)
