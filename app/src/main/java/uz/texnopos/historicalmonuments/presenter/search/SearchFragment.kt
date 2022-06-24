package uz.texnopos.historicalmonuments.presenter.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.berdaqgargabayuli.utils.showMessage
import uz.texnopos.historicalmonuments.R
import uz.texnopos.historicalmonuments.data.entity.Monument
import uz.texnopos.historicalmonuments.databinding.FragmentSearchBinding
import uz.texnopos.historicalmonuments.utils.ResourceState

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private val adapter = SearchListAdapter()
    private var monuments: List<Monument> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view).apply {
            rvMonument.adapter = adapter
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        viewModel.getAllMonuments()
        setupObserver()
        adapter.setOnClickItem { monument, pos ->
            val action = SearchFragmentDirections.actionSearchFragmentToDescriptionFragment(monument.name, pos, monument.description)
            findNavController().navigate(action)
        }

        binding.etSearch.addTextChangedListener {
            adapter.filterWordList(it.toString(), monuments)
            binding.tvNotFound.isVisible = adapter.models.isEmpty() && !it.isNullOrEmpty()
        }

    }

    private fun setupObserver() {
        viewModel.songs.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {}
                ResourceState.SUCCESS -> {
                    adapter.models = it.data!!
                    monuments = it.data
                }
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
            }
        }
    }

    companion object {
        var list = listOf(
            R.drawable.ayaz_kala,
            R.drawable.chilpak,
            R.drawable.djanbas_kala,
            R.drawable.ayaz_kala,
            R.drawable.chilpak,
            R.drawable.djanbas_kala,
            R.drawable.ayaz_kala,
            R.drawable.chilpak,
            R.drawable.djanbas_kala,
            R.drawable.ayaz_kala,
            R.drawable.chilpak,
            R.drawable.djanbas_kala
        )
    }
}