package cz.vladad.assertj.android.api.view

import androidx.annotation.IntDef
import android.view.ViewGroup

@IntDef(ViewGroup.FOCUS_AFTER_DESCENDANTS, ViewGroup.FOCUS_BEFORE_DESCENDANTS, ViewGroup.FOCUS_BLOCK_DESCENDANTS)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ViewGroupDescendantFocusability
