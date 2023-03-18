package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketsDto
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TicketRepositoryImp @Inject constructor(
    private val api: TicketsApi

): Ticket3Repository {

    override fun getTickets(): Flow<Resource<List<TicketsDto>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val tickets =
                api.getTickets() //descarga las ocupaciones de internet, se supone quedemora algo

            emit(Resource.Success(tickets)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override suspend fun putTickets(id: Int, ticketsDto: TicketsDto) {
        api.putTicket(id,ticketsDto)
    }
    override  fun getTicketsbyId(id: Int): Flow<Resource<TicketsDto>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val tickets =
                api.getTicketsbyId(id) //descarga las ocupaciones de internet, se supone quedemora algo

            emit(Resource.Success(tickets)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }


    override suspend fun deleteTickets(id: Int) = api.deleteticket(id)
}


