package com.fajar.submissioncompose.ui.navigation

sealed class Screen(val route:String){
    object Home : Screen("home")
    object Bookmark : Screen("bookmark")
    object Profile : Screen("profile")
    object DetailDisaster : Screen("home/{disaster}"){
        fun createRoute(disasterId:Long) = "home/$disasterId"
    }

}
