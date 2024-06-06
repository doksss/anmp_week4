package com.example.studentapp.view

import android.database.Observable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentapp.R
import com.example.studentapp.databinding.FragmentStudentDetailBinding
import com.example.studentapp.model.Student
import com.example.studentapp.viewmodel.DetailViewModel
import com.example.studentapp.viewmodel.ListViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonUpdateClickListener {
    private lateinit var binding:FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //buat ini gara" pas di pencet button detail lgsg force close gara" picasso data binding
        binding.student = Student("","","","","https://randomuser.me/api/portraits/men/74.jpg")

        if(arguments!=null){
            val student_id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.refresh(student_id)
            observeViewModel()
        }
        binding.listener = this


    }
    fun observeViewModel() {

        //bertujuan untuk mendengarkan dari live data. jika data baru muncul, maka UI akan menanggapi
        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
            binding.student = it

            //SUDAH TIDAK KEPAKE KARENA PAKE DATA BINDING
            // it = berisi array student terbaru
//            binding.txtID.setText(it.id)
//            binding.txtName.setText(it.name)
//            binding.txtBod.setText(it.dob)
//            binding.txtPhone.setText(it.phone)

            //bisa begini picasso
//            Picasso.get().load(it.photoUrl).into(binding.imageView3)

//            var student = it

//            binding.btnUpdate.setOnClickListener {
//                io.reactivex.rxjava3.core.Observable.timer(5,TimeUnit.SECONDS).subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages","five seconds")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",R.drawable.baseline_person_2_24)
//                    }
//            }
        })




    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(context,"Success Update - " + v.tag.toString(), Toast.LENGTH_SHORT).show()
        io.reactivex.rxjava3.core.Observable.timer(5,TimeUnit.SECONDS).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages","five seconds")
                MainActivity.showNotification(v.tag.toString(),
                    "A new notification created",R.drawable.baseline_person_2_24)
            }

    }
}