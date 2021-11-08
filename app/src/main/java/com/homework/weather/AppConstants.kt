package com.homework.weather

class AppConstants {
    companion object {
        const val BASE_URL = "https://www.metaweather.com/"
        const val IMAGE_URL = "https://www.metaweather.com/static/img/weather/png/64/%s.png"
        const val HTTP_CONNECT_TIMEOUT = 10L
        const val HTTP_WRITE_TIMEOUT = 100L
        const val HTTP_READ_TIMEOUT = 30L
        const val NETWORK_CALL_FAIL = 5556
    }
}