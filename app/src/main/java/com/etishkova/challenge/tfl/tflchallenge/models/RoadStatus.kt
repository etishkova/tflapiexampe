package com.etishkova.challenge.tfl.tflchallenge.models

import java.util.ArrayList

data class RoadStatus(
    val type: ArrayList<String>?,
    val bounds: String,
    val displayName: String,
    val envelope: String,
    val id: String,
    val statusSeverity: String,
    val statusSeverityDescription: String,
    val url: String
)

