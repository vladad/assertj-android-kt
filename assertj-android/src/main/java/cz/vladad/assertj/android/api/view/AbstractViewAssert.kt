package cz.vladad.assertj.android.api.view

import android.annotation.TargetApi
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewParent
import android.view.animation.Animation
import org.assertj.core.api.AbstractAssert

import android.os.Build.VERSION_CODES.JELLY_BEAN
import android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
import android.os.Build.VERSION_CODES.JELLY_BEAN_MR2
import android.os.Build.VERSION_CODES.KITKAT
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.LAYER_TYPE_HARDWARE
import android.view.View.LAYER_TYPE_NONE
import android.view.View.LAYER_TYPE_SOFTWARE
import android.view.View.LAYOUT_DIRECTION_INHERIT
import android.view.View.LAYOUT_DIRECTION_LOCALE
import android.view.View.LAYOUT_DIRECTION_LTR
import android.view.View.LAYOUT_DIRECTION_RTL
import android.view.View.OVER_SCROLL_ALWAYS
import android.view.View.OVER_SCROLL_IF_CONTENT_SCROLLS
import android.view.View.OVER_SCROLL_NEVER
import android.view.View.SCROLLBARS_INSIDE_INSET
import android.view.View.SCROLLBARS_INSIDE_OVERLAY
import android.view.View.SCROLLBARS_OUTSIDE_INSET
import android.view.View.SCROLLBARS_OUTSIDE_OVERLAY
import android.view.View.SCROLLBAR_POSITION_DEFAULT
import android.view.View.SCROLLBAR_POSITION_LEFT
import android.view.View.SCROLLBAR_POSITION_RIGHT
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.View.TEXT_ALIGNMENT_GRAVITY
import android.view.View.TEXT_ALIGNMENT_INHERIT
import android.view.View.TEXT_ALIGNMENT_TEXT_END
import android.view.View.TEXT_ALIGNMENT_TEXT_START
import android.view.View.TEXT_ALIGNMENT_VIEW_END
import android.view.View.TEXT_ALIGNMENT_VIEW_START
import android.view.View.TEXT_DIRECTION_ANY_RTL
import android.view.View.TEXT_DIRECTION_FIRST_STRONG
import android.view.View.TEXT_DIRECTION_INHERIT
import android.view.View.TEXT_DIRECTION_LOCALE
import android.view.View.TEXT_DIRECTION_LTR
import android.view.View.TEXT_DIRECTION_RTL
import android.view.View.VISIBLE
import cz.vladad.assertj.android.internal.BitmaskUtils.buildNamedValueString
import org.assertj.core.api.Assertions.assertThat

