package com.snorlax.model

import java.util.*


data class Video(
    val name: String? = null,
    val coverPath: String? = null,
    val mediaPath: String? = null,
    val summary: String = ""
) {
    val designationID: String = UUID.randomUUID().toString()
}
