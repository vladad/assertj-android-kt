package cz.vladad.assertj.android.api.view

import android.view.ViewGroup

/**
 * Assertions for [ViewGroup] instances.
 *
 * This class is final. To extend use [AbstractViewGroupAssert].
 */
@Suppress("unused")
class ViewGroupAssert(actual: ViewGroup) :
    AbstractViewGroupAssert<ViewGroupAssert, ViewGroup>(actual, ViewGroupAssert::class.java)
