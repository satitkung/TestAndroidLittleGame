package com.example.test

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget

fun ImageView.loadImageCircle(res: Int) {
    Glide.with(this)
        .load(res)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun ImageView.loadImage(res: Int) {
    Glide.with(this)
        .load(res)
        .into(this)
}

fun ImageView.loadImageRoundCorner(
    url: String,
    placeholder: Drawable?
) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(24)))
        .into(this)
}

fun ImageView.loadImageUrl(
    url: String,
    @DrawableRes
    placeholder: Int = -1
) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.loadImageUrlNoTransform(
    url: String,
    @DrawableRes
    placeholder: Int = -1
) {
    Glide.with(this)
        .load(url)
        .dontTransform()
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.loadImageCircleUrl(
    url: String,
    @DrawableRes
    placeholder: Int = -1,
    @DrawableRes
    error: Int = R.drawable.ic_add
) {
    Glide.with(this)
        .asBitmap()
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .fitCenter()
        .into(object : BitmapImageViewTarget(this) {
            override fun setResource(resource: Bitmap?) {
                resource?.let {
                    val bitmap = createSquaredBitmap(it)
                    val drawable = RoundedBitmapDrawableFactory.create(
                        this@loadImageCircleUrl.context.resources, bitmap
                    )
                    drawable.isCircular = true
                    this.setDrawable(drawable)
                }
            }
        })
}

private fun createSquaredBitmap(srcBmp: Bitmap): Bitmap {
    val dstSize = Math.max(srcBmp.width, srcBmp.height)
    val dstBmp = Bitmap.createBitmap(dstSize, dstSize, Bitmap.Config.ARGB_8888)
    //Squared image
    var x = 0
    var y = 0
    if (srcBmp.width > srcBmp.height) { //landScape image
        x = 0
        y = (dstSize - srcBmp.height) / 2
    } else if (srcBmp.width < srcBmp.height) { //portrait image
        x = (dstSize - srcBmp.width) / 2
        y = 0
    }
    val sourceRect = Rect(0, 0, srcBmp.width, srcBmp.height)
    val destinationRect = Rect(x, y, x + srcBmp.width, y + srcBmp.height)
    val canvas = Canvas(dstBmp)
    canvas.drawColor(Color.WHITE)
    canvas.drawBitmap(srcBmp, sourceRect, destinationRect, null)
    return dstBmp
}