package uz.texnopos.historicalmonuments.presenter.game

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
    private var count: Int = 0
    private var correct: Int = 0
    private lateinit var imageList: MutableList<ImageView>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)
        setupObserver()
        viewModel.getAllMonuments()
        imageList = ArrayList()
        setImage()
        setQuestion()
        binding.btnSave.setOnClickListener {

            val name: String = binding.etAnswerName.text.toString()

            if (count == 9) {
                val bindingResult = CustomDialogResultBinding.inflate(layoutInflater)
                bindingResult.tvResult.text =
                    "Siz 10 sorawdan $correct sorawǵa juwap berdińiz, ${10 - correct} sorawǵa juwap bere almadińiz"
                var dialog = Dialog(requireContext())

                dialog.setContentView(bindingResult.root)

                bindingResult.btnExit.setOnClickListener {
                    dialog.dismiss()
                    findNavController().navigateUp()
                }
                bindingResult.btnRestart.setOnClickListener {
                    dialog.dismiss()
                    count = 0
                    setQuestion()
                }

                dialog.show()
            }
            var dialog = Dialog(requireContext())
            val bindingDialog = CustomDialogInGameBinding.inflate(layoutInflater)
            if (binding.etAnswerName.text.toString().equals(name == models[count].name)) {
                bindingDialog.ivTrueOrFalse.setImageResource(R.drawable.correct)
                bindingDialog.btnContinue.text = "Keyingisi"
                bindingDialog.tvTrueOrFalse.text = "Siz duris juwap berdińiz"
                correct++
            } else {
                bindingDialog.ivTrueOrFalse.setImageResource(R.drawable.incorrect)
                bindingDialog.btnContinue.text = "Jáne urinip kóriń"
                bindingDialog.tvTrueOrFalse.text = "Siz naduris juwap berdińiz"
            }
            dialog.setContentView(bindingDialog.root)
            bindingDialog.btnContinue.setOnClickListener {
                setQuestion()
                dialog.dismiss()
            }
            dialog.show()
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


    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        count++;
        binding.tvCountQuestion.text = "$correct/${count}"
        binding.ivGame.setImageResource(switchImage())
    }

    private fun switchImage(): Int {
        return when (count) {
            0 -> {
                R.drawable.gaur_qala
            }
            1 -> {
                R.drawable.tupraq_qala
            }
            2 -> {
                R.drawable.ustyurt
            }
            3 -> {
                R.drawable.mizdakhan
            }
            4 -> {
                R.drawable.shilpiq
            }
            5 -> {
                R.drawable.qizil_qorgan
            }
            6 -> {
                R.drawable.ayaz_qala
            }
            7 -> {
                R.drawable.djanbas_qala
            }
            8 -> {
                R.drawable.qoyqirilgan_qala
            }
            else -> {
                R.drawable.guldursun_qala
            }
        }
    }

}