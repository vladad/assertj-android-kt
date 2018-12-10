package cz.vladad.assertj.android.api.view

import androidx.annotation.IntDef
import android.view.ViewGroup

@IntDef(
    ViewGroup.PERSISTENT_ALL_CACHES,
    ViewGroup.PERSISTENT_ANIMATION_CACHE,
    ViewGroup.PERSISTENT_NO_CACHE,
    ViewGroup.PERSISTENT_SCROLLING_CACHE
)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ViewGroupPersistentDrawingCache
