package tr.com.mekhti.satellitesapp.view.fragments.satellite_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tr.com.mekhti.satellitesapp.R
import tr.com.mekhti.satellitesapp.data.model.Satellite
import tr.com.mekhti.satellitesapp.databinding.FragmentSatellitesListBinding
import tr.com.mekhti.satellitesapp.listeners.SatelliteListener
import tr.com.mekhti.satellitesapp.view.MainActivity
import tr.com.mekhti.satellitesapp.view.fragments.adapters.SatellitesAdapter
import tr.com.mekhti.satellitesapp.viewmodel.MainViewModel


@AndroidEntryPoint
class SatellitesListFragment : Fragment() {

    private var _binding: FragmentSatellitesListBinding? = null

    private lateinit var viewModel: MainViewModel
    private lateinit var satellites : ArrayList<Satellite>
    private lateinit var filteredList : ArrayList<Satellite>
    private lateinit var adapter : SatellitesAdapter
    private lateinit var  navController : NavController


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentSatellitesListBinding.inflate(inflater, container, false)


        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        viewModel = (activity as MainActivity).viewModel
        
        observeViewModelData()

        return binding.root
    }

    private fun searchViewListener() {
        binding.searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                adapter.filter.filter(text)
                return false
            }
        })
    }


    private fun observeViewModelData() {
        viewModel.satellites.observe(viewLifecycleOwner,  {
            satellites = it as ArrayList<Satellite>
            filteredList = satellites
            setUpSatellitesRV()
        })
    }

    private fun setUpSatellitesRV() {
        adapter = SatellitesAdapter(filteredList,object : SatelliteListener {
            override fun onClick(id: Int) {

                val b =  Bundle()
                b.putInt("id",id)
                
                navController.navigate(R.id.action_satellitesListFragment_to_sateliiteDetailFragment,b)
            }
        })

        binding.rvSatellites.adapter = adapter
        binding.rvSatellites.layoutManager = LinearLayoutManager(requireContext())

        searchViewListener()
    }

    private fun filterSatellites(text: String) {
        val prevSize = filteredList.size
        filteredList = satellites.filter { it.name.contains(text) } as ArrayList<Satellite>

    }


}