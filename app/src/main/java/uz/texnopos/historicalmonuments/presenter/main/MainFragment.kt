package uz.texnopos.historicalmonuments.presenter.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.berdaqgargabayuli.utils.showMessage
import uz.texnopos.historicalmonuments.R
import uz.texnopos.historicalmonuments.databinding.FragmentMainBinding
import uz.texnopos.historicalmonuments.utils.ResourceState

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val adapter = MainListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            rvMonument.adapter = adapter
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        viewModel.getAllMonuments()
        setupObserver()
        adapter.setOnClickItem { monument, pos ->
            val action = MainFragmentDirections.actionMainFragmentToDescriptionFragment(monument.name, pos, monument.description)
            findNavController().navigate(action)
        }
    }

    private fun setupObserver() {
        viewModel.songs.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {}
                ResourceState.SUCCESS -> {
                    adapter.models = it.data!!
                }
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
            }
        }
    }

    companion object {
        var list = listOf(
            R.drawable.gaur_qala,
            R.drawable.tupraq_qala,
            R.drawable.ustyurt,
            R.drawable.mizdakhan,
            R.drawable.shilpiq,
            R.drawable.qizil_qorgan,
            R.drawable.ayaz_qala,
            R.drawable.djanbas_qala,
            R.drawable.qoyqirilgan_qala,
            R.drawable.guldursun_qala,
        )
    }
}