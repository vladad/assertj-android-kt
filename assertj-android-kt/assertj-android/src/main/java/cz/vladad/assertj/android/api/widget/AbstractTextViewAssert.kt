package cz.vladad.assertj.android.api.widget

import android.annotation.TargetApi
import android.graphics.Typeface
import android.text.TextUtils
import android.widget.TextView
import java.util.regex.Pattern
import cz.vladad.assertj.android.api.view.AbstractViewAssert

import android.os.Build.VERSION_CODES.HONEYCOMB
import android.os.Build.VERSION_CODES.JELLY_BEAN
import android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
import android.view.Gravity.BOTTOM
import android.view.Gravity.CENTER
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.Gravity.CENTER_VERTICAL
import android.view.Gravity.CLIP_HORIZONTAL
import android.view.Gravity.CLIP_VERTICAL
import android.view.Gravity.END
import android.view.Gravity.FILL
import android.view.Gravity.FILL_HORIZONTAL
import android.view.Gravity.FILL_VERTICAL
import android.view.Gravity.LEFT
import android.view.Gravity.NO_GRAVITY
import android.view.Gravity.RIGHT
import android.view.Gravity.START
import android.view.Gravity.TOP
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.view.inputmethod.EditorInfo.IME_ACTION_GO
import android.view.inputmethod.EditorInfo.IME_ACTION_NEXT
import android.view.inputmethod.EditorInfo.IME_ACTION_NONE
import android.view.inputmethod.EditorInfo.IME_ACTION_PREVIOUS
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.view.inputmethod.EditorInfo.IME_ACTION_SEND
import android.view.inputmethod.EditorInfo.IME_ACTION_UNSPECIFIED
import android.view.inputmethod.EditorInfo.IME_FLAG_FORCE_ASCII
import android.view.inputmethod.EditorInfo.IME_FLAG_NAVIGATE_NEXT
import android.view.inputmethod.EditorInfo.IME_FLAG_NAVIGATE_PREVIOUS
import android.view.inputmethod.EditorInfo.IME_FLAG_NO_ACCESSORY_ACTION
import android.view.inputmethod.EditorInfo.IME_FLAG_NO_ENTER_ACTION
import android.view.inputmethod.EditorInfo.IME_FLAG_NO_EXTRACT_UI
import android.view.inputmethod.EditorInfo.IME_FLAG_NO_FULLSCREEN
import android.view.inputmethod.EditorInfo.IME_NULL
import cz.vladad.assertj.android.internal.BitmaskUtils.buildBitMaskString
import org.assertj.core.api.Assertions.assertThat
import java.util.*

