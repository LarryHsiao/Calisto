package com.silverhetch.calisto.config.file

import com.google.gson.JsonObject

interface Json {
    fun value(): JsonObject
}