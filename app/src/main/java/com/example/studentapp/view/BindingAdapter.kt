package com.example.studentapp.view

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.example.studentapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhoto(imageView: ImageView, url:String){
    val picasso = Picasso.Builder(imageView.context)
    //jika terjadi sesuatu pada picasso maka ini jalan
    picasso.listener { picasso, uri, exception -> exception.printStackTrace()  }
    picasso.build().load(url).into(imageView)
    imageView.visibility = View.VISIBLE

    //UNTUK ILANGIN PROGRESS BAR
//    picasso.build().load(url).into(imageView, object:Callback{
//        override fun onSuccess() {
//            var v = imageView.parent as View
//            var pb = v.findViewById<ProgressBar>(R.id.progressBar)
//            pb.visibility = View.INVISIBLE
//            imageView.visibility = View.VISIBLE
//        }
//        override fun onError(e: Exception?){
//            Log.e("picasso",e?.message.toString())
//        }
//    })
//    Log.d("bindingadapter","cek"+url)

}