package tr.com.mekhti.satellitesapp.view.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import tr.com.mekhti.satellitesapp.data.model.Satellite
import tr.com.mekhti.satellitesapp.databinding.SatelliteItemBinding
import tr.com.mekhti.satellitesapp.listeners.SatelliteListener
import java.util.*
import kotlin.collections.ArrayList

class SatellitesAdapter(
    private val satellites:ArrayList<Satellite>,
    private val listener : SatelliteListener

) : RecyclerView.Adapter<SatellitesAdapter.SatelliteViewHolder>(), Filterable {

    var satelliteFilterList = ArrayList<Satellite>()

    init {
        satelliteFilterList = satellites
    }


    class SatelliteViewHolder(private val binding: SatelliteItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(satellite : Satellite,listener : SatelliteListener) {
            binding.satellite = satellite

            binding.root.setOnClickListener {
                listener.onClick(satellite.id)
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent : ViewGroup) : SatelliteViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SatelliteItemBinding.inflate(layoutInflater,parent,false)
                return SatelliteViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder {
        return SatelliteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        val currentSatellite = satelliteFilterList[position]
        holder.bind(currentSatellite,listener)
    }

    override fun getItemCount() = satelliteFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    satelliteFilterList = satellites
                } else {
                    val resultList = ArrayList<Satellite>()
                    for (satellite in satellites) {
                        if (satellite.name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(satellite)
                        }
                    }
                    satelliteFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = satelliteFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                satelliteFilterList = results?.values as ArrayList<Satellite>
                notifyDataSetChanged()
            }

        }
    }

}