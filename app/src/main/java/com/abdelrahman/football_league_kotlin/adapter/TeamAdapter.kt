package com.abdelrahman.football_league_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdelrahman.football_league_kotlin.databinding.TeamItemBinding
import com.abdelrahman.football_league_kotlin.model.Team
import kotlin.properties.Delegates


/**
@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
class TeamAdapter(var listener:OnTeamClick) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    private var teams: List<Team> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TeamItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]
        holder.binding.team = team
    }

    override fun getItemCount(): Int = teams.size


    fun updateTeams(newTeams: List<Team>) {
        teams = newTeams
    }

    interface OnTeamClick{
        fun onTeamClick(teamId: Int)
        fun onWebsiteClick(website: String?)
    }

    inner class ViewHolder(var binding: TeamItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener{
                listener.onTeamClick(teams[adapterPosition].id)
            }
            binding.txtWebsite.setOnClickListener{
                listener.onWebsiteClick(teams[adapterPosition].website)
            }
        }
    }
}
