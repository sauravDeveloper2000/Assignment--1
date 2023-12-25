package com.example.assignment2

import androidx.lifecycle.ViewModel
import com.example.assignment2.model_class.User
import kotlin.random.Random

// Now this view model provides us list of users.
class UserViewModel: ViewModel() {

    /**
     * setOfChars :- It is a set of upper case, lower case and digits, used to generate userId.
     * setOfChars2 :- It is a set of upper case and lower case  used to generate user's full name.
     */
    private val setOfChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
    private val setOfChars2 = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
    private val emailType = listOf<String>("@gmail.com", "@yahoo.com", "@outlook.com")

    private val user = generateSequence {
        User(
            userId = Random.nextLong(10000001, 99999999),
            userName = buildString {
                repeat(6){
                    val char = setOfChars.random()
                    append(char)
                }
            },
            fullName = buildString {
                for (i in 1..10){
                    if (i == 1){
                        append(setOfChars2.random().uppercase())
                    } else {
                        append(setOfChars2.random().lowercase())
                    }
                }
                append("    ")
                for (i in 1..10){
                    if (i == 1){
                        append(setOfChars2.random().uppercase())
                    } else {
                        append(setOfChars2.random().lowercase())
                    }
                }
            },
            emailId = buildString {
                repeat(10){
                    append(setOfChars.random())
                }
                append(emailType.random())
            }
        )
    }
    val users: List<User> = user.take(100).toList()
}