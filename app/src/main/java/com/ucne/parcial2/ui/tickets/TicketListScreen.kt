package com.ucne.parcial2.ui.tickets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun TicketsListScreen(
    onNewTicket: () -> Unit,
    viewModel: TicketsViewModel = hiltViewModel(), onTicketClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de Tickets",
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TicketListBody(uiState.tickets) {
                onTicketClick(it)
            }
        }
    }
}

@Composable
fun TicketListBody(ticketList: List<TicketsDto>, onTicketClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(ticketList) { ticket ->
                TicketRow(ticket) {
                    onTicketClick(it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketRow(ticket: TicketsDto, onTicketClick: (Int) -> Unit) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        //todo : Implementar swipe to delete
        // val drawerState = rememberDrawerState(DrawerValue.Closed )
        Column(
            modifier = Modifier
                .clickable(onClick = { onTicketClick(ticket.ticketId) })
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
                    text = ticket.fecha.substring(0, 10),
                    style = MaterialTheme.typography.titleSmall,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(3f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = ticket.asunto,
                    style = MaterialTheme.typography.titleLarge,
                )
                Icon(
                    imageVector = when (ticket.estatus) {
                        "Solicitado" -> {
                            Icons.Default.Star
                        }
                        "En espera" -> {
                            Icons.Default.Update
                        }
                        else -> {
                            Icons.Default.TaskAlt
                        }
                    }, contentDescription = ticket.estatus,
                    tint = when (ticket.estatus) {
                        "Solicitado" -> {
                            Color(0xFFFFAF33)
                        }
                        "En espera" -> {
                            Color.Gray
                        }
                        else -> {
                            Color.Green
                        }
                    }
                )
            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}