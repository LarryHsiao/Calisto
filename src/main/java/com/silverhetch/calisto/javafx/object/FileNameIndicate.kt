package com.silverhetch.calisto.javafx.`object`

import java.io.File
import java.text.MessageFormat
import java.util.*

class FileNameIndicate(private val file:File, private val resourceBundle: ResourceBundle) {
    fun value():String{
        if (file.isDirectory){
            return MessageFormat.format(resourceBundle.getString("indicate.folder"), file.name)
        }
        return file.name
    }
}