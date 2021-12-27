package tr.com.mekhti.satellitesapp.view.fragments.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

class FragmentSatelliteDetailBinding {
    companion object{

        @BindingAdapter("txtHeight","txtMass")
        @JvmStatic
        fun setHeightMass(textView: TextView, height: Int,mass: Long) {
            textView.text = "$height/$mass"
        }


        @BindingAdapter("txtCost")
        @JvmStatic
        fun txtCost(textView: TextView, cost: Long) {
            textView.text = "$cost"
        }

        @BindingAdapter("txtPosX","txtPosY")
        @JvmStatic
        fun showPositions(textView: TextView, posX: Double,posY: Double) {
            textView.text = "($posX , $posY)"
        }
    }
}