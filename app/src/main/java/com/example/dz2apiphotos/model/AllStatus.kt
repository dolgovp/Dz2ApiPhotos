package com.example.dz2apiphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AllStatus (
    @SerialName("shop_list")
    val shopList: List<Item>,
    val success: Boolean
)