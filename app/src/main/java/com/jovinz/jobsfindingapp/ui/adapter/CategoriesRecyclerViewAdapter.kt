package com.jovinz.jobsfindingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jovinz.jobsfindingapp.R
import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import com.jovinz.jobsfindingapp.data.jobs.Response
import com.jovinz.jobsfindingapp.databinding.ItemCategoriesListBinding
import com.jovinz.jobsfindingapp.databinding.ItemJobsListBinding
import com.jovinz.jobsfindingapp.ui.fragments.onItemClickListener

class CategoriesRecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categories: MutableList<Response> = ArrayList()

    lateinit var onItemClickListener: onItemClickListener

    inner class CategoriesRecyclerViewHolder(val binding: ItemCategoriesListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemCategoriesListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_categories_list,
            parent,
            false
        )
        return CategoriesRecyclerViewHolder(binding)
    }

    fun setCategories(categories: List<Response>) {
        this.categories.addAll(categories)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val jobsRecyclerViewHolder = holder as CategoriesRecyclerViewHolder
        val category = categories[jobsRecyclerViewHolder.adapterPosition]
        jobsRecyclerViewHolder.binding.category = category

        jobsRecyclerViewHolder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(
                category.category
            )
        }


    }


}