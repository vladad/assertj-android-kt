package cz.vladad.assertj.android.api.view

import android.annotation.TargetApi
import android.os.Build
import androidx.annotation.IntDef
import android.view.View


@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
@IntDef(
    View.LAYOUT_DIRECTION_RTL,
    View.LAYOUT_DIRECTION_LTR,
    View.LAYOUT_DIRECTION_INHERIT,
    View.LAYOUT_DIRECTION_LOCALE
)
@Retention(AnnotationRetention.SOURCE)
annotation class ViewLayoutDirection
