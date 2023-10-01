package com.example.dz2apiphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RemoveStatus(
    val success: Boolean,
    @SerialName("new_value")
    val newValue: Boolean
)

