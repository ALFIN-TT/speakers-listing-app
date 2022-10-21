package com.example.speakers.common.network

import java.io.IOException

open class ApiException(message: String) : IOException(message)

class NoInternetException(message: String) : ApiException(message)