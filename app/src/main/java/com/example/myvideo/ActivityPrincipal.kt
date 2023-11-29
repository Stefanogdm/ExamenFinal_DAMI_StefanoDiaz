package com.example.myvideo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myvideo.VideoRest.CustomAdapter
import com.example.myvideo.VideoRest.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.myvideo.VideoRest.ItemViewVideos
import com.example.myvideo.VideoRest.VideoAPI

class ActivityPrincipal : AppCompatActivity() {
    private val data = ArrayList<ItemViewVideos>()
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity_principal)

        val videoAPI = RetrofitHelper.getRetrofitInstance().create(VideoAPI::class.java)

        val videoRecyclerView: RecyclerView = findViewById(R.id.recyclerViewVideo)
        videoRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CustomAdapter(data, object : CustomAdapter.OnItemClickListener {
            override fun onItemClick(selectedItem: ItemViewVideos) {
                showVideoScreen(selectedItem)
            }
        })


        videoRecyclerView.adapter = adapter

        GlobalScope.launch {
            val videoResponse = videoAPI.getCategory()

            if (videoResponse != null && videoResponse.isSuccessful) {
                val responseBody = videoResponse.body()
                if (responseBody != null && responseBody.categories.isNotEmpty()) {
                    val firstCategory = responseBody.categories[0]
                    val videos = firstCategory.videos
                    for (video in videos) {
                        Log.d("Video Title", video.title)
                        data.add(ItemViewVideos(video.title, video.subtitle, video.sources))
                    }
                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                } else {

                }
            } else {

            }
        }
    }

    private fun showVideoScreen(selectedItem: ItemViewVideos) {
        val intent = Intent(this, ActivityVideo::class.java)
        intent.putExtra("videoUrl", selectedItem.sources.firstOrNull())
        startActivity(intent)
    }
}