@Suppress("unused")
abstract class AbstractViewAssert<S : AbstractViewAssert<S, A>, A : View> protected constructor(
    actual: A,
    selfType: Class<S>
) : AbstractAssert<S, A>(actual, selfType) {

    companion object {

        fun visibilityToString(@ViewVisibility visibility: Int): String {
            return buildNamedValueString(visibility.toLong())
                .value(VISIBLE.toLong(), "visible")
                .value(INVISIBLE.toLong(), "invisible")
                .value(GONE.toLong(), "gone")
                .get()
        }

        fun layerTypeToString(@ViewLayerType type: Int): String {
            return buildNamedValueString(type.toLong())
                .value(LAYER_TYPE_NONE.toLong(), "none")
                .value(LAYER_TYPE_SOFTWARE.toLong(), "software")
                .value(LAYER_TYPE_HARDWARE.toLong(), "hardware")
                .get()
        }

        @TargetApi(JELLY_BEAN_MR1)
        fun layoutDirectionToString(@ViewLayoutDirection direction: Int): String {
            return buildNamedValueString(direction.toLong())
                .value(LAYOUT_DIRECTION_RTL.toLong(), "right_to_left")
                .value(LAYOUT_DIRECTION_LTR.toLong(), "left_to_right")
                .value(LAYOUT_DIRECTION_INHERIT.toLong(), "inherit")
                .value(LAYOUT_DIRECTION_LOCALE.toLong(), "locale")
                .get()
        }

        fun overScrollModeToString(@ViewOverscrollMode mode: Int): String {
            return buildNamedValueString(mode.toLong())
                .value(OVER_SCROLL_ALWAYS.toLong(), "always")
                .value(OVER_SCROLL_IF_CONTENT_SCROLLS.toLong(), "ifContentScrolls")
                .value(OVER_SCROLL_NEVER.toLong(), "never")
                .get()
        }

        fun scrollBarStyleToString(@ViewScrollBarStyle style: Int): String {
            return buildNamedValueString(style.toLong())
                .value(SCROLLBARS_INSIDE_INSET.toLong(), "insideInset")
                .value(SCROLLBARS_INSIDE_OVERLAY.toLong(), "insideOverlay")
                .value(SCROLLBARS_OUTSIDE_INSET.toLong(), "outsideInset")
                .value(SCROLLBARS_OUTSIDE_OVERLAY.toLong(), "outsideOverlay")
                .get()
        }

        fun verticalScrollBarPositionToString(@ViewScrollBarPosition position: Int): String {
            return buildNamedValueString(position.toLong())
                .value(SCROLLBAR_POSITION_DEFAULT.toLong(), "default")
                .value(SCROLLBAR_POSITION_LEFT.toLong(), "left")
                .value(SCROLLBAR_POSITION_RIGHT.toLong(), "right")
                .get()
        }

        @TargetApi(JELLY_BEAN_MR1)
        fun textAlignmentToString(@ViewTextAlignment alignment: Int): String {
            return buildNamedValueString(alignment.toLong())
                .value(TEXT_ALIGNMENT_INHERIT.toLong(), "inherit")
                .value(TEXT_ALIGNMENT_GRAVITY.toLong(), "gravity")
                .value(TEXT_ALIGNMENT_TEXT_START.toLong(), "text_start")
                .value(TEXT_ALIGNMENT_TEXT_END.toLong(), "text_end")
                .value(TEXT_ALIGNMENT_CENTER.toLong(), "center")
                .value(TEXT_ALIGNMENT_VIEW_START.toLong(), "view_start")
                .value(TEXT_ALIGNMENT_VIEW_END.toLong(), "view_end")
                .get()
        }

        @TargetApi(JELLY_BEAN_MR1)
        fun textDirectionToString(@ViewTextDirection direction: Int): String {
            return buildNamedValueString(direction.toLong())
                .value(TEXT_DIRECTION_INHERIT.toLong(), "inherit")
                .value(TEXT_DIRECTION_FIRST_STRONG.toLong(), "first_strong")
                .value(TEXT_DIRECTION_ANY_RTL.toLong(), "any_right_to_left")
                .value(TEXT_DIRECTION_LTR.toLong(), "left_to_right")
                .value(TEXT_DIRECTION_RTL.toLong(), "right_to_left")
                .value(TEXT_DIRECTION_LOCALE.toLong(), "locale")
                .get()
        }
    }

    fun isKeepingScreenOn(): S {
        isNotNull
        assertThat(actual.keepScreenOn)
            .overridingErrorMessage("Expected to be keeping screen on but was not")
            .isTrue()
        return myself
    }

    fun isNotKeepingScreenOn(): S {
        isNotNull
        assertThat(actual.keepScreenOn)
            .overridingErrorMessage("Expected to not be keeping screen on but was")
            .isFalse()
        return myself
    }

    fun isVisible(): S {
        isNotNull
        val actualVisibility = actual.visibility
        assertThat(actualVisibility)
            .overridingErrorMessage(
                "Expected to be visible but was %s",
                visibilityToString(actualVisibility)
            )
            .isEqualTo(VISIBLE)
        return myself
    }

    fun isNotVisible(): S {
        isNotNull
        val actualVisibility = actual.visibility
        assertThat(actualVisibility)
            .overridingErrorMessage("Expected to be not visible but was visible")
            .isNotEqualTo(VISIBLE)
        return myself
    }

    fun isInvisible(): S {
        isNotNull
        val actualVisibility = actual.visibility
        assertThat(actualVisibility)
            .overridingErrorMessage(
                "Expected to be invisible but was %s",
                visibilityToString(actualVisibility)
            )
            .isEqualTo(INVISIBLE)
        return myself
    }

    fun isNotInvisible(): S {
        isNotNull
        val actualVisibility = actual.visibility
        assertThat(actualVisibility)
            .overridingErrorMessage("Expected to be not invisible but was invisible")
            .isNotEqualTo(INVISIBLE)
        return myself
    }

    fun isGone(): S {
        isNotNull
        val actualVisibility = actual.visibility
        assertThat(actualVisibility)
            .overridingErrorMessage(
                "Expected to be gone but was %s",
                visibilityToString(actualVisibility)
            )
            .isEqualTo(GONE)
        return myself
    }

    fun isNotGone(): S {
        isNotNull
        val actualVisibility = actual.visibility
        assertThat(actualVisibility)
            .overridingErrorMessage("Expected to be not gone but was gone")
            .isNotEqualTo(GONE)
        return myself
    }

    fun isInFocusedWindow(): S {
        isNotNull
        assertThat(actual.hasWindowFocus())
            .overridingErrorMessage("Expected to be in a focused window but was not")
            .isTrue()
        return myself
    }

    fun isNotInFocusedWindow(): S {
        isNotNull
        assertThat(actual.hasWindowFocus())
            .overridingErrorMessage("Expected to not be in a focused window but was")
            .isFalse()
        return myself
    }

    fun isActivated(): S {
        isNotNull
        assertThat(actual.isActivated)
            .overridingErrorMessage("Expected to be activated but was not")
            .isTrue()
        return myself
    }

    fun isNotActivated(): S {
        isNotNull
        assertThat(actual.isActivated)
            .overridingErrorMessage("Expected to not be activated but was")
            .isFalse()
        return myself
    }

    fun isClickable(): S {
        isNotNull
        assertThat(actual.isClickable)
            .overridingErrorMessage("Expected to be clickable but was not")
            .isTrue()
        return myself
    }

    fun isNotClickable(): S {
        isNotNull
        assertThat(actual.isClickable)
            .overridingErrorMessage("Expected to not be clickable but was")
            .isFalse()
        return myself
    }

    fun isDirty(): S {
        isNotNull
        assertThat(actual.isDirty)
            .overridingErrorMessage("Expected to be dirty but was not")
            .isTrue()
        return myself
    }

    fun isNotDirty(): S {
        isNotNull
        assertThat(actual.isDirty)
            .overridingErrorMessage("Expected to not be dirty but was")
            .isFalse()
        return myself
    }


    fun isDuplicatingParentState(): S {
        isNotNull
        assertThat(actual.isDuplicateParentStateEnabled)
            .overridingErrorMessage("Expected to be duplicating parent state but was not")
            .isTrue()
        return myself
    }

    fun isNotDuplicatingParentState(): S {
        isNotNull
        assertThat(actual.isDuplicateParentStateEnabled)
            .overridingErrorMessage("Expected to not be duplicated parent state but was")
            .isFalse()
        return myself
    }

    fun isEnabled(): S {
        isNotNull
        assertThat(actual.isEnabled)
            .overridingErrorMessage("Expected to be enabled but was disabled")
            .isTrue()
        return myself
    }

    fun isDisabled(): S {
        isNotNull
        assertThat(actual.isEnabled)
            .overridingErrorMessage("Expected to be disabled but was enabled")
            .isFalse()
        return myself
    }

    fun isFocusable(): S {
        isNotNull
        assertThat(actual.isFocusable)
            .overridingErrorMessage("Expected to be focusable but was not")
            .isTrue()
        return myself
    }

    fun isNotFocusable(): S {
        isNotNull
        assertThat(actual.isFocusable)
            .overridingErrorMessage("Expected to not be focusable but was")
            .isFalse()
        return myself
    }

    fun isFocusableInTouchMode(): S {
        isNotNull
        assertThat(actual.isFocusableInTouchMode)
            .overridingErrorMessage("Expected to be focusable in touch mode but was not")
            .isTrue()
        return myself
    }

    fun isNotFocusableInTouchMode(): S {
        isNotNull
        assertThat(actual.isFocusableInTouchMode)
            .overridingErrorMessage("Expected to not be focusable in touch mode but was")
            .isFalse()
        return myself
    }

    fun isFocused(): S {
        isNotNull
        assertThat(actual.isFocused)
            .overridingErrorMessage("Expected to be focused but was not")
            .isTrue()
        return myself
    }

    fun isNotFocused(): S {
        isNotNull
        assertThat(actual.isFocused)
            .overridingErrorMessage("Expected to not be focused but was")
            .isFalse()
        return myself
    }

    fun isHardwareAccelerated(): S {
        isNotNull
        assertThat(actual.isHardwareAccelerated)
            .overridingErrorMessage("Expected to be hardware accelerated but was not")
            .isTrue()
        return myself
    }

    fun isNotHardwareAccelerated(): S {
        isNotNull
        assertThat(actual.isHardwareAccelerated)
            .overridingErrorMessage("Expected to not be hardware accelerated but was")
            .isFalse()
        return myself
    }

    fun isHovered(): S {
        isNotNull
        assertThat(actual.isHovered)
            .overridingErrorMessage("Expected to be hovered but was not")
            .isTrue()
        return myself
    }

    fun isNotHovered(): S {
        isNotNull
        assertThat(actual.isHovered)
            .overridingErrorMessage("Expected to not be hoevered but was")
            .isFalse()
        return myself
    }

    fun isInEditMode(): S {
        isNotNull
        assertThat(actual.isInEditMode)
            .overridingErrorMessage("Expected to be in edit mode but was not")
            .isTrue()
        return myself
    }

    fun isNotInEditMode(): S {
        isNotNull
        assertThat(actual.isInEditMode)
            .overridingErrorMessage("Expected to not be in edit mode but was")
            .isFalse()
        return myself
    }

    @TargetApi(JELLY_BEAN_MR2)
    fun isInLayout(): S {
        isNotNull
        assertThat(actual.isInLayout)
            .overridingErrorMessage("Expected to be in layout but was not.")
            .isTrue()
        return myself
    }

    @TargetApi(JELLY_BEAN_MR2)
    fun isNotInLayout(): S {
        isNotNull
        assertThat(actual.isInLayout)
            .overridingErrorMessage("Expect to not be in layout but was.")
            .isFalse()
        return myself
    }

    fun isInTouchMode(): S {
        isNotNull
        assertThat(actual.isInTouchMode)
            .overridingErrorMessage("Expected to be in touch mode but was not")
            .isTrue()
        return myself
    }

    fun isNotInTouchMode(): S {
        isNotNull
        assertThat(actual.isInTouchMode)
            .overridingErrorMessage("Expected to not be in touch mode but was")
            .isFalse()
        return myself
    }

    fun isLongClickable(): S {
        isNotNull
        assertThat(actual.isLongClickable)
            .overridingErrorMessage("Expected to be long-clickable but was not")
            .isTrue()
        return myself
    }

    fun isNotLongClickable(): S {
        isNotNull
        assertThat(actual.isLongClickable)
            .overridingErrorMessage("Expected to not be long-clickable but was")
            .isFalse()
        return myself
    }

    fun isOpaque(): S {
        isNotNull
        assertThat(actual.isOpaque)
            .overridingErrorMessage("Expected to be opaque but was not")
            .isTrue()
        return myself
    }

    fun isNotOpaque(): S {
        isNotNull
        assertThat(actual.isOpaque)
            .overridingErrorMessage("Expected to not be opaque but was")
            .isFalse()
        return myself
    }

    fun isPressed(): S {
        isNotNull
        assertThat(actual.isPressed)
            .overridingErrorMessage("Expected to be pressed but was not")
            .isTrue()
        return myself
    }

    fun isNotPressed(): S {
        isNotNull
        assertThat(actual.isPressed)
            .overridingErrorMessage("Expected to not be pressed but was")
            .isFalse()
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun isScrollContainer(): S {
        isNotNull
        assertThat(actual.isScrollContainer)
            .overridingErrorMessage("Expected to be a scroll container but was not")
            .isTrue()
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun isNotScrollContainer(): S {
        isNotNull
        assertThat(actual.isScrollContainer)
            .overridingErrorMessage("Expected to not be a scroll container but was")
            .isFalse()
        return myself
    }

    fun isSelected(): S {
        isNotNull
        assertThat(actual.isSelected)
            .overridingErrorMessage("Expected to be selected but was not")
            .isTrue()
        return myself
    }

    fun isNotSelected(): S {
        isNotNull
        assertThat(actual.isSelected)
            .overridingErrorMessage("Expected to not be selected but was")
            .isFalse()
        return myself
    }

    fun isShown(): S {
        isNotNull
        assertThat(actual.isShown)
            .overridingErrorMessage("Expected to be shown but was not")
            .isTrue()
        return myself
    }

    fun isNotShown(): S {
        isNotNull
        assertThat(actual.isShown)
            .overridingErrorMessage("Expected to not be shown but was")
            .isFalse()
        return myself
    }

    @TargetApi(KITKAT)
    fun isAttachedToWindow(): S {
        isNotNull
        assertThat(actual.isAttachedToWindow)
            .overridingErrorMessage("Expected to be attached to window but was not.")
            .isTrue()
        return myself
    }

    @TargetApi(KITKAT)
    fun isNotAttachedToWindow(): S {
        isNotNull
        assertThat(actual.isAttachedToWindow)
            .overridingErrorMessage("Expected to be not attached to window but was.")
            .isFalse()
        return myself
    }

    @TargetApi(KITKAT)
    fun isLaidOut(): S {
        isNotNull
        assertThat(actual.isLaidOut)
            .overridingErrorMessage("Expected to be laid out but was not.")
            .isTrue()
        return myself
    }

    @TargetApi(KITKAT)
    fun isNotLaidOut(): S {
        isNotNull
        assertThat(actual.isLaidOut)
            .overridingErrorMessage("Expected to be not laid out but was.")
            .isFalse()
        return myself
    }

    fun hasAlpha(alpha: Float): S {
        isNotNull
        val actualAlpha = actual.alpha
        assertThat(actualAlpha)
            .overridingErrorMessage("Expected alpha <%s> but was <%s>", alpha, actualAlpha)
            .isEqualTo(alpha)
        return myself
    }

    fun hasAnimation(animation: Animation): S {
        isNotNull
        val actualAnimation = actual.animation
        assertThat(actualAnimation)
            .overridingErrorMessage(
                "Expected animation <%s> but was <%s>", animation,
                actualAnimation
            )
            .isSameAs(animation)
        return myself
    }

    fun hasBackground(background: Drawable): S {
        isNotNull
        val actualDrawable = actual.background
        assertThat(actualDrawable)
            .overridingErrorMessage(
                "Expected background <%s> but was <%s>", background,
                actualDrawable
            )
            .isSameAs(background)
        return myself
    }

    fun hasBaseline(baseline: Int): S {
        isNotNull
        val actualBaseline = actual.baseline
        assertThat(actualBaseline)
            .overridingErrorMessage("Expected baseline <%s> but was <%s>", baseline, actualBaseline)
            .isEqualTo(baseline)
        return myself
    }

    fun hasBottom(bottom: Int): S {
        isNotNull
        val actualBottom = actual.bottom
        assertThat(actualBottom)
            .overridingErrorMessage("Expected bottom <%s> but was <%s>", bottom, actualBottom)
            .isEqualTo(bottom)
        return myself
    }

    fun hasContentDescription(contentDescription: CharSequence): S {
        isNotNull
        val actualContentDescription = actual.contentDescription
        assertThat(actualContentDescription)
            .overridingErrorMessage(
                "Expected content description <%s> but was <%s>",
                contentDescription, actualContentDescription
            )
            .isEqualTo(contentDescription)
        return myself
    }

    fun hasContentDescription(resId: Int): S {
        isNotNull
        return hasContentDescription(actual.context.getString(resId))
    }


    @TargetApi(LOLLIPOP)
    fun hasElevation(elevation: Float): S {
        isNotNull
        val actualElevation = actual.elevation
        assertThat(actualElevation)
            .overridingErrorMessage(
                "Expected elevation <%s> but was <%s>", elevation,
                actualElevation
            )
            .isEqualTo(elevation)
        return myself
    }

    fun hasHeight(height: Int): S {
        isNotNull
        val actualHeight = actual.height
        assertThat(actualHeight)
            .overridingErrorMessage("Expected height <%s> but was <%s>", height, actualHeight)
            .isEqualTo(height)
        return myself
    }

    fun hasHorizontalFadingEdgeLength(length: Int): S {
        isNotNull
        val actualLength = actual.horizontalFadingEdgeLength
        assertThat(actualLength)
            .overridingErrorMessage(
                "Expected horizontal fading edge length <%s> but was <%s>", length,
                actualLength
            )
            .isEqualTo(length)
        return myself
    }

    fun hasId(id: Int): S {
        isNotNull
        val actualId = actual.id
        assertThat(actualId)
            .overridingErrorMessage(
                "Expected ID <%s> but was <%s>", Integer.toHexString(id),
                Integer.toHexString(actualId)
            )
            .isEqualTo(id)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasLabelFor(id: Int): S {
        isNotNull
        val actualId = actual.labelFor
        assertThat(actualId)
            .overridingErrorMessage("Expected to have label for ID <%s> but was <%s>", id, actualId)
            .isEqualTo(id)
        return myself
    }

    fun hasLayerType(@ViewLayerType type: Int): S {
        isNotNull
        val actualType = actual.layerType

        assertThat(actualType)
            .overridingErrorMessage(
                "Expected layer type <%s> but was <%s>", layerTypeToString(type),
                layerTypeToString(actualType)
            )
            .isEqualTo(type)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasLayoutDirection(direction: Int): S {
        isNotNull
        val actualDirection = actual.layoutDirection
        assertThat(actualDirection)
            .overridingErrorMessage(
                "Expected layout direction <%s> but was <%s>",
                layoutDirectionToString(direction), layoutDirectionToString(actualDirection)
            )
            .isEqualTo(direction)
        return myself
    }

    fun hasLeft(left: Int): S {
        isNotNull
        val actualLeft = actual.left
        assertThat(actualLeft)
            .overridingErrorMessage("Expected left <%s> but was <%s>", left, actualLeft)
            .isEqualTo(left)
        return myself
    }

    fun hasMeasuredHeight(height: Int): S {
        isNotNull
        val actualHeight = actual.measuredHeight
        assertThat(actualHeight)
            .overridingErrorMessage(
                "Expected measured height <%s> but was <%s>", height,
                actualHeight
            )
            .isEqualTo(height)
        return myself
    }

    fun hasMeasuredHeightAndState(heightAndState: Int): S {
        isNotNull
        val actualHeightAndState = actual.measuredHeightAndState
        assertThat(actualHeightAndState)
            .overridingErrorMessage(
                "Expected measured height and state <%s> but was <%s>",
                heightAndState, actualHeightAndState
            )
            .isEqualTo(heightAndState)
        return myself
    }

    fun hasMeasuredState(state: Int): S {
        isNotNull
        val actualState = actual.measuredState
        assertThat(actualState)
            .overridingErrorMessage("Expected measured state <%s> but was <%s>", state, actualState)
            .isEqualTo(state)
        return myself
    }

    fun hasMeasuredWidth(width: Int): S {
        isNotNull
        val actualWidth = actual.measuredWidth
        assertThat(actualWidth)
            .overridingErrorMessage("Expected measured width <%s> but was <%s>", width, actualWidth)
            .isEqualTo(width)
        return myself
    }

    fun hasMeasuredWidthAndState(widthAndState: Int): S {
        isNotNull
        val actualWidthAndState = actual.measuredWidthAndState
        assertThat(actualWidthAndState)
            .overridingErrorMessage(
                "Expected measured width and state <%s> but was <%s>",
                widthAndState, actualWidthAndState
            )
            .isEqualTo(widthAndState)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMinimumHeight(height: Int): S {
        isNotNull
        val actualHeight = actual.minimumHeight
        assertThat(actualHeight)
            .overridingErrorMessage(
                "Expected minimum height <%s> but was <%s>", height,
                actualHeight
            )
            .isEqualTo(height)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMinimumWidth(width: Int): S {
        isNotNull
        val actualWidth = actual.minimumWidth
        assertThat(actualWidth)
            .overridingErrorMessage("Expected minimum width <%s> but was <%s>", width, actualWidth)
            .isEqualTo(width)
        return myself
    }

    fun hasNextFocusDownId(id: Int): S {
        isNotNull
        val actualId = actual.nextFocusDownId
        assertThat(actualId)
            .overridingErrorMessage("Expected next focus down ID <%s> but was <%s>", id, actualId)
            .isEqualTo(id)
        return myself
    }

    fun hasNextFocusForwardId(id: Int): S {
        isNotNull
        val actualId = actual.nextFocusForwardId
        assertThat(actualId)
            .overridingErrorMessage("Expected next focus forward ID <%s> but was <%s>", id, actualId)
            .isEqualTo(id)
        return myself
    }

    fun hasNextFocusLeftId(id: Int): S {
        isNotNull
        val actualId = actual.nextFocusLeftId
        assertThat(actualId)
            .overridingErrorMessage("Expected next focus left ID <%s> but was <%s>", id, actualId)
            .isEqualTo(id)
        return myself
    }

    fun hasNextFocusRightId(id: Int): S {
        isNotNull
        val actualId = actual.nextFocusRightId
        assertThat(actualId)
            .overridingErrorMessage("Expected next focus right ID <%s> but was <%s>", id, actualId)
            .isEqualTo(id)
        return myself
    }

    fun hasNextFocusUpId(id: Int): S {
        isNotNull
        val actualId = actual.nextFocusUpId
        assertThat(actualId)
            .overridingErrorMessage("Expected next focus up ID <%s> but was <%s>", id, actualId)
            .isEqualTo(id)
        return myself
    }

    fun hasOverScrollMode(@ViewOverscrollMode mode: Int): S {
        isNotNull
        val actualMode = actual.overScrollMode

        assertThat(actualMode)
            .overridingErrorMessage(
                "Expected over scroll mode <%s> but was <%s>",
                overScrollModeToString(mode), overScrollModeToString(actualMode)
            )
            .isEqualTo(mode)
        return myself
    }

    fun hasPaddingBottom(padding: Int): S {
        isNotNull
        val actualPadding = actual.paddingBottom
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected padding bottom <%s> but was <%s>", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasPaddingEnd(padding: Int): S {
        isNotNull
        val actualPadding = actual.paddingEnd
        assertThat(actualPadding)
            .overridingErrorMessage("Expected padding end <%s> but was <%s>", padding, actualPadding)
            .isEqualTo(padding)
        return myself
    }

    fun hasPaddingLeft(padding: Int): S {
        isNotNull
        val actualPadding = actual.paddingLeft
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected padding left <%s> but was <%s>", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasPaddingRight(padding: Int): S {
        isNotNull
        val actualPadding = actual.paddingRight
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected padding right <%s> but was <%s>", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasPaddingStart(padding: Int): S {
        isNotNull
        val actualPadding = actual.paddingStart
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected padding start <%s> but was <%s>", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasPaddingTop(padding: Int): S {
        isNotNull
        val actualPadding = actual.paddingTop
        assertThat(actualPadding)
            .overridingErrorMessage("Expected padding top <%s> but was <%s>", padding, actualPadding)
            .isEqualTo(padding)
        return myself
    }

    fun hasParent(parent: ViewParent): S {
        isNotNull
        val actualParent = actual.parent
        assertThat(actualParent)
            .overridingErrorMessage("Expected parent <%s> but was <%s>", parent, actualParent)
            .isSameAs(parent)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasParentForAccessibility(parent: ViewParent): S {
        isNotNull
        val actualParent = actual.parentForAccessibility
        assertThat(actualParent)
            .overridingErrorMessage(
                "Expected parent for accessibility <%s> but was <%s>", parent,
                actualParent
            )
            .isSameAs(parent)
        return myself
    }

    fun hasPivotX(pivotX: Float): S {
        isNotNull
        val actualPivotX = actual.pivotX
        assertThat(actualPivotX)
            .overridingErrorMessage("Expected x pivot <%s> but was <%s>", pivotX, actualPivotX)
            .isEqualTo(pivotX)
        return myself
    }

    fun hasPivotY(pivotY: Float): S {
        isNotNull
        val actualPivotY = actual.pivotY
        assertThat(actualPivotY)
            .overridingErrorMessage("Expected y pivot <s> but was <%s>", pivotY, actualPivotY)
            .isEqualTo(pivotY)
        return myself
    }

    fun hasRight(right: Int): S {
        isNotNull
        val actualRight = actual.right
        assertThat(actualRight)
            .overridingErrorMessage("Expected right <%s> but was <%s>", right, actualRight)
            .isEqualTo(right)
        return myself
    }

    fun hasRootView(view: View): S {
        isNotNull
        val actualView = actual.rootView
        assertThat(actualView)
            .overridingErrorMessage("Expected root view <%s> but was <%s>", view, actualView)
            .isSameAs(view)
        return myself
    }

    fun hasRotation(rotation: Float): S {
        isNotNull
        val actualRotation = actual.rotation
        assertThat(actualRotation)
            .overridingErrorMessage("Expected rotation <%s> but was <%s>", rotation, actualRotation)
            .isEqualTo(rotation)
        return myself
    }

    fun hasRotationX(rotation: Float): S {
        isNotNull
        val actualRotation = actual.rotationX
        assertThat(actualRotation)
            .overridingErrorMessage(
                "Expected x rotation <%s> but was <%s>", rotation,
                actualRotation
            )
            .isEqualTo(rotation)
        return myself
    }

    fun hasRotationY(rotation: Float): S {
        isNotNull
        val actualRotation = actual.rotationY
        assertThat(actualRotation)
            .overridingErrorMessage(
                "Expected y rotation <%s> but was <%s>", rotation,
                actualRotation
            )
            .isEqualTo(rotation)
        return myself
    }

    fun hasScaleX(scale: Float): S {
        isNotNull
        val actualScale = actual.scaleX
        assertThat(actualScale)
            .overridingErrorMessage("Expected x scale <%s> but was <%s>", scale, actualScale)
            .isEqualTo(scale)
        return myself
    }

    fun hasScaleY(scale: Float): S {
        isNotNull
        val actualScale = actual.scaleY
        assertThat(actualScale)
            .overridingErrorMessage("Expected y scale <%s> but was <%s>", scale, actualScale)
            .isEqualTo(scale)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasScrollBarDefaultDelayBeforeFade(fade: Int): S {
        isNotNull
        val actualFade = actual.scrollBarDefaultDelayBeforeFade
        assertThat(actualFade)
            .overridingErrorMessage(
                "Expected scroll bar default delay before fade <%s> but was <%s>",
                fade, actualFade
            )
            .isEqualTo(fade)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasScrollBarFadeDuration(fade: Int): S {
        isNotNull
        val actualFade = actual.scrollBarFadeDuration
        assertThat(actualFade)
            .overridingErrorMessage(
                "Expected scroll bar fade duration <%s> but was <%s>", fade,
                actualFade
            )
            .isEqualTo(fade)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasScrollBarSize(size: Int): S {
        isNotNull
        val actualSize = actual.scrollBarSize
        assertThat(actualSize)
            .overridingErrorMessage("Expected scroll bar size <%s> but was <%s>", size, actualSize)
            .isEqualTo(size)
        return myself
    }

    fun hasScrollBarStyle(@ViewScrollBarStyle style: Int): S {
        isNotNull
        val actualStyle = actual.scrollBarStyle

        assertThat(actualStyle)
            .overridingErrorMessage(
                "Expected scroll bar style <%s> but was <%s>",
                scrollBarStyleToString(style), scrollBarStyleToString(actualStyle)
            )
            .isEqualTo(style)
        return myself
    }

    fun hasScrollX(scroll: Int): S {
        isNotNull
        val actualScroll = actual.scrollX
        assertThat(actualScroll)
            .overridingErrorMessage("Expected x scroll <%s> but was <%s>", scroll, actualScroll)
            .isEqualTo(scroll)
        return myself
    }

    fun hasScrollY(scroll: Int): S {
        isNotNull
        val actualScroll = actual.scrollY
        assertThat(actualScroll)
            .overridingErrorMessage("Expected y scroll <%s> but was <%s>", scroll, actualScroll)
            .isEqualTo(scroll)
        return myself
    }

    fun hasSolidColor(color: Int): S {
        isNotNull
        val actualColor = actual.solidColor
        assertThat(actualColor)
            .overridingErrorMessage(
                "Expected solid color <%s> but was <%s>",
                Integer.toHexString(color), Integer.toHexString(actualColor)
            )
            .isEqualTo(color)
        return myself
    }

    fun hasSystemUiVisibility(visibility: Int): S {
        isNotNull
        val actualVisibility = actual.systemUiVisibility
        assertThat(actualVisibility)
            .overridingErrorMessage(
                "Expected system UI visibility <%s> but was <%s>", visibility,
                actualVisibility
            )
            .isEqualTo(visibility)
        return myself
    }

    fun hasTag(key: Int, tag: Any): S {
        isNotNull
        val actualTag = actual.getTag(key)
        assertThat(actualTag)
            .overridingErrorMessage("Expected tag <%s> at key %s but was <%s>", tag, key, actualTag)
            .isEqualTo(tag)
        return myself
    }

    fun hasTag(tag: Any): S {
        isNotNull
        val actualTag = actual.tag
        assertThat(actualTag)
            .overridingErrorMessage("Expected tag <%s> but was <%s>", tag, actualTag)
            .isEqualTo(tag)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasTextAlignment(@ViewTextAlignment alignment: Int): S {
        isNotNull
        val actualAlignment = actual.textAlignment
        assertThat(actualAlignment)
            .overridingErrorMessage(
                "Expected text alignment <%s> but was <%s>",
                textAlignmentToString(alignment), textAlignmentToString(actualAlignment)
            )
            .isEqualTo(alignment)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasTextDirection(@ViewTextDirection direction: Int): S {
        isNotNull
        val actualDirection = actual.textDirection

        assertThat(actualDirection)
            .overridingErrorMessage(
                "Expected text direction <%s> but was <%s>",
                textDirectionToString(direction), textDirectionToString(actualDirection)
            )
            .isEqualTo(direction)
        return myself
    }

    fun hasTop(top: Int): S {
        isNotNull
        val actualTop = actual.top
        assertThat(actualTop)
            .overridingErrorMessage("Expected top <%s> but was <%s>", top, actualTop)
            .isEqualTo(top)
        return myself
    }

    fun hasTranslationX(translation: Float): S {
        isNotNull
        val actualTranslation = actual.translationX
        assertThat(actualTranslation)
            .overridingErrorMessage(
                "Expected x translation <%s> but was <%s>", translation,
                actualTranslation
            )
            .isEqualTo(translation)
        return myself
    }

    fun hasTranslationY(translation: Float): S {
        isNotNull
        val actualTranslation = actual.translationY
        assertThat(actualTranslation)
            .overridingErrorMessage(
                "Expected y translation <%s> but was <%s>", translation,
                actualTranslation
            )
            .isEqualTo(translation)
        return myself
    }

    @TargetApi(LOLLIPOP)
    fun hasTranslationZ(translation: Float): S {
        isNotNull
        val actualTranslation = actual.translationZ
        assertThat(actualTranslation)
            .overridingErrorMessage(
                "Expected z translation <%s> but was <%s>", translation,
                actualTranslation
            )
            .isEqualTo(translation)
        return myself
    }

    fun hasVerticalFadingEdgeLength(length: Int): S {
        isNotNull
        val actualLength = actual.verticalFadingEdgeLength
        assertThat(actualLength)
            .overridingErrorMessage(
                "Expected vertical fading edge length <%s> but was <%s>", length,
                actualLength
            )
            .isEqualTo(length)
        return myself
    }

    fun hasVerticalScrollbarPosition(position: Int): S {
        isNotNull
        val actualPosition = actual.verticalScrollbarPosition

        assertThat(actualPosition)
            .overridingErrorMessage(
                "Expected vertical scroll bar position <%s> but was <%s>",
                verticalScrollBarPositionToString(position),
                verticalScrollBarPositionToString(actualPosition)
            )
            .isEqualTo(position)
        return myself
    }

    fun hasVerticalScrollbarWidth(width: Int): S {
        isNotNull
        val actualWidth = actual.verticalScrollbarWidth
        assertThat(actualWidth)
            .overridingErrorMessage(
                "Expected vertical scroll bar width <%s> but was <%s>", width,
                actualWidth
            )
            .isEqualTo(width)
        return myself
    }

    fun hasVisibility(@ViewVisibility visibility: Int): S {
        isNotNull
        val actualVisibility = actual.visibility
        assertThat(actualVisibility)
            .overridingErrorMessage(
                "Expected visibility <%s> but was <%s>.",
                visibilityToString(visibility), visibilityToString(actualVisibility)
            )
            .isEqualTo(visibility)
        return myself
    }

    fun hasWidth(width: Int): S {
        isNotNull
        val actualWidth = actual.width
        assertThat(actualWidth)
            .overridingErrorMessage("Expected width <%s> but was <%s>", width, actualWidth)
            .isEqualTo(width)
        return myself
    }

    fun hasWindowVisibility(visibility: Int): S {
        isNotNull
        val actualVisibility = actual.windowVisibility
        assertThat(actualVisibility)
            .overridingErrorMessage(
                "Expected window visibility <%s> but was <%s>",
                visibilityToString(visibility), visibilityToString(actualVisibility)
            )
            .isEqualTo(visibility)
        return myself
    }

    fun hasX(x: Float): S {
        isNotNull
        val actualX = actual.x
        assertThat(actualX)
            .overridingErrorMessage("Expected x <%s> but was <%s>", x, actualX)
            .isEqualTo(x)
        return myself
    }

    fun hasY(y: Float): S {
        isNotNull
        val actualY = actual.y
        assertThat(actualY)
            .overridingErrorMessage("Expected y <%s> but was <%s>", y, actualY)
            .isEqualTo(y)
        return myself
    }

    @TargetApi(LOLLIPOP)
    fun hasZ(z: Float): S {
        isNotNull
        val actualZ = actual.z
        assertThat(actualZ)
            .overridingErrorMessage("Expected z <%s> but was <%s>", z, actualZ)
            .isEqualTo(z)
        return myself
    }

    fun hasFocus(): S {
        isNotNull
        assertThat(actual.hasFocus())
            .overridingErrorMessage("Expected to have focus but was not focused")
            .isTrue()
        return myself
    }

    fun hasNoFocus(): S {
        isNotNull
        assertThat(actual.hasFocus())
            .overridingErrorMessage("Expected to not have focus but was focused")
            .isFalse()
        return myself
    }

    fun hasFocusable(): S {
        isNotNull
        assertThat(actual.hasFocusable())
            .overridingErrorMessage("Expected to have focusable but was not")
            .isTrue()
        return myself
    }

    fun hasHapticFeedbackEnabled(): S {
        isNotNull
        assertThat(actual.isHapticFeedbackEnabled)
            .overridingErrorMessage("Expected to have haptic feedback enabled but was disabled")
            .isTrue()
        return myself
    }

    fun hasHapticFeedbackDisabled(): S {
        isNotNull
        assertThat(actual.isHapticFeedbackEnabled)
            .overridingErrorMessage("Expected to have haptic feedback disabled but was enabled")
            .isFalse()
        return myself
    }

    fun hasHorizontalFadingEdgesEnabled(): S {
        isNotNull
        assertThat(actual.isHorizontalFadingEdgeEnabled)
            .overridingErrorMessage("Expected to be fading horizontal edges but was not")
            .isTrue()
        return myself
    }

    fun hasHorizontalFadingEdgesDisabled(): S {
        isNotNull
        assertThat(actual.isHorizontalFadingEdgeEnabled)
            .overridingErrorMessage("Expected to not be fading horizontal edges but was")
            .isFalse()
        return myself
    }

    fun hasHorizontalScrollbarEnabled(): S {
        isNotNull
        assertThat(actual.isHorizontalScrollBarEnabled)
            .overridingErrorMessage(
                "Expected to have horizontal scroll bar enabled but was disabled"
            )
            .isTrue()
        return myself
    }

    fun hasHorizontalScrollbarDisabled(): S {
        isNotNull
        assertThat(actual.isHorizontalScrollBarEnabled)
            .overridingErrorMessage(
                "Expected to have horizontal scroll bar disabled but was enabled"
            )
            .isFalse()
        return myself
    }

    fun hasLayoutRequested(): S {
        isNotNull
        assertThat(actual.isLayoutRequested)
            .overridingErrorMessage("Expected to have layout requested but was not")
            .isTrue()
        return myself
    }

    fun hasNoLayoutRequested(): S {
        isNotNull
        assertThat(actual.isLayoutRequested)
            .overridingErrorMessage("Expected to not have layout requested but had")
            .isFalse()
        return myself
    }

    fun hasSaveEnabled(): S {
        isNotNull
        assertThat(actual.isSaveEnabled)
            .overridingErrorMessage("Expected to have save enabled but was disabled")
            .isTrue()
        return myself
    }

    fun hasSaveDisabled(): S {
        isNotNull
        assertThat(actual.isSaveEnabled)
            .overridingErrorMessage("Expected to have save disabled but was enabled")
            .isFalse()
        return myself
    }

    fun hasSaveFromParentEnabled(): S {
        isNotNull
        assertThat(actual.isSaveFromParentEnabled)
            .overridingErrorMessage("Expected to have save from parent enabled but was disabled")
            .isTrue()
        return myself
    }

    fun hasSaveFromParentDisabled(): S {
        isNotNull
        assertThat(actual.isSaveFromParentEnabled)
            .overridingErrorMessage("Expected to have save from parent disabled but was enabled")
            .isFalse()
        return myself
    }

    fun hasScrollbarFadingEnabled(): S {
        isNotNull
        assertThat(actual.isScrollbarFadingEnabled)
            .overridingErrorMessage("Expected to have scroll bar fading enabled but was disabled")
            .isTrue()
        return myself
    }

    fun hasScrollbarFadingDisabled(): S {
        isNotNull
        assertThat(actual.isScrollbarFadingEnabled)
            .overridingErrorMessage("Expected to have scroll bar fading disabled but was enabled")
            .isFalse()
        return myself
    }

    fun hasSoundEffectsEnabled(): S {
        isNotNull
        assertThat(actual.isSoundEffectsEnabled)
            .overridingErrorMessage("Expected sound effects to be enabled but was disabled")
            .isTrue()
        return myself
    }

    fun hasSoundEffectsDisabled(): S {
        isNotNull
        assertThat(actual.isSoundEffectsEnabled)
            .overridingErrorMessage("Expected sound effects to be disabled but was enabled")
            .isFalse()
        return myself
    }

    fun hasVerticalFadingEdgeEnabled(): S {
        isNotNull
        assertThat(actual.isVerticalFadingEdgeEnabled)
            .overridingErrorMessage("Expected to have vertical fading edge enabled but was disabled")
            .isTrue()
        return myself
    }

    fun hasVerticalFadingEdgeDisabled(): S {
        isNotNull
        assertThat(actual.isVerticalFadingEdgeEnabled)
            .overridingErrorMessage("Expected to have vertical fading edge disabled but was enabled")
            .isFalse()
        return myself
    }

    fun hasVerticalScrollBarEnabled(): S {
        isNotNull
        assertThat(actual.isVerticalScrollBarEnabled)
            .overridingErrorMessage("Expected to have vertical scroll bar enabled but was disabled")
            .isTrue()
        return myself
    }

    fun hasVerticalScrollBarDisabled(): S {
        isNotNull
        assertThat(actual.isVerticalScrollBarEnabled)
            .overridingErrorMessage("Expected to have vertical scroll bar disabled but was enabled")
            .isFalse()
        return myself
    }

    @TargetApi(KITKAT)
    fun canResolveLayoutDirection(): S {
        isNotNull
        assertThat(actual.canResolveLayoutDirection())
            .overridingErrorMessage("Expected to be able to resolve layout direction but cannot.")
            .isTrue()
        return myself
    }

    @TargetApi(KITKAT)
    fun canNotResolveLayoutDirection(): S {
        isNotNull
        assertThat(actual.canResolveLayoutDirection())
            .overridingErrorMessage("Expected to not be able to resolve layout direction but can.")
            .isFalse()
        return myself
    }

    @TargetApi(KITKAT)
    fun canResolveTextAlignment(): S {
        isNotNull
        assertThat(actual.canResolveTextAlignment())
            .overridingErrorMessage("Expected to  be able to resolve text alignment but cannot.")
            .isTrue()
        return myself
    }

    @TargetApi(KITKAT)
    fun canNotResolveTextAlignment(): S {
        isNotNull
        assertThat(actual.canResolveTextAlignment())
            .overridingErrorMessage("Expected to not be able to resolve text alignment but can.")
            .isFalse()
        return myself
    }

    @TargetApi(KITKAT)
    fun canResolveTextDirection(): S {
        isNotNull
        assertThat(actual.canResolveTextDirection())
            .overridingErrorMessage("Expected to be able to resolve text direction but cannot.")
            .isTrue()
        return myself
    }

    @TargetApi(KITKAT)
    fun canNotResolveTextDirection(): S {
        isNotNull
        assertThat(actual.canResolveTextDirection())
            .overridingErrorMessage("Expected to not be able to resolve text direction but can.")
            .isFalse()
        return myself
    }

    @TargetApi(KITKAT)
    fun hasResolvedLayoutDirection(): S {
        isNotNull
        assertThat(actual.isLayoutDirectionResolved)
            .overridingErrorMessage("Expected layout direction to be resolved but was not.")
            .isTrue()
        return myself
    }

    @TargetApi(KITKAT)
    fun hasNotResolvedLayoutDirection(): S {
        isNotNull
        assertThat(actual.isLayoutDirectionResolved)
            .overridingErrorMessage("Expected layout direction to not be resolved but was.")
            .isFalse()
        return myself
    }

    @TargetApi(KITKAT)
    fun hasResolvedTextAlignment(): S {
        isNotNull
        assertThat(actual.isTextAlignmentResolved)
            .overridingErrorMessage("Expected text alignment to be resolved but was not.")
            .isTrue()
        return myself
    }

    @TargetApi(KITKAT)
    fun hasNotResolvedTextAlignment(): S {
        isNotNull
        assertThat(actual.isTextAlignmentResolved)
            .overridingErrorMessage("Expected text alignment to not be resolved but was.")
            .isFalse()
        return myself
    }

    @TargetApi(KITKAT)
    fun hasResolvedTextDirection(): S {
        isNotNull
        assertThat(actual.isTextDirectionResolved)
            .overridingErrorMessage("Expected text direction to be resolved but was not.")
            .isTrue()
        return myself
    }

    @TargetApi(KITKAT)
    fun hasNotResolvedTextDirection(): S {
        isNotNull
        assertThat(actual.isTextDirectionResolved)
            .overridingErrorMessage("Expected text direction to not be resolved but was.")
            .isFalse()
        return myself
    }
}
