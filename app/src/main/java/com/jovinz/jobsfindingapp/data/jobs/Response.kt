package com.jovinz.jobsfindingapp.data.jobs


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

data class Response(
    @SerializedName("category") var category: String?,
    @SerializedName("categoryDisplay") var categoryDisplay: String?,
    @SerializedName("id") var id: String?
)