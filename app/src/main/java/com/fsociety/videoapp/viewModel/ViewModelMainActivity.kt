package com.fsociety.videoapp.viewModel

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.fsociety.videoapp.R
import com.fsociety.videoapp.model.Video
import com.fsociety.videoapp.viewHolder.VideoViewHolder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var adapter:FirebaseRecyclerAdapter<Video,VideoViewHolder>
lateinit var options:FirebaseRecyclerOptions<Video>
var firebaseDatabase = FirebaseDatabase.getInstance()

class ViewModelMainActivity: ViewModel() {

    private val commentReference = MutableLiveData<String>()
    private val showLoading = MutableLiveData<Int>()
    private val isShowComment = MutableLiveData<Boolean>()
    val comment: LiveData<String>
        get() = commentReference
    val loading:LiveData<Int>
        get() = showLoading
    val showComments:LiveData<Boolean>
        get() = isShowComment


    init {
        commentReference.value = "01"
        showLoading.value = 0
        isShowComment.value = false
    }

    fun getReference(ref:String):DatabaseReference{
        return firebaseDatabase.getReference(ref)
    }

    fun initializeAdapter() {
        val my_query = getReference("Videos")
        options =
            FirebaseRecyclerOptions.Builder<Video>().setQuery(my_query, Video::class.java).build()

        adapter = object : FirebaseRecyclerAdapter<Video, VideoViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {

                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.video_item_layout, parent, false)

                return VideoViewHolder(view)
            }

            override fun onBindViewHolder(holder: VideoViewHolder, position: Int, model: Video) {
                holder.videoView.setVideoPath(model.url)
                holder.videoView.setOnPreparedListener {
                    showLoading.value = 1
                    it.start()

                    val videoRation = it.videoWidth / it.videoHeight.toFloat()
                    val screenRation = holder.videoView.width / holder.videoView.width.toFloat()
                    //calculate the scale
                    val scale = videoRation / screenRation

                    if (scale >= 1f) {
                        holder.videoView.scaleX = scale
                    } else {
                        holder.videoView.scaleY / 1f
                    }


                }
                holder.videoView.setOnInfoListener {

                        mp, what, extra ->

                    when (what) {

                        MediaPlayer.MEDIA_INFO_BUFFERING_START -> showLoading.value = 0
                        MediaPlayer.MEDIA_INFO_BUFFERING_END -> showLoading.value= 1
                        else -> 1


                    }
                    false


                }
            }

        }

    }
}