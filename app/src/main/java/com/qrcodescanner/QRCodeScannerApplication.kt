package com.qrcodescanner

import android.app.Application

class QRCodeScannerApplication : Application() {

    companion object {
        lateinit var instance: QRCodeScannerApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}