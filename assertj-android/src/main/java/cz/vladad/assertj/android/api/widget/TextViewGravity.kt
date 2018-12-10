package cz.vladad.assertj.android.api.widget

import androidx.annotation.IntDef
import android.view.Gravity

import java.lang.annotation.RetentionPolicy.SOURCE

@IntDef(
    Gravity.NO_GRAVITY,
    Gravity.TOP,
    Gravity.BOTTOM,
    Gravity.LEFT,
    Gravity.RIGHT,
    Gravity.CENTER_VERTICAL,
    Gravity.FILL_VERTICAL,
    Gravity.CENTER_HORIZONTAL,
    Gravity.FILL_HORIZONTAL,
    Gravity.CENTER,
    Gravity.FILL,
    Gravity.CLIP_VERTICAL,
    Gravity.CLIP_HORIZONTAL,
    Gravity.START,
    Gravity.END
)
@Retention(AnnotationRetention.SOURCE)
internal annotation class TextViewGravity
