package cz.vladad.assertj.android.api.view

import androidx.annotation.IntDef
import android.view.View

@IntDef(
    View.VISIBLE,
    View.INVISIBLE,
    View.GONE
)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ViewVisibility
