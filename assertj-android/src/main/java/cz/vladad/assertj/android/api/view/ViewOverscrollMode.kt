package cz.vladad.assertj.android.api.view

import androidx.annotation.IntDef
import android.view.View


@IntDef(
    View.OVER_SCROLL_ALWAYS,
    View.OVER_SCROLL_IF_CONTENT_SCROLLS,
    View.OVER_SCROLL_NEVER
)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ViewOverscrollMode
