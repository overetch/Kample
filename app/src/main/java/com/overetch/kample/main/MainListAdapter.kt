package com.overetch.kample.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.overetch.kample.R
import com.overetch.kample.data.Movie
import java.util.*
import java.util.zip.Inflater
/**
 * Created by Igor.Sakovich on 21.01.2017.
 */
class MainListAdapter(val movies: ArrayList<Movie>) : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.onBind(movies.get(position))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.main_adapter_list_item,parent,false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val mTitle = itemView?.findViewById(R.id.title) as TextView

        fun onBind(movie: Movie) {
            mTitle.text = movie.Title
        }
    }
}