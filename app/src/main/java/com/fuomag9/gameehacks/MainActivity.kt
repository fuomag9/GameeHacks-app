package com.fuomag9.gameehacks

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //carica il layout della main activity
        val myWebView = findViewById<WebView>(R.id.WebView)   //webview viene setuppato all'apertura dell'app così permette di handlare anche gli intent di condivisione direttamente
        myWebView.settings.javaScriptEnabled = true
        //myWebView.setWebChromeClient(new WebChromeClient());     //idk if this is useful
        myWebView.webViewClient = WebViewClient()
        val intent = intent     //handling della condivisione: intent
        val action = intent.action //handling della condivisione: azione
        val type = intent.type //handling della condivisione: tipo
        if (Intent.ACTION_SEND == action && type != null) {
            if ("text/plain" == type) {
                handleSendText(intent) // Handle text being sent
            }
        }
    }

    private fun handleSendText(intent: Intent) {
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
        if (sharedText != null) {
            val Link = findViewById<EditText>(R.id.Link)
            Link.setText(sharedText)
            val myWebView = findViewById<WebView>(R.id.WebView)
            myWebView.visibility = View.VISIBLE
            myWebView.loadUrl(Link.text.toString())
        }
    }

    fun Load_website(view: View) {
        val Link = findViewById<EditText>(R.id.Link)
        var url: String? = null
        if (Link.text.toString().startsWith("http://www.gameeapp.com") or Link.text.toString().startsWith("https://www.gameeapp.com") or Link.text.toString().startsWith("http://gameeapp.com") or Link.text.toString().startsWith("https://gameeapp.com")) {
            url = Link.text.toString()
        } else {
            if (!Link.text.toString().contains("gameeapp.com")) {
                //url = String.format("https://%s",String.valueOf(Link.getText())); //corregge la formattazione dell'url
                url = "errore"
                Link.setText(url) //corregge l'url sullo schermo
            }
        }
        val myWebView = findViewById<WebView>(R.id.WebView)
        myWebView.visibility = View.VISIBLE
        myWebView.loadUrl(url)
    }

    fun Hack(view: View) {
        val myWebView = findViewById<WebView>(R.id.WebView)
        val Score = findViewById<EditText>(R.id.Score)
        val punteggio = Score.text
        val javascript = "javascript:(function() { gameeUI.gameStart();gameeUI.gamePause();gameeUI.updateScore($punteggio);gameeUI.gameOver();})()"
        myWebView.loadUrl(javascript)
        Toast.makeText(applicationContext, R.string.successful_hack, Toast.LENGTH_SHORT).show() //messaggio di avvenuto hacking

    }

    fun Reset(view: View) {
        val myWebView = findViewById<WebView>(R.id.WebView)
        val Score = findViewById<EditText>(R.id.Score)
        val Link = findViewById<EditText>(R.id.Link)
        myWebView.loadUrl("about:blank") //reset dell'url caricato nel webview
        myWebView.visibility = View.INVISIBLE //webview reso invisibile
        Score.setText("")
        Link.setText("")
    }

    fun display_info(view: View) {
        Toast.makeText(applicationContext, R.string.text_info, Toast.LENGTH_LONG).show() //messaggio di come hackerare
    }

}