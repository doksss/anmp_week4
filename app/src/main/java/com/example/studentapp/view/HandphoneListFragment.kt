package com.example.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapp.R
import com.example.studentapp.databinding.FragmentHandphoneListBinding
import com.example.studentapp.databinding.HandphoneListItemBinding
import com.example.studentapp.viewmodel.ListHandphoneViewModel
import com.example.studentapp.viewmodel.ListViewModel

class HandphoneListFragment : Fragment() {
    private lateinit var viewModel:ListHandphoneViewModel
    private val handphoneListAdapter = HandphoneListAdapter(arrayListOf())
    private lateinit var binding: FragmentHandphoneListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentHandphoneListBinding.inflate(inflater,container,false)
//        return inflater.inflate(R.layout.fragment_handphone_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListHandphoneViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = handphoneListAdapter

        observeViewModel()

    }
    fun observeViewModel() {
        viewModel.handphonesLD.observe(viewLifecycleOwner, Observer {
            handphoneListAdapter.updateList(it)
        })
    }

}