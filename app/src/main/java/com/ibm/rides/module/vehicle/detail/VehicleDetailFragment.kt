package com.ibm.rides.module.vehicle.detail

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ibm.rides.basecontroller.BaseAndroidViewModel
import com.ibm.rides.basecontroller.BaseFragment
import com.ibm.rides.databinding.FragmentVehicleDetailBinding
import com.ibm.rides.module.vehicle.model.Vehicle
import com.ibm.rides.utils.Constant
import com.ibm.rides.utils.Injector

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class VehicleDetailFragment : BaseFragment() {

    private var _binding: FragmentVehicleDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: VehicleDetailViewModel by viewModels {
        Injector.getVehicleDetailVMFactory(
            context?.applicationContext as Application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleDetailBinding.inflate(inflater, container, false)
        binding.vModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun getVieModel(): BaseAndroidViewModel {
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupUI() {
        arguments?.let { args ->
            val vehicle = args.getParcelable<Vehicle>(Constant.KEY_VEHICLE)
            viewModel.vehicle.postValue(vehicle)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}