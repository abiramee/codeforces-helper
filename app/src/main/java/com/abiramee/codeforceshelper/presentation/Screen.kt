package com.abiramee.codeforceshelper.presentation

sealed class Screen(val route: String) {
    object SelectUserNameScreen: Screen("select_user_name_screen")
    object SolvedListScreen: Screen("solved_list_screen")
}