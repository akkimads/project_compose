package com.example.assignment_compose
import java.lang.NumberFormatException

/**
 * Created by akash
 */
object CounterHelper {

    fun processInput(editedText: String): String {
        return try {
            val counterValue = editedText.toInt()
            "Counter = $counterValue"
        } catch (e: NumberFormatException) {
            "Invalid entry"
        }
    }
}