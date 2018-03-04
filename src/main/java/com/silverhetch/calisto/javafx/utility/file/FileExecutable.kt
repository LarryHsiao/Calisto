package com.silverhetch.calisto.javafx.utility.file

import java.awt.Desktop
import java.io.File
import java.net.URI

internal class FileExecutable : Executable {
    override fun execute(uri: URI) {
        Desktop.getDesktop().open(File(uri))
    }
}