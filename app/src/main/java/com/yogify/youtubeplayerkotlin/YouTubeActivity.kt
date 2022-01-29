package com.yogify.youtubeplayerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "0rKO3npQ7yg"
const val YOUTUBE_PLAYLIST_ID = "PLRKyZvuMYSIO0jLgj8g6sADnD0IBaWaw2"

class YouTubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    val TAG = "YotuTubeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      setContentView(R.layout.activity_you_tube)
//      val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)

        val layout = layoutInflater.inflate(R.layout.activity_you_tube, null) as ConstraintLayout
        setContentView(layout)

        //       Used To Create An Dynamic View added to Layout
//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600, 200)
//        button1.text = "Button Added"
//        layout.addView(button1)

        val playerview = YouTubePlayerView(this)
        playerview.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerview)

        playerview.initialize(getString(R.string.API_KEY), this)


    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Log.d(TAG, "Provider")
        player?.setPlayerStateChangeListener(stateChangeListener)
        player?.setPlaybackEventListener(playbackEventListener)
        if (!wasRestored) {
            player?.cueVideo(YOUTUBE_VIDEO_ID)
        }else{
            player?.play()
        }

    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        initializationresult: YouTubeInitializationResult?
    ) {

        val REQUEST_CODE = 0
        if (initializationresult!!.isUserRecoverableError) {
            initializationresult!!.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errormessage =
                "This was an error initializing the YoutubePlayer $initializationresult"
            Toast.makeText(this, errormessage, Toast.LENGTH_LONG);
        }
    }
    // Play Back Event

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onPlaying() {
            Toast.makeText(this@YouTubeActivity, "Playing", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YouTubeActivity, "Pause", Toast.LENGTH_SHORT).show()

        }

        override fun onStopped() {
            Toast.makeText(this@YouTubeActivity, "Video Stoped", Toast.LENGTH_SHORT).show()

        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onSeekTo(p0: Int) {

        }

    }

    private val stateChangeListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onLoading() {

        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {

        }

        override fun onVideoStarted() {
            Toast.makeText(this@YouTubeActivity, "Video Started", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YouTubeActivity, "Video Ended", Toast.LENGTH_SHORT).show()

        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }

    }
}