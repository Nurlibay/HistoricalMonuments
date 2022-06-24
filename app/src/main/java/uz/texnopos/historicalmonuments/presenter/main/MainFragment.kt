package uz.texnopos.historicalmonuments.presenter.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import uz.texnopos.berdaqgargabayuli.utils.onClick
import uz.texnopos.historicalmonuments.R
import uz.texnopos.historicalmonuments.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding = FragmentMainBinding.bind(view).apply {
            btnLifestyle.onClick {
                navController.navigate(MainFragmentDirections.actionMainFragmentToLifeStyleFragment())
            }
            btnCreativity.onClick {
                navController.navigate(MainFragmentDirections.actionMainFragmentToCreativityFragment())
            }
            btnSongs.onClick {
                navController.navigate(MainFragmentDirections.actionMainFragmentToSongsFragment())
            }
            btnPoets.onClick {
                navController.navigate(MainFragmentDirections.actionMainFragmentToPoemListFragment3())
            }
        }
    }
}