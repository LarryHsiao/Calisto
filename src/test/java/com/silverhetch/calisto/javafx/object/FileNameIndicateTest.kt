package com.silverhetch.calisto.javafx.`object`

import com.silverhetch.calisto.javafx.ResourceBundleFactory
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.File
import java.util.*

class FileNameIndicateTest{
    @Before
    fun setUp() {
        val file = File("file")
        file.createNewFile()

        val directory = File("directory")
        directory.mkdir()
    }

    @After
    fun tearDown() {
        File("file").delete()
        File("directory").delete()
    }

    @Test
    fun fileName() {

        val formatted = FileNameIndicate( File("file"), ResourceBundle.getBundle("i18n/bundle")).value()
        assertEquals("file", formatted)
    }

    @Test
    fun directoryName() {
        val formatted = FileNameIndicate(File("directory"), ResourceBundle.getBundle("i18n/bundle")).value()
        assertEquals("directory (Folder)", formatted)
    }
}