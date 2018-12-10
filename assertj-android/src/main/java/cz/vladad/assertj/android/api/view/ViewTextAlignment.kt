package cz.vladad.assertj.android.api.view

import android.annotation.TargetApi
import android.os.Build
import androidx.annotation.IntDef
import android.view.View

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
@IntDef(
    View.TEXT_ALIGNMENT_INHERIT,
    View.TEXT_ALIGNMENT_GRAVITY,
    View.TEXT_ALIGNMENT_TEXT_START,
    View.TEXT_ALIGNMENT_TEXT_END,
    View.TEXT_ALIGNMENT_CENTER,
    View.TEXT_ALIGNMENT_VIEW_START,
    View.TEXT_ALIGNMENT_VIEW_END
)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ViewTextAlignment
