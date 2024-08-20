package com.example.core.network.data.models

import com.google.gson.annotations.SerializedName

class ApiResponse<T>(@SerializedName("data") val data: T)
