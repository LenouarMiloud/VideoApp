package com.fsociety.videoapp.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.fsociety.videoapp.R

class VideoViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val commentImage : ImageView = view.findViewById(R.id.commentImage)
    val likeImage : ImageView = view.findViewById(R.id.likeImage)
    val shareImage : ImageView = view.findViewById(R.id.shareImage)
    val likeText : TextView = view.findViewById(R.id.likeNumber)
    val videoView : VideoView = view.findViewById(R.id.videoView)


}