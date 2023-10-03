package com.example.dz2apiphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AllStatus (
    val shop_list: List<Item>,
    val success: Boolean
)