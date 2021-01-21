package com.abdelrahman.football_league_kotlin.ui.destinations.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import com.abdelrahman.football_league_kotlin.R
import com.abdelrahman.football_league_kotlin.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            navigate()
        }, 5000)
    }

    private fun navigate() {
        val extras = FragmentNavigator.Extras.Builder()
            .addSharedElement(binding.splashLogo, "splash")
            .addSharedElement(binding.container, "container")
            .addSharedElement(binding.text,"text")
            .build()

        Navigation.findNavController(binding.splashLogo)
            .navigate(R.id.teamsFragment, null, null, extras)

    }

}