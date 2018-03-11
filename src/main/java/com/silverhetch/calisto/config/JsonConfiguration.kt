package com.silverhetch.calisto.config

import com.silverhetch.calisto.config.file.JsonFile
import java.io.File

internal class JsonConfiguration(private val jsonPath: String) : Configuration {
    private val default: Configuration = ConfigurationImpl()
    private val jsonFile: JsonFile = JsonFile(jsonPath)

    override fun workspaceFile(): File {
        try {
            return File(jsonFile.value()["workspaceFile"].asString)
        }catch (e :Exception){
            return default.workspaceFile()
        }
    }
}