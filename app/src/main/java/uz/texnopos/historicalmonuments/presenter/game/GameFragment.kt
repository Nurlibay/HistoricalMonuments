package uz.texnopos.historicalmonuments.presenter.game

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.berdaqgargabayuli.utils.showMessage
import uz.texnopos.historicalmonuments.R
import uz.texnopos.historicalmonuments.data.entity.Monument
import uz.texnopos.historicalmonuments.databinding.CustomDialogInGameBinding
import uz.texnopos.historicalmonuments.databinding.CustomDialogResultBinding
import uz.texnopos.historicalmonuments.databinding.FragmentGameBinding
import uz.texnopos.historicalmonuments.presenter.main.MainViewModel
import uz.texnopos.historicalmonuments.utils.ResourceState

class GameFragment() : Fragment(R.layout.fragment_game) {

    private val viewModel: MainViewModel by viewModel()

    private var models: List<Monument> = emptyList()

    private lateinit var binding: FragmentGameBinding

    private var count: Int = -1
    private var c: Boolean = true
    private var corrent_answer: Int = 0
    private var incorrect_answer: Int = 0
    private lateinit var imageList: MutableList<ImageView>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)

        setupObserver()
        viewModel.getAllMonuments()

        val bindingDialog = CustomDialogInGameBinding.inflate(layoutInflater)
        val bindingResult = CustomDialogResultBinding.inflate(layoutInflater)

        setImage()

        setQuestion()

        binding.btnSave.setOnClickListener {

            val name: String = binding.etAnswerName.text.toString()

            if ((count == 9) || (incorrect_answer == 4 && name == models[count].name) ) {
                bindingResult.tvResult.text =
                    "siz 10 ta savoldan $corrent_answer ta savolga javob berdingiz, ${10-corrent_answer} ta savolga javob bera olmadingiz"
                var dialog = Dialog(requireContext())
                dialog.setContentView(bindingResult.root)
                bindingResult.btnExit.setOnClickListener {
                    dialog.dismiss()
                    // TODO: back
                }
                bindingResult.btnRestart.setOnClickListener {
                    dialog.dismiss()
                    count = -1
                    setQuestion()
                }
                dialog.show()
            } else {
                if (name == models[count].name) {
                    c = true
                    bindingDialog.btnContinue.text = "continue"
                    bindingDialog.tvTrueOrFalse.text = "siz to'g'i javob berdingiz"
                    bindingDialog.ivTrueOrFalse.setImageResource(R.drawable.correct)
                } else {
                    c = false
                    bindingDialog.btnContinue.text = "takroran urinib ko'rish"
                    bindingDialog.tvTrueOrFalse.text = "siz noto'g'i javob berdingiz"
                    bindingDialog.ivTrueOrFalse.setImageResource(R.drawable.incorrect)
                }
                var dialog = Dialog(requireContext())
                dialog.setContentView(bindingDialog.root)
                bindingDialog.btnContinue.setOnClickListener {
                    if (c) {
                        corrent_answer++;
                        setQuestion()
                    } else {
                        imageList[incorrect_answer].setImageResource(R.drawable.light_bulb_black)
                        incorrect_answer++;
                    }
                    dialog.dismiss()
                }
                dialog.show()
            }
        }


    }

    private fun setupObserver() {
        viewModel.songs.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {}
                ResourceState.SUCCESS -> {
                    models = it.data!!
                }
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
            }
        }
    }

    private fun setImage() {
        imageList.add(binding.ivLightOne)
        imageList.add(binding.ivLightTwo)
        imageList.add(binding.ivLightThree)
        imageList.add(binding.ivLightFour)
        imageList.add(binding.ivLightFive)
    }


    private fun setQuestion() {
        count++;
        binding.tvCountQuestion.text = "$corrent_answer/${count + 1}"
        binding.tvDescription.text = models[count].description
        binding.ivGame.setImageResource(switchImage())
    }

    private fun switchImage():Int{
        return when(count){
            0->{
                R.drawable.gaur_qala
            }
            1->{
                R.drawable.tupraq_qala
            }
            2->{
                R.drawable.ustyurt
            }
            3->{
                R.drawable.mizdakhan
            }
            4->{
                R.drawable.shilpiq
            }
            5->{
                R.drawable.qizil_qorgan
            }
            6->{
                R.drawable.ayaz_qala
            }
            7->{
                R.drawable.djanbas_qala
            }
            8->{
                R.drawable.qoyqirilgan_qala
            }
            else ->{
                R.drawable.guldursun_qala
            }
        }
    }


}