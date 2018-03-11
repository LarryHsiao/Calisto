package com.silverhetch.calisto.config.file

import com.google.gson.JsonObject

interface Json {
    fun json(): JsonObject
    fun rewrite(key: String, value: String)
}