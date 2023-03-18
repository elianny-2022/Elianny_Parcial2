package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.remote.dto.TicketsDto
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.Flow

interface Ticket3Repository {
    fun getTickets(): Flow<Resource<List<TicketsDto>>>
    suspend fun putTickets(id: Int, ticketsDto: TicketsDto)
    fun getTicketsbyId(id: Int): Flow<Resource<TicketsDto>>
    suspend fun deleteTickets(id: Int)
}