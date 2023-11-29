package com.example.myvideo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class ActivityVideo : AppCompatActivity() {
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity_video)

        val videoUrl = intent.getStringExtra("videoUrl")
        videoView = findViewById(R.id.video)

        videoView.setVideoURI(Uri.parse(videoUrl))
        videoView.requestFocus()

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)

        videoView.start()
    }
}
