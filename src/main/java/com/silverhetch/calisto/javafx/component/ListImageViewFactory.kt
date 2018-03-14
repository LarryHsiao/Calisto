package com.silverhetch.calisto.javafx.component

import javafx.scene.image.Image
import javafx.scene.image.ImageView

class ListImageViewFactory(private val uri: String) {
    companion object {
        const val IMAGE_SIZE = 25
    }

    fun imageView(): ImageView? {
        return try {
            val image = Image(uri, IMAGE_SIZE.toDouble(), IMAGE_SIZE.toDouble(), true, true, true)
            val imageView = ImageView(image)
            imageView.fitHeight = IMAGE_SIZE.toDouble()
            imageView.fitWidth = IMAGE_SIZE.toDouble()
            imageView
        } catch (ignore: Exception) {
            null
        }
    }
}
