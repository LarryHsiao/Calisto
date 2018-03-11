package com.silverhetch.calisto.config

import java.io.File

internal class DefaultConfiguration : Configuration {
    override fun changeHome(path: String?): File {
        throw RuntimeException("default config modification no allow")
    }

    override fun workspaceFile(): File {
        val file = File(System.getProperty("user.home") + File.separator + ".Calisto" + File.separator)
        file.mkdirs()
        return file
    }
}