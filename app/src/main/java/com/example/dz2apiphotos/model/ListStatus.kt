package com.example.dz2apiphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ListStatus(
    val success: Boolean,
    val list_id: Int
)