abstract class AbstractTextViewAssert<S : AbstractTextViewAssert<S, A>, A : TextView> protected constructor(
    actual: A,
    selfType: Class<S>
) : AbstractViewAssert<S, A>(actual, selfType) {

    @TargetApi(JELLY_BEAN)
    fun isIncludingFontPadding(): S {
        isNotNull
        assertThat(actual.includeFontPadding)
            .overridingErrorMessage("Expected to be including font padding but was not.")
            .isTrue()
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun isNotIncludingFontPadding(): S {
        isNotNull
        assertThat(actual.includeFontPadding)
            .overridingErrorMessage("Expected to not be including font padding but was.")
            .isFalse()
        return myself
    }

    fun isEmpty(): S {
        isNotNull
        return hasTextString("")
    }

    fun isNotEmpty(): S {
        isNotNull
        val text = actual.text
        assertThat(text)
            .overridingErrorMessage("Expected empty text but was <%s>.", text)
            .isNotEqualTo("")
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun isCursorVisible(): S {
        isNotNull
        assertThat(actual.isCursorVisible)
            .overridingErrorMessage("Expected cursor to be visible but was not visible.")
            .isTrue()
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun isCursorNotVisible(): S {
        isNotNull
        assertThat(actual.isCursorVisible)
            .overridingErrorMessage("Expected cursor to not be visible but was visible.")
            .isFalse()
        return myself
    }

    fun isInputMethodTarget(): S {
        isNotNull
        assertThat(actual.isInputMethodTarget)
            .overridingErrorMessage("Expected to be the input method target but was not.")
            .isTrue()
        return myself
    }

    fun isNotInputMethodTarget(): S {
        isNotNull
        assertThat(actual.isInputMethodTarget)
            .overridingErrorMessage("Expected to not be the input method target but was.")
            .isFalse()
        return myself
    }

    fun hasAutoLinkMask(mask: Int): S {
        isNotNull
        val actualMask = actual.autoLinkMask
        assertThat(actualMask)
            .overridingErrorMessage("Expected auto-link mask <%s> but was <%s>.", mask, actualMask)
            .isEqualTo(mask)
        return myself
    }

    fun hasCompoundDrawablePadding(padding: Int): S {
        isNotNull
        val actualPadding = actual.compoundDrawablePadding
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected compound drawable padding <%s> but was <%s>.", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasCompoundPaddingBottom(padding: Int): S {
        isNotNull
        val actualPadding = actual.compoundPaddingBottom
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected compound drawable bottom padding <%s> but was <%s>.",
                padding, actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasCompoundPaddingEnd(padding: Int): S {
        isNotNull
        val actualPadding = actual.compoundPaddingEnd
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected compound drawable end padding <%s> but was <%s>.",
                padding, actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasCompoundPaddingLeft(padding: Int): S {
        isNotNull
        val actualPadding = actual.compoundPaddingLeft
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected compound drawable left padding <%s> but was <%s>.",
                padding, actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasCompoundPaddingRight(padding: Int): S {
        isNotNull
        val actualPadding = actual.compoundPaddingRight
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected compound drawable right padding <%s> but was <%s>.",
                padding, actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasCompoundPaddingStart(padding: Int): S {
        isNotNull
        val actualPadding = actual.compoundPaddingStart
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected compound drawable start padding <%s> but was <%s>.",
                padding, actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasCompoundPaddingTop(padding: Int): S {
        isNotNull
        val actualPadding = actual.compoundPaddingTop
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected compound drawable top padding <%s> but was <%s>.",
                padding, actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasCurrentHintTextColor(color: Int): S {
        isNotNull
        val actualColor = actual.currentHintTextColor
        assertThat(actualColor)
            .overridingErrorMessage(
                "Expected current hint text color <%s> but was <%s>.",
                Integer.toHexString(color), Integer.toHexString(actualColor)
            )
            .isEqualTo(color)
        return myself
    }

    fun hasCurrentTextColor(color: Int): S {
        isNotNull
        val actualColor = actual.currentTextColor
        assertThat(actualColor)
            .overridingErrorMessage(
                "Expected current text color <%s> but was <%s>.",
                Integer.toHexString(color), Integer.toHexString(actualColor)
            )
            .isEqualTo(color)
        return myself
    }

    fun hasEllipsize(truncation: TextUtils.TruncateAt): S {
        isNotNull
        val actualTruncation = actual.ellipsize
        assertThat(actualTruncation)
            .overridingErrorMessage(
                "Expected ellipsize <%s> but was <%s>.", truncation,
                actualTruncation
            )
            .isEqualTo(truncation)
        return myself
    }

    fun hasError(): S {
        isNotNull
        assertThat(actual.error)
            .overridingErrorMessage("Expected error but had none.")
            .isNotNull()
        return myself
    }

    fun hasNoError(): S {
        isNotNull
        assertThat(actual.error)
            .overridingErrorMessage("Expected no error but had one.")
            .isNull()
        return myself
    }

    fun hasError(error: CharSequence): S {
        isNotNull
        val actualError = actual.error
        assertThat(actualError)
            .overridingErrorMessage("Expected error <%s> but was <%s>.", error, actualError)
            .isEqualTo(error)
        return myself
    }

    fun hasError(resId: Int): S {
        isNotNull
        return hasError(actual.context.getString(resId))
    }

    fun hasExtendedPaddingBottom(padding: Int): S {
        isNotNull
        val actualPadding = actual.extendedPaddingBottom
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected extended bottom padding <%s> but was <%s>.", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasExtendedPaddingTop(padding: Int): S {
        isNotNull
        val actualPadding = actual.extendedPaddingTop
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected extended top padding <%s> but was <%s>.", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasFreezesText(freezes: Boolean): S {
        isNotNull
        val actualFreezes = actual.freezesText
        assertThat(actualFreezes)
            .overridingErrorMessage(
                "Expected freezes text <%s> but was <%s>.", freezes,
                actualFreezes
            )
            .isEqualTo(freezes)
        return myself
    }

    fun hasGravity(@TextViewGravity gravity: Int): S {
        isNotNull
        val actualGravity = actual.gravity

        assertThat(actualGravity)
            .overridingErrorMessage(
                "Expected gravity <%s> but was <%s>.", gravityToString(gravity),
                gravityToString(actualGravity)
            )
            .isEqualTo(gravity)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasHighlightColor(color: Int): S {
        isNotNull
        val actualColor = actual.highlightColor
        assertThat(actualColor)
            .overridingErrorMessage(
                "Expected highlight color <%s> but was <%s>.",
                Integer.toHexString(color), Integer.toHexString(actualColor)
            )
            .isEqualTo(color)
        return myself
    }

    fun hasHint(hint: CharSequence): S {
        isNotNull
        val actualHint = actual.hint
        assertThat(actualHint)
            .overridingErrorMessage("Expected hint <%s> but was <%s>.", hint, actualHint)
            .isEqualTo(hint)
        return myself
    }

    fun hasHint(resId: Int): S {
        isNotNull
        return hasHint(actual.context.getString(resId))
    }

    fun hasImeActionId(id: Int): S {
        isNotNull
        val actualId = actual.imeActionId
        assertThat(actualId)
            .overridingErrorMessage("Expected IME action ID <%s> but was <%s>.", id, actualId)
            .isEqualTo(id)
        return myself
    }

    fun hasImeActionLabel(label: CharSequence): S {
        isNotNull
        val actualLabel = actual.imeActionLabel
        assertThat(actualLabel)
            .overridingErrorMessage(
                "Expected IME action label <%s> but was <%s>.", label,
                actualLabel
            )
            .isEqualTo(label)
        return myself
    }

    fun hasImeActionLabel(resId: Int): S {
        isNotNull
        return hasImeActionLabel(actual.context.getString(resId))
    }

    fun hasImeOptions(@TextViewImeOptions options: Int): S {
        isNotNull
        val actualOptions = actual.imeOptions

        assertThat(actualOptions)
            .overridingErrorMessage(
                "Expected IME options <%s> but was <%s>.",
                imeOptionsToString(options), imeOptionsToString(actualOptions)
            )
            .isEqualTo(options)
        return myself
    }

    fun hasInputType(type: Int): S {
        isNotNull
        val actualType = actual.inputType
        assertThat(actualType)
            .overridingErrorMessage("Expected input type <%s> but was <%s>.", type, actualType)
            .isEqualTo(type)
        return myself
    }

    fun hasLineCount(count: Int): S {
        isNotNull
        val actualCount = actual.lineCount
        assertThat(actualCount)
            .overridingErrorMessage("Expected line count <%s> but was <%s>.", count, actualCount)
            .isEqualTo(count)
        return myself
    }

    fun hasLineHeight(height: Int): S {
        isNotNull
        val actualHeight = actual.lineHeight
        assertThat(actualHeight)
            .overridingErrorMessage("Expected line height <%s> but was <%s>.", height, actualHeight)
            .isEqualTo(height)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasLineSpacingExtra(extra: Float): S {
        isNotNull
        val actualExtra = actual.lineSpacingExtra
        assertThat(actualExtra)
            .overridingErrorMessage(
                "Expected line spacing extra <%s> but was <%s>.", extra,
                actualExtra
            )
            .isEqualTo(extra)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasLineSpacingMultiplier(multiplier: Float): S {
        isNotNull
        val actualMultiplier = actual.lineSpacingMultiplier
        assertThat(actualMultiplier)
            .overridingErrorMessage(
                "Expected line spacing multiplier <%s> but was <%s>.", multiplier,
                actualMultiplier
            )
            .isEqualTo(multiplier)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMarqueeRepeatLimit(limit: Int): S {
        isNotNull
        val actualLimit = actual.marqueeRepeatLimit
        assertThat(actualLimit)
            .overridingErrorMessage(
                "Expected marquee repeat limit <%s> but was <%s>.", limit,
                actualLimit
            )
            .isEqualTo(limit)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMaxEms(ems: Int): S {
        isNotNull
        val actualEms = actual.maxEms
        assertThat(actualEms)
            .overridingErrorMessage("Expected maximum EMs <%s> but was <%s>.", ems, actualEms)
            .isEqualTo(ems)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMaxHeight(height: Int): S {
        isNotNull
        val actualHeight = actual.maxHeight
        assertThat(actualHeight)
            .overridingErrorMessage(
                "Expected maximum height <%s> but was <%s>.", height,
                actualHeight
            )
            .isEqualTo(height)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMaxLines(lines: Int): S {
        isNotNull
        val actualLines = actual.maxLines
        assertThat(actualLines)
            .overridingErrorMessage("Expected maximum lines <%s> but was <%s>.", lines, actualLines)
            .isEqualTo(lines)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMaxWidth(ems: Int): S {
        isNotNull
        val actualWidth = actual.maxWidth
        assertThat(actualWidth)
            .overridingErrorMessage("Expected maximum width <%s> but was <%s>.", ems, actualWidth)
            .isEqualTo(ems)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMinEms(ems: Int): S {
        isNotNull
        val actualEms = actual.minEms
        assertThat(actualEms)
            .overridingErrorMessage("Expected minimum EMs <%s> but was <%s>.", ems, actualEms)
            .isEqualTo(ems)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMinHeight(height: Int): S {
        isNotNull
        val actualHeight = actual.minHeight
        assertThat(actualHeight)
            .overridingErrorMessage(
                "Expected minimum height <%s> but was <%s>.", height,
                actualHeight
            )
            .isEqualTo(height)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMinLines(lines: Int): S {
        isNotNull
        val actualLines = actual.minLines
        assertThat(actualLines)
            .overridingErrorMessage("Expected minimum lines <%s> but was <%s>.", lines, actualLines)
            .isEqualTo(lines)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasMinWidth(ems: Int): S {
        isNotNull
        val actualWidth = actual.minWidth
        assertThat(actualWidth)
            .overridingErrorMessage("Expected minimum width <%s> but was <%s>.", ems, actualWidth)
            .isEqualTo(ems)
        return myself
    }

    fun hasPaintFlags(flags: Int): S {
        isNotNull
        val actualFlags = actual.paintFlags
        assertThat(actualFlags)
            .overridingErrorMessage("Expected paint flags <%s> but was <%s>.", flags, actualFlags)
            .isEqualTo(flags)
        return myself
    }

    fun hasSelectionEnd(position: Int): S {
        isNotNull
        val actualPosition = actual.selectionEnd
        assertThat(actualPosition)
            .overridingErrorMessage(
                "Expected selection end <%s> but was <%s>.", position,
                actualPosition
            )
            .isEqualTo(position)
        return myself
    }

    fun hasSelectionStart(position: Int): S {
        isNotNull
        val actualPosition = actual.selectionStart
        assertThat(actualPosition)
            .overridingErrorMessage(
                "Expected selection start <%s> but was <%s>.", position,
                actualPosition
            )
            .isEqualTo(position)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasShadowColor(color: Int): S {
        isNotNull
        val actualColor = actual.shadowColor
        assertThat(actualColor)
            .overridingErrorMessage(
                "Expected shadow color <%s> but was <%s>.",
                Integer.toHexString(color), Integer.toHexString(actualColor)
            )
            .isEqualTo(color)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasShadowDx(dx: Float): S {
        isNotNull
        val actualDx = actual.shadowDx
        assertThat(actualDx)
            .overridingErrorMessage("Expected shadow DX <%s> but was <%s>.", dx, actualDx)
            .isEqualTo(dx)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasShadowDy(dy: Float): S {
        isNotNull
        val actualDy = actual.shadowDy
        assertThat(actualDy)
            .overridingErrorMessage("Expected shadow DY <%s> but was <%s>.", dy, actualDy)
            .isEqualTo(dy)
        return myself
    }

    @TargetApi(JELLY_BEAN)
    fun hasShadowRadius(radius: Float): S {
        isNotNull
        val actualRadius = actual.shadowRadius
        assertThat(actualRadius)
            .overridingErrorMessage(
                "Expected shadow radius <%s> but was <%s>.", radius,
                actualRadius
            )
            .isEqualTo(radius)
        return myself
    }

    fun hasText(text: CharSequence): S {
        isNotNull
        val actualText = actual.text
        assertThat(actualText)
            .overridingErrorMessage("Expected text <%s> but was <%s>.", text, actualText)
            .isEqualTo(text)
        return myself
    }

    fun hasText(resId: Int): S {
        isNotNull
        return hasText(actual.context.getString(resId))
    }

    fun hasTextString(text: String): S {
        isNotNull
        val actualText = actual.text.toString()
        assertThat(actualText)
            .overridingErrorMessage("Expected text string <%s> but was <%s>.", text, actualText)
            .isEqualTo(text)
        return myself
    }

    fun hasTextString(resId: Int): S {
        isNotNull
        return hasTextString(actual.context.getString(resId))
    }

    fun matches(pattern: Pattern): S {
        isNotNull
        val text = actual.text.toString()
        assertThat(pattern.matcher(text).matches())
            .overridingErrorMessage(
                "Expected text <%s> to match <%s>, but did not.", text,
                pattern.pattern()
            )
            .isTrue()
        return myself
    }

    fun doesNotMatch(pattern: Pattern): S {
        isNotNull
        val text = actual.text.toString()
        assertThat(pattern.matcher(text).matches())
            .overridingErrorMessage(
                "Expected text <%s> to not match <%s>, but did.", text,
                pattern.pattern()
            )
            .isFalse()
        return myself
    }

    fun containsText(sequence: String): S {
        isNotNull
        assertThat(actual.text.toString()).contains(sequence)
        return myself
    }

    fun containsText(resId: Int): S {
        isNotNull
        return containsText(actual.context.getString(resId))
    }

    fun doesNotContainText(sequence: String): S {
        isNotNull
        assertThat(actual.text.toString()).doesNotContain(sequence)
        return myself
    }

    fun doesNotContainText(resId: Int): S {
        isNotNull
        return doesNotContainText(actual.context.getString(resId))
    }

    fun startsWithText(sequence: String): S {
        isNotNull
        val text = actual.text.toString()
        assertThat(text.startsWith(sequence))
            .overridingErrorMessage(
                "Expected text <%s> to start with <%s> but did not.", text,
                sequence
            )
            .isTrue()
        return myself
    }

    fun startsWithText(resId: Int): S {
        isNotNull
        return startsWithText(actual.context.getString(resId))
    }

    fun doesNotStartWithText(sequence: String): S {
        isNotNull
        val text = actual.text.toString()
        assertThat(text.startsWith(sequence))
            .overridingErrorMessage(
                "Expected text <%s> to not start with <%s> but did.", text,
                sequence
            )
            .isFalse()
        return myself
    }

    fun doesNotStartWithText(resId: Int): S {
        isNotNull
        return doesNotStartWithText(actual.context.getString(resId))
    }

    fun endsWithText(sequence: String): S {
        isNotNull
        val text = actual.text.toString()
        assertThat(text.endsWith(sequence))
            .overridingErrorMessage(
                "Expected text <%s> to end with <%s> but did not.", text,
                sequence
            )
            .isTrue()
        return myself
    }

    fun endsWithText(resId: Int): S {
        isNotNull
        return endsWithText(actual.context.getString(resId))
    }

    fun doesNotEndWithText(sequence: String): S {
        isNotNull
        val text = actual.text.toString()
        assertThat(text.endsWith(sequence))
            .overridingErrorMessage(
                "Expected text <%s> to not end with <%s> but did.", text,
                sequence
            )
            .isFalse()
        return myself
    }

    fun doesNotEndWithText(resId: Int): S {
        isNotNull
        return doesNotEndWithText(actual.context.getString(resId))
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasTextLocale(locale: Locale): S {
        isNotNull
        val actualLocale = actual.textLocale
        assertThat(actualLocale)
            .overridingErrorMessage("Expected text locale <%s> but was <%s>.", locale, actualLocale)
            .isEqualTo(locale)
        return myself
    }

    fun hasTextScaleX(scale: Float): S {
        isNotNull
        val actualScale = actual.textScaleX
        assertThat(actualScale)
            .overridingErrorMessage("Expected text X scale <%s> but was <%s>.", scale, actualScale)
            .isEqualTo(scale)
        return myself
    }

    fun hasTextSize(size: Float): S {
        isNotNull
        val actualSize = actual.textSize
        assertThat(actualSize)
            .overridingErrorMessage("Expected text size <%s> but was <%s>.", size, actualSize)
            .isEqualTo(size)
        return myself
    }

    fun hasTotalPaddingBottom(padding: Int): S {
        isNotNull
        val actualPadding = actual.totalPaddingBottom
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected total bottom padding <%s> but was <%s>.", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasTotalPaddingEnd(padding: Int): S {
        isNotNull
        val actualPadding = actual.totalPaddingEnd
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected total end padding <%s> but was <%s>.", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasTotalPaddingLeft(padding: Int): S {
        isNotNull
        val actualPadding = actual.totalPaddingLeft
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected total left padding <%s> but was <%s>.", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasTotalPaddingRight(padding: Int): S {
        isNotNull
        val actualPadding = actual.totalPaddingRight
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected total right padding <%s> but was <%s>.", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    @TargetApi(JELLY_BEAN_MR1)
    fun hasTotalPaddingStart(padding: Int): S {
        isNotNull
        val actualPadding = actual.totalPaddingStart
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected total start padding <%s> but was <%s>.", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasTotalPaddingTop(padding: Int): S {
        isNotNull
        val actualPadding = actual.totalPaddingTop
        assertThat(actualPadding)
            .overridingErrorMessage(
                "Expected total top padding <%s> but was <%s>.", padding,
                actualPadding
            )
            .isEqualTo(padding)
        return myself
    }

    fun hasTypeface(typeface: Typeface): S {
        isNotNull
        val actualTypeface = actual.typeface
        assertThat(actualTypeface)
            .overridingErrorMessage("Expected typeface <%s> but was <%s>.", typeface, actualTypeface)
            .isSameAs(typeface)
        return myself
    }

    @TargetApi(HONEYCOMB)
    fun hasSelectableText(): S {
        isNotNull
        assertThat(actual.isTextSelectable)
            .overridingErrorMessage("Expected text to be selectable but was not.")
            .isTrue()
        return myself
    }

    @TargetApi(HONEYCOMB)
    fun hasUnselectableText(): S {
        isNotNull
        assertThat(actual.isTextSelectable)
            .overridingErrorMessage("Expected text to not be selectable but was.")
            .isFalse()
        return myself
    }

    fun hasLength(length: Int): S {
        isNotNull
        val actualLength = actual.length()
        assertThat(actualLength)
            .overridingErrorMessage("Expected length <%s> but was <%s>.", length, actualLength)
            .isEqualTo(length)
        return myself
    }

    companion object {
        @JvmStatic
        fun gravityToString(@TextViewGravity gravity: Int): String {
            return buildBitMaskString(gravity.toLong())
                .flag(NO_GRAVITY.toLong(), "no_gravity")
                .flag(TOP.toLong(), "top")
                .flag(BOTTOM.toLong(), "bottom")
                .flag(LEFT.toLong(), "left")
                .flag(RIGHT.toLong(), "right")
                .flag(CENTER_VERTICAL.toLong(), "center_vertical")
                .flag(FILL_VERTICAL.toLong(), "fill_vertical")
                .flag(CENTER_HORIZONTAL.toLong(), "center_horizontal")
                .flag(FILL_HORIZONTAL.toLong(), "fill_horizontal")
                .flag(CENTER.toLong(), "center")
                .flag(FILL.toLong(), "fill")
                .flag(CLIP_VERTICAL.toLong(), "clip_vertical")
                .flag(CLIP_HORIZONTAL.toLong(), "clip_horizontal")
                .flag(START.toLong(), "start")
                .flag(END.toLong(), "end")
                .get()
        }

        @TargetApi(JELLY_BEAN)
        @JvmStatic
        fun imeOptionsToString(@TextViewImeOptions options: Int): String {
            return buildBitMaskString(options.toLong())
                .flag(IME_ACTION_UNSPECIFIED.toLong(), "action_unspecified")
                .flag(IME_ACTION_NONE.toLong(), "action_none")
                .flag(IME_ACTION_GO.toLong(), "action_go")
                .flag(IME_ACTION_SEARCH.toLong(), "action_search")
                .flag(IME_ACTION_SEND.toLong(), "action_send")
                .flag(IME_ACTION_NEXT.toLong(), "action_next")
                .flag(IME_ACTION_DONE.toLong(), "action_done")
                .flag(IME_ACTION_PREVIOUS.toLong(), "action_previous")
                .flag(IME_FLAG_NO_FULLSCREEN.toLong(), "flag_no_fullscreen")
                .flag(IME_FLAG_NAVIGATE_PREVIOUS.toLong(), "flag_navigate_previous")
                .flag(IME_FLAG_NAVIGATE_NEXT.toLong(), "flag_navigate_next")
                .flag(IME_FLAG_NO_EXTRACT_UI.toLong(), "flag_no_extract_ui")
                .flag(IME_FLAG_NO_ACCESSORY_ACTION.toLong(), "flag_no_accessory_action")
                .flag(IME_FLAG_NO_ENTER_ACTION.toLong(), "flag_no_enter_action")
                .flag(IME_FLAG_FORCE_ASCII.toLong(), "flag_force_ascii")
                .flag(IME_NULL.toLong(), "null")
                .get()
        }
    }
}
