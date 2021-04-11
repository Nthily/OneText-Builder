package com.compose.onetextbuilder


import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.random.Random


@RequiresApi(Build.VERSION_CODES.N)

suspend fun getSentences() = withContext(Dispatchers.IO){

    val type = "b"
    val url = URL("https://cdn.jsdelivr.net/gh/hitokoto-osc/sentences-bundle@1.0.56/sentences/$type.json")
    var result = ""
    var sentence: JsonElement? = null

    with(url.openConnection() as HttpURLConnection) {
        this.requestMethod = "GET"
        this.setRequestProperty(
            "user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")

        println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

        inputStream.bufferedReader().use {
            result = it.readText()
            sentence = Json.parseToJsonElement(result)
        }
    }
    val arraySize = sentence?.jsonArray?.size
    val randomSentence = arraySize?.let { Random.nextInt(0, it) }
    sentence = randomSentence?.let { sentence?.jsonArray?.get(it) }
    result = sentence!!.jsonObject["hitokoto"].toString()

    return@withContext result.replace("\"", "")
}

fun startShare(context: Context, message:String) {
    val sendIntent: Intent = Intent().apply { // 分享 Intent
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "选择分享到哪里吧 ~")
    startActivity(context, shareIntent, Bundle())
}