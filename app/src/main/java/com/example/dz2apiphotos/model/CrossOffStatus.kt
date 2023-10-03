package com.example.dz2apiphotos.model

import kotlinx.serialization.Serializable

@Serializable
class CrossOffStatus(
    val success: Boolean,
    val rowsAffected: Int
)
