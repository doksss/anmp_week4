package com.example.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.databinding.HandphoneListItemBinding
import com.example.studentapp.model.Handphone

class HandphoneListAdapter(val handphoneList: ArrayList<Handphone>)
    :RecyclerView.Adapter<HandphoneListAdapter.HandphoneViewHolder>(){
    class HandphoneViewHolder(var binding: HandphoneListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HandphoneViewHolder {
        val binding = HandphoneListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HandphoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HandphoneViewHolder, position: Int) {
        holder.binding.txtId.text = handphoneList[position].id
        holder.binding.txtBrand.text = handphoneList[position].brand
        holder.binding.txtModel.text = handphoneList[position].model
        var colors = handphoneList[position].colors
        var size = colors.size
        var i = 0
        var text = ""
        for(color in colors){
            if(i>=size-1){
                text += color
            }else{
                text += color + ", "
            }
            i++
        }
        holder.binding.txtColors.text = text
        holder.binding.txtRelease.text = handphoneList[position].releaseyear
        holder.binding.txtDisplay.text = handphoneList[position].specifications?.display
        holder.binding.txtProcessor.text = handphoneList[position].specifications?.processor
        holder.binding.txtCamera.text = handphoneList[position].specifications?.camera
        holder.binding.txtBattery.text = handphoneList[position].specifications?.battery

    }

    override fun getItemCount(): Int {
        return handphoneList.size
    }

    fun updateList(newHandphoneList:ArrayList<Handphone>){
        handphoneList.clear()
        handphoneList.addAll(newHandphoneList)
        notifyDataSetChanged()
    }

}