package id.interconnect.moviesandtv.utils

import android.content.Context
import android.content.res.Resources
import android.util.Log
import id.interconnect.moviesandtv.R
import java.io.File
import java.io.InputStreamReader
import java.nio.channels.AsynchronousFileChannel.open

class MockResponseFileReader(path: String) {
    lateinit var content: String

//    private val ASSET_LOCATION = "../../app/src/main/assets/"

    init {
        val uri = this.javaClass.classLoader!!.getResource(path)
        val file = File(uri.path).readBytes()
        content = String(file)
    }

}