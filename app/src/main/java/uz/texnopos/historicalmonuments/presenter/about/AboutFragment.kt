package uz.texnopos.historicalmonuments.presenter.about

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import uz.texnopos.historicalmonuments.R
import uz.texnopos.historicalmonuments.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {

    private lateinit var binding: FragmentAboutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view).apply {
            tvAbout.movementMethod = LinkMovementMethod.getInstance()
        }
    }

}
