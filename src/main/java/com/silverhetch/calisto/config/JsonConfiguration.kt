package com.silverhetch.calisto.config

import com.silverhetch.calisto.config.file.Json
import com.silverhetch.calisto.config.file.JsonFile
import java.io.File

internal class JsonConfiguration(private val jsonFile:Json,private val default: Configuration) : Configuration {
    override fun changeHome(path: String?): File {
        jsonFile.rewrite("workspaceFile", path!!)
        return File(path)
    }

    override fun workspaceFile(): File {
        return try {
            File(jsonFile.json()["workspaceFile"].asString)
        }catch (e: Exception){
            default.workspaceFile()
        }
    }
}