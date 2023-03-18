package com.ucne.parcial2.ui.tickets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.parcial2.data.remote.dto.TicketsDto


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketsListScreen(onNewTicket: () -> Unit, viewModel: TicketsViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = { Text("Tickets", style = MaterialTheme.typography.headlineLarge) }
            )
        },
       /* floatingActionButton = {
            FloatingActionButton(
                onClick = { onNewTicket() }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Save")
            }
        },
        floatingActionButtonPosition = FabPosition.End*/
    ) {
        val uiState by viewModel.uiState.collectAsState()
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            TicketListBody(uiState.tickets)
        }
    }
}

@Composable
fun TicketListBody(ticketList: List<TicketsDto>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(ticketList) { ticket ->
                TicketRow(ticket)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketRow(ticket: TicketsDto/*, navController: NavController*/) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        //todo : Implementar swipe to delete
       // val drawerState = rememberDrawerState(DrawerValue.Closed )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row() {
                Text(
                    text = ticket.empresa,
                    style = MaterialTheme.typography.titleLarge,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = ticket.fecha,
                    style = MaterialTheme.typography.titleSmall,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(2f)
                )
            }
            Text(
                text = ticket.asunto,
                style = MaterialTheme.typography.titleLarge,
                //modifier = Modifier.weight(3f)
            )

        }
        Divider(Modifier.fillMaxWidth())
    }
}