package cz.vladad.assertj.android.api.view

import android.annotation.TargetApi
import android.view.View
import android.view.ViewGroup

import android.os.Build.VERSION_CODES.JELLY_BEAN_MR2
import android.view.ViewGroup.FOCUS_AFTER_DESCENDANTS
import android.view.ViewGroup.FOCUS_BEFORE_DESCENDANTS
import android.view.ViewGroup.FOCUS_BLOCK_DESCENDANTS
import android.view.ViewGroup.LAYOUT_MODE_CLIP_BOUNDS
import android.view.ViewGroup.LAYOUT_MODE_OPTICAL_BOUNDS
import android.view.ViewGroup.PERSISTENT_ALL_CACHES
import android.view.ViewGroup.PERSISTENT_ANIMATION_CACHE
import android.view.ViewGroup.PERSISTENT_NO_CACHE
import android.view.ViewGroup.PERSISTENT_SCROLLING_CACHE
import cz.vladad.assertj.android.internal.BitmaskUtils.buildNamedValueString
import org.assertj.core.api.Assertions.assertThat


abstract class AbstractViewGroupAssert<S : AbstractViewGroupAssert<S, A>, A : ViewGroup>
protected constructor(
    actual: A,
    selfType: Class<S>
) : AbstractViewAssert<S, A>(actual, selfType) {

    companion object {

        @JvmStatic
        fun descendantFocusabilityToString(@ViewGroupDescendantFocusability focusability: Int): String {
            return buildNamedValueString(focusability.toLong())
                .value(FOCUS_AFTER_DESCENDANTS.toLong(), "afterDescendants")
                .value(FOCUS_BEFORE_DESCENDANTS.toLong(), "beforeDescendants")
                .value(FOCUS_BLOCK_DESCENDANTS.toLong(), "blockDescendants")
                .get()
        }

        @JvmStatic
        fun persistentDrawingCacheToString(@ViewGroupPersistentDrawingCache cache: Int): String {
            return buildNamedValueString(cache.toLong())
                .value(PERSISTENT_ALL_CACHES.toLong(), "all")
                .value(PERSISTENT_ANIMATION_CACHE.toLong(), "animation")
                .value(PERSISTENT_NO_CACHE.toLong(), "none")
                .value(PERSISTENT_SCROLLING_CACHE.toLong(), "scrolling")
                .get()
        }

        @TargetApi(JELLY_BEAN_MR2)
        @JvmStatic
        fun layoutModeToString(@ViewGroupLayoutMode layoutMode: Int): String {
            return buildNamedValueString(layoutMode.toLong())
                .value(LAYOUT_MODE_CLIP_BOUNDS.toLong(), "clip_bounds")
                .value(LAYOUT_MODE_OPTICAL_BOUNDS.toLong(), "optical_bounds")
                .get()
        }
    }

    fun isAddingStatesFromChildren(): S {
        isNotNull
        assertThat(actual.addStatesFromChildren())
            .overridingErrorMessage("Expected adding states from children but was not")
            .isTrue()
        return myself
    }

    fun isNotAddingStatesFromChildren(): S {
        isNotNull
        assertThat(actual.addStatesFromChildren())
            .overridingErrorMessage("Expected not adding states from children but was")
            .isFalse()
        return myself
    }

    fun isAlwaysDrawnWithCache(): S {
        isNotNull
        assertThat(actual.isAlwaysDrawnWithCacheEnabled)
            .overridingErrorMessage("Expected to always draw with cache but was not")
            .isTrue()
        return myself
    }

    fun isNotAlwaysDrawnWithCache(): S {
        isNotNull
        assertThat(actual.isAlwaysDrawnWithCacheEnabled)
            .overridingErrorMessage("Expected to not always draw with cache but was")
            .isFalse()
        return myself
    }

    @TargetApi(JELLY_BEAN_MR2)
    fun isClippingChildren(): S {
        isNotNull
        assertThat(actual.clipChildren)
            .overridingErrorMessage("Expected to be clipping children but was not.")
            .isTrue()
        return myself
    }

    @TargetApi(JELLY_BEAN_MR2)
    fun isNotClippingChildren(): S {
        isNotNull
        assertThat(actual.clipChildren)
            .overridingErrorMessage("Expected to not be clipping children but was.")
            .isFalse()
        return myself
    }

    fun hasChildCount(count: Int): S {
        isNotNull
        val actualCount = actual.childCount
        assertThat(actualCount)
            .overridingErrorMessage("Expected child count <%s> but was <%s>", count, actualCount)
            .isEqualTo(count)
        return myself
    }

    fun hasDescendantFocusability(@ViewGroupDescendantFocusability focusability: Int): S {
        isNotNull
        val actualFocusability = actual.descendantFocusability

        assertThat(actualFocusability)
            .overridingErrorMessage(
                "Expected descendant focusability <%s> but was <%s>",
                descendantFocusabilityToString(focusability),
                descendantFocusabilityToString(actualFocusability)
            )
            .isEqualTo(focusability)
        return myself
    }

    fun hasFocusedChild(view: View): S {
        isNotNull
        val actualView = actual.focusedChild
        assertThat(actualView)
            .overridingErrorMessage("Expected focused child <%s> but was <%s>", view, actualView)
            .isSameAs(view)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR2)
    fun hasLayoutMode(@ViewGroupLayoutMode layoutMode: Int): S {
        isNotNull
        val actualLayoutMode = actual.layoutMode

        assertThat(actualLayoutMode)
            .overridingErrorMessage(
                "Expected layout mode <%s> but was <%s>.",
                layoutModeToString(layoutMode), layoutModeToString(actualLayoutMode)
            )
            .isEqualTo(layoutMode)
        return myself
    }

    fun hasPersistentDrawingCache(@ViewGroupPersistentDrawingCache cache: Int): S {
        isNotNull
        val actualCache = actual.persistentDrawingCache

        assertThat(actualCache)
            .overridingErrorMessage(
                "Expected persistent drawing cache <%s> but was <%s>",
                persistentDrawingCacheToString(cache), persistentDrawingCacheToString(actualCache)
            )
            .isEqualTo(cache)
        return myself
    }

    fun hasAnimationCacheEnabled(): S {
        isNotNull
        assertThat(actual.isAnimationCacheEnabled)
            .overridingErrorMessage("Expected animation cache enabled but was disabled")
            .isTrue()
        return myself
    }

    fun hasAnimationCacheDisabled(): S {
        isNotNull
        assertThat(actual.isAnimationCacheEnabled)
            .overridingErrorMessage("Expected animation cache disabled but was enabled")
            .isFalse()
        return myself
    }

    fun hasMotionEventSplittingEnabled(): S {
        isNotNull
        assertThat(actual.isMotionEventSplittingEnabled)
            .overridingErrorMessage("Expected motion event splitting enabled but was disabled")
            .isTrue()
        return myself
    }

    fun hasMotionEventSplittingDisabled(): S {
        isNotNull
        assertThat(actual.isMotionEventSplittingEnabled)
            .overridingErrorMessage("Expected motion event splitting disabled but was enabled")
            .isFalse()
        return myself
    }
}
