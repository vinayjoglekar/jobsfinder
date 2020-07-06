package com.jovinz.jobsfindingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jovinz.jobsfindingapp.R
import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import com.jovinz.jobsfindingapp.databinding.ItemJobsListBinding

class JobsRecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var jobs: MutableList<JobsByLangResponseItem> = ArrayList()

    inner class JobsRecyclerViewHolder(val binding: ItemJobsListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemJobsListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_jobs_list,
            parent,
            false
        )
        return JobsRecyclerViewHolder(binding)
    }

    fun setJobs(jobs: List<JobsByLangResponseItem>) {
        this.jobs.clear()
        this.jobs.addAll(jobs)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val jobsRecyclerViewHolder = holder as JobsRecyclerViewHolder
        val job = jobs[jobsRecyclerViewHolder.adapterPosition]
        jobsRecyclerViewHolder.binding.job = job

        Glide.with(jobsRecyclerViewHolder.binding.imgCompany.context)
            .load(job.companyLogo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(jobsRecyclerViewHolder.binding.imgCompany)
    }


}