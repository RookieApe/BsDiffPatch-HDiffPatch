package com.cantbot.ndk

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bspatch.BsPatch
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun loadPatch(view: View) {
        val sd = Environment.getExternalStorageDirectory().absolutePath

        val oldFile = "$sd/U05_Pad_Launcher-31.apk"
        val newFile = "$sd/31-32.apk"
        val patchFile = "$sd/diff.patch"

        if (BsPatch.bsPatch(File(oldFile), File(newFile), File(patchFile))) {
            Log.e(TAG, "jin patch success")
        }else {
            Log.e(TAG, "jni patch failed")
        }
        Log.e(TAG, "patch complete")
    }

    fun asyncLoadPatch(view: View) {
        val sd = Environment.getExternalStorageDirectory().absolutePath

        val oldFile = "$sd/U05_Pad_Launcher-31.apk"
        val newFile = "$sd/31-32.apk"
        val patchFile = "$sd/diff.patch"

        Log.e(TAG, "start async patch")
        BsPatch.bsPatch(File(oldFile), File(newFile), File(patchFile), object : BsPatch.CallBack {
            override fun onSucceed() {
                Log.e(TAG, "patch succeed")
            }

            override fun onFailed() {
                Log.e(TAG, "patch failed")
            }
        })
        Log.e(TAG, "wait async patch complete")
    }

}
