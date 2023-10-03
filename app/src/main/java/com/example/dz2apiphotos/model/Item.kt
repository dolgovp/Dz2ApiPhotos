package com.example.dz2apiphotos.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable


@Serializable
class Item(
    val created: String,
    val name: String,
    val id: Int,
)
