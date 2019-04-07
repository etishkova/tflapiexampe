package com.etishkova.challenge.tfl.tflchallenge.models

data class RoadStatus(
    val type: String?,
    val bounds: String = "",
    val displayName: String = "",
    val envelope: String = "",
    val id: String = "",
    val statusSeverity: String = "",
    val statusSeverityDescription: String = "",
    val url: String = "",
    val exceptionType: String = "",
    val httpStatus: String?,
    val httpStatusCode: Int = 200,
    val message: String?,
    val relativeUri: String?,
    val timestampUtc: String?
)

/*data class RequestError(
    val type: String?,
    val exceptionType: String,
    val httpStatus: String,
    val httpStatusCode: Int,
    val message: String,
    val relativeUri: String,
    val timestampUtc: String
)*/

