package com.example.dz2apiphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ItemStatus(
    val success: Boolean,
    @SerialName("item_id")
    val itemId: Int
)