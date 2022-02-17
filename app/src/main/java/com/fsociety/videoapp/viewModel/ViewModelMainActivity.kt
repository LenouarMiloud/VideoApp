package com.fsociety.videoapp.viewModel

import androidx.lifecycle.ViewModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.fsociety.videoapp.model.Video
import com.fsociety.videoapp.viewHolder.VideoViewHolder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var adapter:FirebaseRecyclerAdapter<Video,VideoViewHolder>
lateinit var options:FirebaseRecyclerOptions<Video>
var firebaseDatabase = FirebaseDatabase.getInstance()

class ViewModelMainActivity: ViewModel() {

    fun getReference(ref:String):DatabaseReference{
        
    }


}