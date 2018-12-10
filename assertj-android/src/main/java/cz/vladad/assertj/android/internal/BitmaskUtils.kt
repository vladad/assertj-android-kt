package cz.vladad.assertj.android.internal

import java.util.LinkedHashMap
import org.assertj.core.util.Strings

@Suppress("unused")
object BitmaskUtils {

    /**
     * Convenience builder for printing out a human-readable list of all of the individual values
     * in a bitmask.
     */
    @JvmStatic
    fun buildBitMaskString(value: Long): BitMaskStringBuilder {
        return BitMaskStringBuilder(value)
    }

    /**
     *  Convenience builder for printing out a human-readable string of a bitmask.
     */
    @JvmStatic
    fun buildNamedValueString(value: Long): NamedValueStringBuilder {
        return NamedValueStringBuilder(value)
    }

    class BitMaskStringBuilder internal constructor(private val value: Long) {
        private val parts: MutableMap<Long, String> = LinkedHashMap()

        fun flag(flag: Long, flagName: String): BitMaskStringBuilder {
            if (value and flag != 0L) {
                if (parts.containsKey(flag)) {
                    parts[flag] = parts[flag] + "|" + flagName
                } else {
                    parts[flag] = flagName
                }
            }
            return this
        }

        fun get(): String {
            return if (value == 0L) {
                "none"
            } else {
                Strings.join(parts.values).with(", ")
            }
        }
    }

    class NamedValueStringBuilder internal constructor(private val value: Long) {
        private val valueNames: MutableMap<Long, String> = LinkedHashMap()

        fun getOrValue(): String {
            return valueNames[value] ?: value.toString()
        }

        fun get(): String {
            return valueNames[value] ?: throw IllegalStateException("Unknown value: $value")
        }

        fun value(value: Long, name: String): NamedValueStringBuilder {
            val dupe = valueNames[value]
            if (dupe != null) {
                throw IllegalStateException(
                    "Duplicate value $value with name $dupe and $name"
                )
            }
            valueNames[value] = name
            return this
        }

    }
}
