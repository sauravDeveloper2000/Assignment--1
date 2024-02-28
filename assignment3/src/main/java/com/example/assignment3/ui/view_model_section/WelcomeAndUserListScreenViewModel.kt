package com.example.assignment3.ui.view_model_section

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment3.database.User
import com.example.assignment3.repository_section.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class WelcomeAndUserListScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    /**
     * listOfUsers :- It represents list of users being read from db and total users consists = 5 or 1.
     */
    var listOfUsers = userRepository.getAllUsersFromDb()
        private set

    private val setOfChars: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private val setOfChars2 = ('a'..'z') + ('A'..'Z')


    /**
     *  generateAndInsertUsers() :- This function 1st read 5 values from userSequence.
     *  2nd convert it into list.
     *  3rd we insert each user into DB using forEach() and userRepo's method.
     */
    fun generateAndInsertUsers(number: Int) {
        if (number == 5){
            viewModelScope.launch {
                val users = generateAndReturnUserSequence().take(5).toList()
                users.forEach(){user ->
                    userRepository.updateOrInsertUser(user)
                }
            }
        } else{
            viewModelScope.launch {
                val user = generateAndReturnUserSequence().take(2).toList()
                user.forEach { user ->
                    userRepository.updateOrInsertUser(user)
                }
            }
        }
    }

    /**
     * generateAndReturnUsersSequence() - This function generating an sequence of user with random info
     * Like random userId, name, emailId and user name.
     */
    private fun generateAndReturnUserSequence(): Sequence<User> {
        val userSequence = generateSequence {
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
        return userSequence
    }

}
