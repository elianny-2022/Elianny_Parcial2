package com.ucne.parcial2.data.local.entity

import androidx.room.PrimaryKey
import com.ucne.parcial2.data.remote.dto.TicketsDto

data class TicketEntity (
    @PrimaryKey(autoGenerate = true)
    val ticketId: Int? = null,
    val asunto: String,
    val empresa: String,
    val encargadoId: Int,
    val especificaciones: String,
    val estatus: String,
    val fecha: String,
    val orden: Int,
    val enviado: Boolean = false
)
fun TicketEntity.toTicketsDto(): TicketsDto {
    return TicketsDto(
        ticketId = this.ticketId ?: 0,
        asunto = this.asunto,
        empresa = this.empresa,
        encargadoId = this.encargadoId,
        especificaciones = this.especificaciones,
        estatus = this.estatus,
        fecha = this.fecha,
        orden = this.orden
    )
}