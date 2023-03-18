package com.ucne.parcial2.ui.tickets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TicketScreen(ticketId:Int, viewModel: TicketsViewModel = hiltViewModel(), onSaveClick: () -> Unit) {
    remember {
        viewModel.setTicket(ticketId)
        0
    }
    Column(Modifier.fillMaxWidth()) {
        //val uiState by viewModel.uiState.collectAsState()
        TicketBody(viewModel = viewModel){
            onSaveClick()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TicketBody(
    viewModel: TicketsViewModel,
    onSaveClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = viewModel.empresa,
            label = { Text(text = "Empresa") },
            onValueChange = { viewModel.empresa = it })

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = viewModel.asunto,
            label = { Text(text = "Asunto") },
            onValueChange = { viewModel.asunto = it })

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(8.dp),
            value = viewModel.estatus,
            enabled = false, readOnly = true,
            label = { Text(text = "Estatus") },
            onValueChange = { viewModel.estatus = it })

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)

        ) {
            viewModel.opcionesEstatus.forEach { opcion ->
                DropdownMenuItem(
                    text = {
                        Text(text = opcion, textAlign = TextAlign.Center)
                    },
                    onClick = {
                        expanded = false
                        viewModel.estatus = opcion
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = viewModel.especificaciones,
            label = { Text(text = "Especificaciones") },
            onValueChange = { viewModel.especificaciones = it })

    }

    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        text = { Text("Guardar") },
        icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
        onClick = { viewModel.putTicket()
        onSaveClick()})
}