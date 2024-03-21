package com.example.studentapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentapp.model.Student

class ListViewModel: ViewModel() {
    val studentsLD = MutableLiveData<ArrayList<Student>>() //live data yang berjenis ArrayListofStudent
    //kenapa bikin arraylist? karena recylcler view kita punya adapter yang minta arraylist
    val loadingLD = MutableLiveData<Boolean>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()

    //view model biasanya punya function yang menghasilkan data
    fun refresh() {
        studentsLD.value = arrayListOf(
            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100" +
                    ".jpg/cc0000/ffffff"),
            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
                    ".jpg/5fa2dd/ffffff"),
            Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        )
        //kita anggap tdk ada loading - loading nya karena hardcode
        studentLoadErrorLD.value = false
        loadingLD.value = false
    }
}


