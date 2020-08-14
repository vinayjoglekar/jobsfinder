package com.jovinz.jobsfindingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jovinz.jobsfindingapp.R
import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import com.jovinz.jobsfindingapp.databinding.ItemJobsListBinding

class JobsPagedAdapter :
    PagingDataAdapter<JobsByLangResponseItem, JobsPagedAdapter.JobsPagedViewHolder>(
        DataDifferntiator
    ) {

    inner class JobsPagedViewHolder(val binding: ItemJobsListBinding) :
        RecyclerView.ViewHolder(binding.root)

    object DataDifferntiator : DiffUtil.ItemCallback<JobsByLangResponseItem>() {

        override fun areItemsTheSame(
            oldItem: JobsByLangResponseItem,
            newItem: JobsByLangResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: JobsByLangResponseItem,
            newItem: JobsByLangResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsPagedViewHolder {
        val binding = DataBindingUtil.inflate<ItemJobsListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_jobs_list,
            parent,
            false
        )
        return JobsPagedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobsPagedViewHolder, position: Int) {
        holder.binding.job = getItem(position)

        Glide.with(holder.binding.imgCompany.context)
            .load(getItem(position)?.companyLogo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.binding.imgCompany)

        itemAction?.let {
            holder.itemView.setOnClickListener { it(getItem(position)?.id!!) }
        }
    }

    private var itemAction: ((String) -> Unit)? = null

    fun setItemAction(action: (String) -> Unit) {
        this.itemAction = action
    }

}