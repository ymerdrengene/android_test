package dk.harj_it.utils

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String?) {
    val colorDrawable = ColorDrawable(ContextCompat.getColor(view.context, android.R.color.darker_gray))

    val options = RequestOptions()
        .placeholder(colorDrawable)
        .error(colorDrawable)

    Glide
        .with(view)
        .load(url)
//        .apply(options)
        .into(view)
}