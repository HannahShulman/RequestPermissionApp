package com.`is`.id.of.requestpermissionapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn.setOnClickListener {
            //            sendSms()


            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CALL_PHONE), 234
                );
            } else {
//                val intent = Intent(Intent.ACTION_CALL,
//                    Uri.parse("tel:" + phoneNumebrEt.text.toString()))// Initiates the Intent
                val intent =
                    Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumebrEt.text.toString()))// Initiates the Intent
                startActivity(intent)

//            }
            }
        }

    }

    fun sendSms() {
        val sendIntent = Intent(Intent.ACTION_VIEW)
        sendIntent.putExtra("sms_body", "default content")
        sendIntent.type = "vnd.android-dir/mms-sms"
        startActivity(sendIntent)
    }

    /** Check if this device has a camera */
    private fun checkCameraHardware(context: Context): Boolean {
        if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true
        } else {
            // no camera on this device
            return false
        }
    }

    fun openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),
                111
            );
        } else {
            android.hardware.Camera.open()
        }
    }


    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 234 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumebrEt.text.toString()))
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumebrEt.text.toString()))
            startActivity(intent)
        } else {

        }
    }
}
