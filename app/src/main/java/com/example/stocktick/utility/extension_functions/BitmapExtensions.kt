package com.example.stocktick.utility.extension_functions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


fun Bitmap.getResizedBitmap(newWidth: Int, newHeight: Int): Bitmap? {
    val width = this.width
    val height = this.height
    val scaleWidth = newWidth.toFloat() / width
    val scaleHeight = newHeight.toFloat() / height
    // CREATE A MATRIX FOR THE MANIPULATION
    val matrix = Matrix()
    // RESIZE THE BIT MAP
    matrix.postScale(scaleWidth, scaleHeight)

    // "RECREATE" THE NEW BITMAP
    val resizedBitmap = Bitmap.createBitmap(
        this, 0, 0, width, height, matrix, false
    )
    this.recycle()
    return resizedBitmap
}

/**
 * This function should be used before uploading image to server
 * This will reduce the image*/
fun InputStream.reduceImageSize(tempFile: File) {
    try {
        // BitmapFactory options to downsize the image
        val o = BitmapFactory.Options()
        o.inJustDecodeBounds = true
        o.inSampleSize = 6
        var inputStream = this

        val REQUIRED_SIZE = 75

        // Find the correct scale value. It should be the power of 2.
        var scale = 1
        while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
            o.outHeight / scale / 2 >= REQUIRED_SIZE
        ) {
            scale *= 2
        }
        val o2 = BitmapFactory.Options()
        o2.inSampleSize = scale
        val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
        inputStream.close()

        val outputStream = FileOutputStream(tempFile)
        selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 30, outputStream)
        outputStream.close()


    } catch (e: Exception) {
        null
    }
}


/**
 * This function copies the exif information/metadata
 * Without this function, information like orientation is lost*/
@Throws(IOException::class)
fun copyExif(oldPath: String, newPath: String) {
    val oldExif = ExifInterface(oldPath)
    val attributes = arrayOf(
        ExifInterface.TAG_DATETIME,
        ExifInterface.TAG_DATETIME_DIGITIZED,
//        ExifInterface.TAG_EXPOSURE_TIME,
//        ExifInterface.TAG_FLASH,
//        ExifInterface.TAG_FOCAL_LENGTH,
//        ExifInterface.TAG_GPS_ALTITUDE,
//        ExifInterface.TAG_GPS_ALTITUDE_REF,
//        ExifInterface.TAG_GPS_DATESTAMP,
//        ExifInterface.TAG_GPS_LATITUDE,
//        ExifInterface.TAG_GPS_LATITUDE_REF,
//        ExifInterface.TAG_GPS_LONGITUDE,
//        ExifInterface.TAG_GPS_LONGITUDE_REF,
//        ExifInterface.TAG_GPS_PROCESSING_METHOD,
        ExifInterface.TAG_GPS_TIMESTAMP,
        ExifInterface.TAG_IMAGE_LENGTH,
        ExifInterface.TAG_IMAGE_WIDTH,
//        ExifInterface.TAG_MAKE,
//        ExifInterface.TAG_MODEL,
        ExifInterface.TAG_ORIENTATION,
//        ExifInterface.TAG_SUBSEC_TIME,
//        ExifInterface.TAG_WHITE_BALANCE
    )
    val newExif = ExifInterface(newPath)
    for (i in attributes.indices) {
        val value = oldExif.getAttribute(attributes[i])
        if (value != null) newExif.setAttribute(attributes[i], value)
    }
    newExif.saveAttributes()
}
