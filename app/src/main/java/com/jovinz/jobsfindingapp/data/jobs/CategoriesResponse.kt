package com.jovinz.jobsfindingapp.data.jobs


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable


data class CategoriesResponse(
    @SerializedName("response") var response: List<Response>?
)