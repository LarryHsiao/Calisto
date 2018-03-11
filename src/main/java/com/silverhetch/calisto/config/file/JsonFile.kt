package com.silverhetch.calisto.config.file

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.stream.JsonReader
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption.*

internal class JsonFile(private val jsonPath: String) : Json {
    private var json: JsonObject? = null
    override fun json(): JsonObject {
        return try {
            if (json == null) {
                json = JsonParser().parse(JsonReader(FileReader(jsonPath))).asJsonObject
            }
            json!!
        } catch (e: Exception) {
            JsonObject()
        }
    }

    override fun rewrite(key: String, value: String) {
        val path = Paths.get(jsonPath)
        val json = json()
        json.addProperty(key, value)
        Files.write(path, json.toString().toByteArray(), CREATE, WRITE, TRUNCATE_EXISTING)
    }
}