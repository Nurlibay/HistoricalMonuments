package uz.texnopos.historicalmonuments.presenter.main.description

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.texnopos.historicalmonuments.R
import uz.texnopos.historicalmonuments.databinding.FragmentDescriptionBinding
import uz.texnopos.historicalmonuments.presenter.main.MainFragment

class DescriptionFragment : Fragment(R.layout.fragment_description) {

    private lateinit var binding: FragmentDescriptionBinding
    private val args: DescriptionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescriptionBinding.bind(view).apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.title = args.name
            tvDescription.text = args.description
            ivMonument.setImageResource(MainFragment.list[args.pos])
        }
    }
}