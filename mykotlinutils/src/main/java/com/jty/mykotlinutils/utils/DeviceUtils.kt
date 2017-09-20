package com.jty.mykotlinutils.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings

import java.io.File
import java.net.NetworkInterface
import java.util.Collections

/**
 * Created by TaoYuan on 2017/9/20 0020.
 *
 * Get Device Info
 */

class DeviceUtils private constructor() {
    init {
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        /**
         * Judge Device isRoot
         */
        val isDeviceRooted: Boolean
            get() {
                val su = "su"
                val locations = arrayOf("/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/")
                for (location in locations) {
                    if (File(location + su).exists()) {
                        return true
                    }
                }
                return false
            }

        /**
         * Get Device OS SDK Version
         * @return SDK Version
         */
        val sdkVersion: Int
            get() = android.os.Build.VERSION.SDK_INT


        /**
         * Get Device AndroidID
         *
         * @return AndroidID
         */
        @SuppressLint("HardwareIds")
        fun getAndroidID(context: Context): String {
            return Settings.Secure.getString(context.applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
        }

        /**
         * Get Device MAC Address
         *
         * Need Add  Permission
         * `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>`
         * `<uses-permission android:name="android.permission.INTERNET"/>`
         *
         * @param context
         * @return MAC Address
         */
        fun getMacAddress(context: Context): String {
            var macAddress = getMacAddressByWifiInfo(context)
            if ("02:00:00:00:00:00" != macAddress) {
                return macAddress
            }
            macAddress = macAddressByNetworkInterface
            return if ("02:00:00:00:00:00" != macAddress) {
                macAddress
            } else "please open wifi"
        }

        /**
         * Get Device MAC Address
         * Need Add  Permission`<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>`
         *
         * @return MAC Address
         */
        @SuppressLint("HardwareIds")
        private fun getMacAddressByWifiInfo(context: Context): String {
            try {
                val wifi = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                if (wifi != null) {
                    val info = wifi.connectionInfo
                    if (info != null) return info.macAddress
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "02:00:00:00:00:00"
        }

        /**
         * Get Device MAC Address
         *
         * Need Add  Permission
         * `<uses-permission android:name="android.permission.INTERNET"/>`
         *
         * @return MAC Address
         */
        private val macAddressByNetworkInterface: String
            get() {
                try {
                    val nis = Collections.list(NetworkInterface.getNetworkInterfaces())
                    for (ni in nis) {
                        if (!ni.name.equals("wlan0", ignoreCase = true)) continue
                        val macBytes = ni.hardwareAddress
                        if (macBytes != null && macBytes.size > 0) {
                            val res1 = StringBuilder()
                            for (b in macBytes) {
                                res1.append(String.format("%02x:", b))
                            }
                            return res1.deleteCharAt(res1.length - 1).toString()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return "02:00:00:00:00:00"
            }


        /**
         * Get Device Firm
         * @return
         */
        val manufacturer: String
            get() = Build.MANUFACTURER

        /**
         * Get Device Model
         * @return
         */
        val model: String
            get() {
                var model: String? = Build.MODEL
                if (model != null) {
                    model = model.trim { it <= ' ' }.replace("\\s*".toRegex(), "")
                } else {
                    model = ""
                }
                return model
            }
    }
}
