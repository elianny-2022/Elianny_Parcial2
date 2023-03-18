package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.local.entity.TicketEntity
import com.ucne.parcial2.data.local.entity.toTicketsDto
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketsDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Ticket2Repository @Inject constructor(
    private  val ticketsDto: TicketsDto,
    private  val ticketsApi: TicketsApi
) {
    suspend fun insert(ticket: TicketEntity) {

        //ticketsDto.insert(ticket) //insertar en la base de datos

        //val ticket = ticketsDto.getNoEnviadas() //buscar no enviados

        //tePrestoApi.postOcupacion(ocupacion.toOcupacionDto())

        ticketsApi.putTicket(ticket.ticketId!! ,ticket.toTicketsDto())

        /*    ocupacionesNoEnviadas.map { ocupacionEntity ->
                val dto = ocupacionEntity.toOcupacionDto()
                tePrestoApi.postOcupacion(dto)
            }*/

    }
    //suspend fun delete(ticket: TicketEntity) = ticketsDto.delete(ticket)

   // suspend fun find(ticket: TicketEntity) = ticketsDto.find(ticket)
    suspend fun putTicket(id: Int, ticketsDto: TicketsDto) = ticketsApi.putTicket(id,ticketsDto)

   // fun getTicket(): Flow<List<TicketEntity>> = ticketsDto.getT()

}
