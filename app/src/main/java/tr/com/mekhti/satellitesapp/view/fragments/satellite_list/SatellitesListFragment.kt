package tr.com.mekhti.satellitesapp.view.fragments.satellite_list

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import tr.com.mekhti.satellitesapp.databinding.FragmentSatellitesListBinding
import tr.com.mekhti.satellitesapp.view.MainActivity
import tr.com.mekhti.satellitesapp.viewmodel.MainViewModel

@AndroidEntryPoint
class SatellitesListFragment : Fragment() {

    private var _binding: FragmentSatellitesListBinding? = null

    private lateinit var viewModel: MainViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentSatellitesListBinding.inflate(inflater, container, false)

        viewModel = (activity as MainActivity).viewModel
        observeViewModelData()

        return binding.root
    }

    private fun observeViewModelData() {
        viewModel.satellites.observe(viewLifecycleOwner,  {
            Log.d(ContentValues.TAG, "onCreate: "+it.size)
        })
    }




}