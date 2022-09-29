package com.example.notlonesomegeorge

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import com.squareup.picasso.Picasso
import java.io.File

private const val TAG = "Configs"

// https://play.kotlinlang.org/byExample/03_special_classes/04_Object
// The object declaration allows us to directly access its members,
// e.g., we can do FileUtility.createDefaultProfileDir(...)
internal object Configs {
    private const val PROFILE_DIR = "profile"
    private const val LOG_DIR = "log"


    // this turns on the cache indicator overlays
    // the blue, green, and red triangles on the top left corner of the ImageView
    fun configPicasso() {
        Picasso.get().setIndicatorsEnabled(true)
    }

    fun createDefaultProfileDir(context: Context): File {
        // setup subdirectories
        val profileDirectory = File(context.applicationContext.filesDir, PROFILE_DIR)
        // creates the directory if not present yet
        if (!profileDirectory.exists()) {
            // true if and only if the directory was created; false otherwise
            profileDirectory.mkdir()
        }
        return profileDirectory
    }

    fun getDefaultProfileDir(context: Context): File {
        return createDefaultProfileDir(context)
    }

    private fun createDefaultLogDir(context: Context): File {
        // setup subdirectories
        val logDirectory = File(context.applicationContext.filesDir, LOG_DIR)
        // creates the directory if not present yet
        if (!logDirectory.exists()) {
            // true if and only if the directory was created; false otherwise
            logDirectory.mkdir()
        }
        return logDirectory
    }

    fun getDefaultLogDir(context: Context): File {
        return createDefaultLogDir(context)
    }


    fun getAssetDirPath(): String {
        return "file:///android_asset"
    }

    /**
     * construct the Uri based on the fileName and the default directory
     */
    fun createUri(context: Context, fileName: String): Uri {

        val mFile = File(getDefaultProfileDir(context), fileName)

        // photoFile is nothing special. Similar to File I/O in Java,
        // we could do the following:
        // photoFile!!.writeBytes("photos".toByteArray())
        // which will just write the word "photos" to that file.
        val mUri = FileProvider.getUriForFile(
            context,
            "info.tianguo.cameraintentdemo.fileprovider", // need to match exactly as defined in AndroidManifest
            mFile
        )
        Log.d(TAG, mUri.toString())
        return mUri
    }

}

