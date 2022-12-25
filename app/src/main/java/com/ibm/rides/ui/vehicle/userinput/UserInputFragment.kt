package com.ibm.rides.ui.vehicle.userinput

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ibm.rides.R
import com.ibm.rides.basecontroller.BaseActivity
import com.ibm.rides.basecontroller.BaseFragment
import com.ibm.rides.basecontroller.BaseViewModel
import com.ibm.rides.databinding.FragmentUserInputBinding
import com.ibm.rides.utils.Constant
import com.ibm.rides.utils.Util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class UserInputFragment : BaseFragment() {

    private var _binding: FragmentUserInputBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: UserInputViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentUserInputBinding.inflate(inflater, container, false)
        binding.vModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun getVieModel(): BaseViewModel {
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFetch.setOnClickListener {
            var vehicleCount = 0

            viewModel.vehicleCount.value?.let {
                try {
                    vehicleCount = it.toInt()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            if (UserInputValidator.isValidVehicleCount(vehicleCount)) {
                val bundle = Bundle()
                bundle.putInt(Constant.KEY_VEHICLE_COUNT, vehicleCount)
                findNavController().navigate(R.id.action_UserInputFragment_to_VehicleListingFragment, bundle)
            } else {
                val activity = requireActivity()
                activity.hideKeyboard(binding.etVehicleCount)
                if (activity is BaseActivity) activity.showSnackBar(getString(R.string.error_invalid_input))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}