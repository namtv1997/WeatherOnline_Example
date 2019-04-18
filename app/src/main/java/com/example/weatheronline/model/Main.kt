package com.example.weatheronline.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Main {
    @SerializedName("temp")
    @Expose
    val temp: Double? = null
    @SerializedName("temp_min")
    @Expose
    val tempMin: Double? = null
    @SerializedName("temp_max")
    @Expose
    val tempMax: Double? = null
    @SerializedName("pressure")
    @Expose
    val pressure: Double? = null
    @SerializedName("sea_level")
    @Expose
    val seaLevel: Double? = null
    @SerializedName("grnd_level")
    @Expose
    val grndLevel: Double? = null
    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null
    @SerializedName("temp_kf")
    @Expose
    val tempKf: Float? = null
}