package com.example.assignment3.ui.view_model_section

import androidx.lifecycle.ViewModel
import com.example.assignment3.database.User
import com.example.assignment3.repository_section.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class WelcomeAndUserListScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val setOfChars: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private val setOfChars2 = ('a'..'z') + ('A'..'Z')

    private val user = generateSequence {
        User(
            userId = Random.nextLong(10000001, 99999999),
            userName = buildString {
                repeat(6) {
                    val char = setOfChars.random()
                    append(char)
                }
            },
            fullName = buildString {
                for (i in 1..10) {
                    if (i == 1) {
                        append(setOfChars2.random().uppercase())
                    } else {
                        append(setOfChars2.random().lowercase())
                    }
                }
                append("    ")
                for (i in 1..10) {
                    if (i == 1) {
                        append(setOfChars2.random().uppercase())
                    } else {
                        append(setOfChars2.random().lowercase())
                    }
                }
            },
            emailId = buildString {
                repeat(10) {
                    append(setOfChars.random())
                }
                append("@gmail.com")
            }
        )
    }
    var users = user.take(5)
        private set
}