package com.jovinz.jobsfindingapp.data.jobs


import com.google.gson.annotations.SerializedName


data class JobsByLangResponseItem(
    @SerializedName("company") var company: String?,
    @SerializedName("company_logo") var companyLogo: String?,
    @SerializedName("company_url") var companyUrl: String?,
    @SerializedName("created_at") var createdAt: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("how_to_apply") var howToApply: String?,
    @SerializedName("id") var id: String?,
    @SerializedName("location") var location: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("url") var url: String?
)