package com.ibm.rides.ui.vehicle.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibm.rides.databinding.ListItemVehicleBinding
import com.ibm.rides.domain.vehicle.list.Vehicle
import com.ibm.rides.utils.OnItemClickListener


class VehicleListAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<VehicleListAdapter.ViewHolder>() {

    val list = mutableListOf<Vehicle>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemBinding =
            ListItemVehicleBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        return ViewHolder(listItemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: ListItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.executePendingBindings()
            binding.txtMakeAndModelVal.text = list[position].makeAndModel
            binding.txtVinVal.text = list[position].vin
            binding.containerListItem.setOnClickListener {
                listener.onClick(binding.containerListItem, adapterPosition)
            }
        }
    }
}