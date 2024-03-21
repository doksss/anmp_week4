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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>() //live data yang berjenis ArrayListofStudent
    //kenapa bikin arraylist? karena recylcler view kita punya adapter yang minta arraylist
    val loadingLD = MutableLiveData<Boolean>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag" //ini bebas mau dikasi nama apa variablenya
    private var queue:RequestQueue ?=null //object volley

    //view model biasanya punya function yang menghasilkan data
    fun refresh() { //database volley ditaruh disini

        //UDAH GA DIPAKE KARENA DAH PAKE VOLLEY
//        studentsLD.value = arrayListOf(
//            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100" +
//                    ".jpg/cc0000/ffffff"),
//            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
//                    ".jpg/5fa2dd/ffffff"),
//            Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
//        )

        //kita anggap tdk ada loading - loading nya karena hardcode
        studentLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())//requestQueue butuh context, karena viewmodel gada context maka parent class diganti AndroidViewModel
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {//callback function jika volly success dibaca
                loadingLD.value = false
                Log.d("show_volley",it)
                //stype object untuk GSON
                val sType = object:TypeToken<List<Student>>(){}.type
                val result = Gson().fromJson<List<Student>>(it,sType)
                studentsLD.value = result as ArrayList<Student>
            },
            {
                loadingLD.value = false
                Log.e("show_volley",it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        //yang mau di clearkan tentu saja queue volley
        queue?.cancelAll(TAG)
    }
}


