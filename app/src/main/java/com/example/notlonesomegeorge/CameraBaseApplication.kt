package com.example.notlonesomegeorge

import android.app.Application
import android.content.Context

/**
 * This class provides context for other non-UI code
 */
class CameraBaseApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: CameraBaseApplication? = null

        fun getAppContext(): Context {
            return instance!!.applicationContext
        }
    }
}