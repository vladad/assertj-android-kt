package cz.vladad.assertj.android.api.view

import androidx.annotation.IntDef
import android.view.View

@IntDef(
    View.SCROLLBARS_INSIDE_INSET,
    View.SCROLLBARS_INSIDE_OVERLAY,
    View.SCROLLBARS_OUTSIDE_INSET,
    View.SCROLLBARS_OUTSIDE_OVERLAY
)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ViewScrollBarStyle
