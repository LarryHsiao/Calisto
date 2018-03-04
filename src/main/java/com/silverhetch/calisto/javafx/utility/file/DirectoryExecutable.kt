package com.silverhetch.calisto.javafx.utility.file

import java.awt.Desktop
import java.net.URI

internal class DirectoryExecutable : Executable {
    override fun execute(uri: URI) {
        Desktop.getDesktop().browse(uri)
    }
}