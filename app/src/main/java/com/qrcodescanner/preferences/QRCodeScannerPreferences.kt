package com.qrcodescanner.preferences

import android.content.Context
import android.content.SharedPreferences

class QRCodeScannerPreferences private constructor(context: Context) {

    companion object {

        @Volatile
        private var INSTANCE: QRCodeScannerPreferences? = null

        private const val SHARED_PREF_FILE_NAME = "qr_code_scanner_data"
        private const val TAG_USER_TYPE = "user_type"
        private const val TAG_USER = "user"

        fun getInstance(context: Context): QRCodeScannerPreferences {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = QRCodeScannerPreferences(context)
                INSTANCE = instance
                return instance
            }
        }
    }

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences(
            SHARED_PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun getUserType(): String? {
        return sharedPreferences.getString(TAG_USER_TYPE, null)
    }

    fun setUserType(userType: String) {
        sharedPreferences.edit().putString(TAG_USER_TYPE, userType).apply()
    }

    fun getUser(): String? {
        return sharedPreferences.getString(TAG_USER, null)
    }

    fun setUser(user: String) {
        sharedPreferences.edit().putString(TAG_USER, user).apply()
    }


}