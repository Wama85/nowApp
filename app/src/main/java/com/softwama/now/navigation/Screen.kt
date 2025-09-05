package com.softwama.now.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Olvidepass : Screen("olvidepass")



}