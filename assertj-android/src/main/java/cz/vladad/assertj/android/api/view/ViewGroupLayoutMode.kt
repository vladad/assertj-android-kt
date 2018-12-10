package cz.vladad.assertj.android.api.view

import android.annotation.TargetApi
import android.os.Build
import androidx.annotation.IntDef
import android.view.ViewGroup

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@IntDef(ViewGroup.LAYOUT_MODE_CLIP_BOUNDS, ViewGroup.LAYOUT_MODE_OPTICAL_BOUNDS)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ViewGroupLayoutMode
