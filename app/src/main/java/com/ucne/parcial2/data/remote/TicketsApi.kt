package com.ucne.parcial2.data.remote

import com.ucne.parcial2.data.remote.dto.TicketsDto
import retrofit2.Response
import retrofit2.http.*


interface TicketsApi {
    @GET("/api/tickets")
    suspend fun getTickets(): List<TicketsDto>
    @GET("/api/tickets/{id}")
    suspend fun getTicketsbyId(@Path("id") id: Int): TicketsDto
    @POST("/api/tickets")
    suspend fun postTicket(@Body ticketsDto: TicketsDto): TicketsDto
    @PUT("/api/tickets/{id}")
    suspend fun putTicket(@Path("id") id: Int, @Body ticketsDto: TicketsDto): Response<Unit>
    @DELETE("/api/tickets/{id}")
    suspend fun deleteticket(@Path("id") id: Int)
}