package com.mihodihasan.marvelsuperheroes.utils

class Constants {
    companion object {
        const val API_HIT_THRESHOLD= 1000 * 60 * 3      /*Three Minutes in Millis*/
        const val HEROES_API_MAX_PAGE = "HEROES_API_MAX_PAGE"
        const val HEROES_API_LAST_HIT = "HEROES_API_LAST_HIT"
        const val CONTENT_API_MAX_PAGE = "CONTENT_API_MAX_PAGE"
        const val CONTENT_API_LAST_HIT = "CONTENT_API_LAST_HIT"
        const val DEFAULT_DATE_FORMAT = "EEE MMM dd HH:mm:s z yyyy"
        const val CURRENT_HERO_ID: String = "CURRENT_HERO_ID"
        const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
        const val BEAUTIFY_DATE_FORMAT = "MMM dd, yyyy"
    }
}
