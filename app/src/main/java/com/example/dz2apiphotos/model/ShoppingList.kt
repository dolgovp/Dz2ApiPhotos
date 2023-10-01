package com.example.dz2apiphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ShoppingList(
    val success: Boolean,
    @SerialName("item_list")
    val item_list: List<ListItem>
)