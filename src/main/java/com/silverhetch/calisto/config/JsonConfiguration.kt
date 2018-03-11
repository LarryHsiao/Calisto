package com.silverhetch.calisto.config

import com.silverhetch.calisto.config.file.JsonFile
import java.io.File

internal class JsonConfiguration(private val default: Configuration) : Configuration {
    private val jsonFile: JsonFile = JsonFile(System.getProperty("user.home") + File.separator + ".calisto.config")
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