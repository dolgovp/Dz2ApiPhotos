package com.example.dz2apiphotos.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable


@Serializable
data class Item(
    val created: LocalDate,
    val name: String,
    val id: Int,
)
