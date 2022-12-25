package com.ibm.rides.ui.vehicle.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibm.rides.R
import com.ibm.rides.basecontroller.BaseActivity
import com.ibm.rides.basecontroller.BaseFragment
import com.ibm.rides.basecontroller.BaseViewModel
import com.ibm.rides.databinding.FragmentVehicleListingBinding
import com.ibm.rides.utils.Constant
import com.ibm.rides.utils.OnItemClickListener
import com.ibm.rides.utils.Util.isInternetAvailable
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class VehicleListingFragment : BaseFragment(), OnItemClickListener {

    private var _binding: FragmentVehicleListingBinding? = null
    private val viewModel: VehicleListViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = VehicleListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentVehicleListingBinding.inflate(inflater, container, false)
        binding.vModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun getVieModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var vehicleCount = 0
        arguments?.let {
            vehicleCount = it.getInt(Constant.KEY_VEHICLE_COUNT, 0)
        }
        viewModel.fetchVehicleList(vehicleCount)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupUI() {
        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.rvVehicles.layoutManager = llm
        binding.rvVehicles.adapter = adapter
        viewModel.vehicleList.observe(viewLifecycleOwner) {
            it?.let { vehicleList ->
                adapter.list.clear()
                adapter.list.addAll(vehicleList)
                adapter.notifyDataSetChanged()
                binding.pullToRefresh.isRefreshing = false
            }
        }

        binding.pullToRefresh.apply {
            setOnRefreshListener {
                val activity = requireActivity()
                if (activity.isInternetAvailable()) {
                    viewModel.fetchVehicleListOnPullToRefresh()
                } else {
                    isRefreshing = false
                    if (activity is BaseActivity) activity.showSnackBar(getString(R.string.error_no_internet))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?, position: Int) {
        when (view?.id) {
            R.id.containerListItem -> {
                viewModel.vehicleList.value?.let { vehicleList ->
                    val bundle = Bundle()
                    bundle.putParcelable(Constant.KEY_VEHICLE, vehicleList[position])
                    findNavController().navigate(R.id.launchVehicleDetail, bundle)
                }
            }
        }
    }

    override fun onClick(view: View?) {

    }
}