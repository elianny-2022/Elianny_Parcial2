package com.ucne.parcial2.util

sealed class Screen(val route:String){

    object TicketListScreen: Screen("ListScreen")
    object TicketScreen: Screen("TicketScreen")
}
