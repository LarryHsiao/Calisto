package com.silverhetch.calisto.config.file

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.FileReader

internal class JsonFile(private val jsonPath: String) : Json {
    private var json: JsonObject? = null
    override fun value(): JsonObject {
        try {
            if (json == null) {
                JsonParser().parse(FileReader(jsonPath))
            }
            return json!!
        } catch (e: Exception) {
            return JsonObject()
        }
    }
}