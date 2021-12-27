package tr.com.mekhti.satellitesapp.view.fragments.satellite_detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import tr.com.mekhti.satellitesapp.data.model.PositionsOfSatellite
import tr.com.mekhti.satellitesapp.databinding.FragmentSatelliteDetailBinding
import tr.com.mekhti.satellitesapp.view.MainActivity
import tr.com.mekhti.satellitesapp.viewmodel.MainViewModel

@AndroidEntryPoint
class SatelliteDetailFragment : Fragment() {

    private var _binding: FragmentSatelliteDetailBinding? = null

    private lateinit var viewModel: MainViewModel
    private lateinit var positions : PositionsOfSatellite


    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSatelliteDetailBinding.inflate(inflater, container, false)

        viewModel = (activity as MainActivity).viewModel

        viewModel.getSatelliteDetail(requireArguments().getInt("id"))

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.currentSatelliteDetail.observe(viewLifecycleOwner,{
            binding.detail = it
        })
        
        viewModel.currentSatellite.observe(viewLifecycleOwner,{
            binding.satellite = it
        })

        viewModel.currentSatellitePosition.observe(viewLifecycleOwner,{
            positions = it
            showPositions()
        })
    }

    private fun showPositions() {
        val mainHandler = Handler(Looper.getMainLooper())

        var i  = 0

        mainHandler.post(object : Runnable {
            override fun run() {
                emmitPosition(i)
                i++
                mainHandler.postDelayed(this, 3000)
            }
        })
    }

    private fun emmitPosition(i : Int) {
        val index = i % positions.positions.size
        val currentPos =  positions.positions[index]

        binding.currentPos = currentPos
    }

}