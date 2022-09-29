package com.example.notlonesomegeorge

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Build
import androidx.exifinterface.media.ExifInterface
import kotlin.math.roundToInt


internal object Utils {

    /**
     * Scale the bitmap to fit the ImageView
     * Rotate if needed
     * If you are using Picasso, you don't need this function, Picasso does it for you under the hood.
     */
    fun getScaledBitmap(path: String, destWidth: Int, destHeight: Int): Bitmap {
        // Read in the dimensions of the image on disk
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)

        val srcWidth = options.outWidth.toFloat()
        val srcHeight = options.outHeight.toFloat()

        // Figure out how much to scale down by
        val sampleSize = if (srcHeight <= destHeight && srcWidth <= destWidth) {
            1
        } else {
            val heightScale = srcHeight / destHeight
            val widthScale = srcWidth / destWidth

            minOf(heightScale, widthScale).roundToInt()
        }

        // Read in and create final bitmap
        val mBitmap: Bitmap = BitmapFactory.decodeFile(path, BitmapFactory.Options().apply {
            inSampleSize = sampleSize
        })

        // check to see if we need to rotate based on the metadata
        return rotateIfNeeded(path, mBitmap)
    }


    private fun rotateIfNeeded(path: String, bitmap: Bitmap): Bitmap {
        val ei = ExifInterface(path)
        val orientation: Int = ei.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )

        val rotatedBitmap: Bitmap = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90F)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180F)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270F)
            ExifInterface.ORIENTATION_NORMAL -> bitmap
            else -> bitmap
        }

        return rotatedBitmap
    }

    // helper function to rotate image
    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height,
            matrix, true
        )
    }


    /**
     * this function checks to make sure there is at least one camera app in the current device
     * you need to add a configuration in [AndroidManifest.xml] to support the PackageManager query
     * Othewise, even if camera apps exist, this function will always return false
     */
    fun canResolveIntent(intent: Intent): Boolean {
        val packageManager: PackageManager = CameraBaseApplication.getAppContext().packageManager

//        if (Build.VERSION.SDK_INT >= 33) {
//            val resolvedActivity: ResolveInfo? = packageManager.resolveActivity(
//                intent,PackageManager.
//                PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
//            )
//            return resolvedActivity != null
//        }

        val resolvedActivity: ResolveInfo? =
            packageManager.resolveActivity(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
        return resolvedActivity != null
    }


}