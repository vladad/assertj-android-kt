package cz.vladad.assertj.android.api.view

import androidx.annotation.IntDef
import android.view.View


@IntDef(
    View.LAYER_TYPE_NONE,
    View.LAYER_TYPE_SOFTWARE,
    View.LAYER_TYPE_HARDWARE
)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ViewLayerType
