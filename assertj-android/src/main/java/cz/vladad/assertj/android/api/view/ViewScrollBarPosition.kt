package cz.vladad.assertj.android.api.view

import androidx.annotation.IntDef
import android.view.View

@IntDef(
    View.SCROLLBAR_POSITION_DEFAULT,
    View.SCROLLBAR_POSITION_LEFT,
    View.SCROLLBAR_POSITION_RIGHT
)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ViewScrollBarPosition
