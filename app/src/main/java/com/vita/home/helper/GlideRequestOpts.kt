package com.vita.home.helper

import com.bumptech.glide.request.RequestOptions
import com.vita.home.R

/**
 *
 * @FileName: com.vita.home.helper.GlideRequestOpts.java
 * @Author: Vita
 * @Date: 2018-01-15 10:18
 * @Usage:
 */
object GlideRequestOpts {

    var baseOpts = RequestOptions()
            .error(R.drawable.lan)
            .placeholder(R.drawable.lan)
            .fallback(R.drawable.lan)

    var centerCropOpts = baseOpts.centerCrop()
    var fitCenterOpts = baseOpts.fitCenter()
    var circleCropOpts = baseOpts.circleCrop()
    var centerInsideOpts = baseOpts.centerInside()

    var basePersonOpts = RequestOptions()
            .error(R.drawable.ic_person_white_24dp)
            .placeholder(R.drawable.ic_person_white_24dp)
            .fallback(R.drawable.ic_person_white_24dp)

    var baseImageOpts = RequestOptions()
            .error(R.drawable.ic_image_lightgray_24dp)
            .placeholder(R.drawable.ic_image_lightgray_24dp)
            .fallback(R.drawable.ic_image_lightgray_24dp)
}