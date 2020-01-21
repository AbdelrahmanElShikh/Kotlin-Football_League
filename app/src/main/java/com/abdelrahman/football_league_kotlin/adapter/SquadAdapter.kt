package com.abdelrahman.football_league_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdelrahman.football_league_kotlin.databinding.SquadItemBinding
import com.abdelrahman.football_league_kotlin.model.Squad
import kotlin.properties.Delegates


/**
@author  Abdel-Rahman El-Shikh on 21-Jan-20.
 */
class SquadAdapter : RecyclerView.Adapter<SquadAdapter.ViewHolder>() {
    private var squadMembers: List<Squad> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SquadItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = squadMembers[position]
        holder.binding.player = player
    }
    fun updateSquade(squads: List<Squad>) {
        squadMembers = squads
    }

    override fun getItemCount(): Int = squadMembers.size


    class ViewHolder(var binding: SquadItemBinding) : RecyclerView.ViewHolder(binding.root)
}
