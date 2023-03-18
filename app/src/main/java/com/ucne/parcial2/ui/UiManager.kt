package com.ucne.parcial2.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ucne.parcial2.ui.tickets.TicketScreen
import com.ucne.parcial2.ui.tickets.TicketsListScreen
import com.ucne.parcial2.util.Screen

@Composable
fun UiManager(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.TicketListScreen.route){
        composable(route = Screen.TicketListScreen.route){
            TicketsListScreen(onNewTicket = {})
        }
        composable(route = Screen.TicketScreen.route){
            TicketScreen(ticketId = 0)
        }
    }
}