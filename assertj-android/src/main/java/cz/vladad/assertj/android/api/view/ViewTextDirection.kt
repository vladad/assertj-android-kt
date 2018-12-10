package cz.vladad.assertj.android.api.view

import android.annotation.TargetApi
import android.os.Build
import androidx.annotation.IntDef
import android.view.View

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
@IntDef(
    View.TEXT_DIRECTION_INHERIT,
    View.TEXT_DIRECTION_FIRST_STRONG,
    View.TEXT_DIRECTION_ANY_RTL,
    View.TEXT_DIRECTION_LTR,
    View.TEXT_DIRECTION_RTL,
    View.TEXT_DIRECTION_LOCALE
)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ViewTextDirection
