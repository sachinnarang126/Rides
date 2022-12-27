package com.ibm.rides.ui.vehicle.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ibm.rides.basecontroller.BaseFragment
import com.ibm.rides.basecontroller.BaseViewModel
import com.ibm.rides.databinding.BottomSheetCarbonEmissionBinding
import com.ibm.rides.databinding.FragmentVehicleDetailBinding
import com.ibm.rides.domain.vehicle.list.Vehicle
import com.ibm.rides.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class VehicleDetailFragment : BaseFragment() {

    private var _binding: FragmentVehicleDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: VehicleDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentVehicleDetailBinding.inflate(inflater, container, false)
        binding.vModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun getVieModel(): BaseViewModel {
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupUI() {
        var vehicle: Vehicle? = null
        arguments?.let { args ->
            vehicle = args.getParcelable(Constant.KEY_VEHICLE)
            viewModel.vehicle.postValue(vehicle)
        }

        binding.btnCarbonEmission.setOnClickListener {
            vehicle?.let {
                showBottomSheetDialog(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun showBottomSheetDialog(vehicle: Vehicle) {

        val bottomSheetBinding = BottomSheetCarbonEmissionBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())

        try {
            val carbonEmission = viewModel.calculateTotalCarbonEmission(vehicle.kilometrage)
            val estimatedCarbon = viewModel.calculateEstimateCarbonEmission(vehicle.kilometrage!!, carbonEmission)
            val formattedEstimation = String.format("%.2f", estimatedCarbon).toDouble()
            bottomSheetBinding.txtCarbonEmissionVal.text = "$carbonEmission g"
            bottomSheetBinding.txtEstimatedCarbonVal.text = "$formattedEstimation g/km"
            bottomSheetBinding.txtKMRangeVal.text = vehicle.kilometrage.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            bottomSheetDialog.setContentView(bottomSheetBinding.root)
            bottomSheetDialog.show()
        }
    }
}