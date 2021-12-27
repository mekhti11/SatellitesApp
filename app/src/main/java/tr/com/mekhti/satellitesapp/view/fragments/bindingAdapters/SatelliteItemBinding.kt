package tr.com.mekhti.satellitesapp.view.fragments.bindingAdapters

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import tr.com.mekhti.satellitesapp.R

class SatelliteItemBinding {

    companion object{

        @SuppressLint("SetTextI18n")
        @BindingAdapter("isActive")
        @JvmStatic
        fun isActive(textView: TextView, isActive: Boolean) {
            if (isActive){
                textView.text = "Active"
            }else{
                textView.text = "Passive"
            }
        }

        @BindingAdapter("isActiveCircle")
        @JvmStatic
        fun isActiveCircle(imageView: ImageView, isActive: Boolean) {
            if (isActive){
                imageView.setImageResource(R.drawable.green_circle)
            }else{
                imageView.setImageResource(R.drawable.red_circle)
            }
        }

    }

}