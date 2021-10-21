package com.hepsiburada.dgrubuodev2.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidationUtil {

    fun validateUsername(input: String): Boolean {
        val username = input
        val pattern: Pattern
        val matcher: Matcher

        val usernamePattern = "[a-z0-9_]+"
        pattern = Pattern.compile(usernamePattern)
        matcher = pattern.matcher(username)
        return matcher.matches()
    }

    fun validateEmail(input: String): Boolean {
        val email = input
        val pattern: Pattern
        val matcher: Matcher

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z0-9]+\\.[a-z0-9]{2}+\\.?[a-z]+"
        pattern = Pattern.compile(emailPattern)
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun validatePassword(input: String): Boolean {
        var result = false

        if (input.matches("(.*[:?!@#$%^&*()].*)".toRegex())
            && input.matches("(.*[A-Z].*)".toRegex())
                 && input.matches("(.*[a-z].*)".toRegex())
                        && input.matches("(.*[0-9].*)".toRegex())
        )
            result = true

        return result
    }
}