package uz.texnopos.historicalmonuments.presenter.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.historicalmonuments.utils.ResourceState
import uz.texnopos.berdaqgargabayuli.utils.showMessage
import uz.texnopos.historicalmonuments.R
import uz.texnopos.historicalmonuments.databinding.FragmentSongsBinding

class SearchListFragment : Fragment(R.layout.fragment_songs) {
    private lateinit var binding: FragmentSongsBinding
    private val viewModel: SearchListViewModel by viewModel()
    private val adapter = SearchListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongsBinding.bind(view).apply {
            rvSongs.adapter = adapter
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        viewModel.getSongs()
        setupObserver()
        adapter.setOnClickItem {

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
}