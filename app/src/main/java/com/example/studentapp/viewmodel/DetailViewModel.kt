package com.example.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentapp.model.Student
import com.example.studentapp.view.StudentDetailFragmentArgs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application): AndroidViewModel(application){
    val studentLD = MutableLiveData<Student>()
//    val studentLD = MutableLiveData<ArrayList<Student>>()
    fun refresh(student_id: String){
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
        val TAG = "volleyTag" //ini bebas mau dikasi nama apa variablenya
        var queue: RequestQueue?=null //object volley
        queue = Volley.newRequestQueue(getApplication())//requestQueue butuh context, karena viewmodel gada context maka parent class diganti AndroidViewModel
        val url = "http://adv.jitusolution.com/student.php?id=" + student_id
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            {
                Log.d("show_volley_detail",it)
                //stype object untuk GSON
                //mengirim data tunggal berupa class student
                val student1 = Gson().fromJson(it,Student::class.java)
                studentLD.value = student1
            },
            {
                Log.e("show_volley",it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}