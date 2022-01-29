package com.yogify.youtubeplayerkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.youtube.player.YouTubeStandalonePlayer

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val singlevideoactivity = findViewById<Button>(R.id.singlevideoactivity)
        val singlevideo = findViewById<Button>(R.id.singlevideo)
        val playlistvideo = findViewById<Button>(R.id.playlistvideo)
        singlevideoactivity.setOnClickListener(this)
        singlevideo.setOnClickListener(this)
        playlistvideo.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val intent = when (p0?.id) {

            R.id.singlevideoactivity -> Intent(this, YouTubeActivity::class.java)
            R.id.singlevideo -> YouTubeStandalonePlayer.createVideoIntent(
                this, getString(R.string.API_KEY),
                YOUTUBE_VIDEO_ID
            )
            R.id.playlistvideo -> YouTubeStandalonePlayer.createPlaylistIntent(
                this, getString(R.string.API_KEY),
                YOUTUBE_PLAYLIST_ID
            )
            else -> throw IllegalArgumentException("Undefined nutton clicked")

        }
        startActivity(intent)

    }
}