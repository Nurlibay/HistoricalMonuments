package uz.texnopos.historicalmonuments.presenter.game

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import uz.texnopos.historicalmonuments.R
import uz.texnopos.historicalmonuments.data.entity.Monument
import uz.texnopos.historicalmonuments.databinding.CustomDialogInGameBinding
import uz.texnopos.historicalmonuments.databinding.CustomDialogResultBinding
import uz.texnopos.historicalmonuments.databinding.FragmentGameBinding

class GameFragment(private val models: List<Monument>) : Fragment(R.layout.fragment_game) {
    private lateinit var binding: FragmentGameBinding
    private var count: Int = -1
    private var c: Boolean = true
    private var corrent_answer: Int = 0
    private var incorrect_answer: Int = 0
    private lateinit var imageList: MutableList<ImageView>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)

        val bindingDialog = CustomDialogInGameBinding.inflate(layoutInflater)
        var bindingResult = CustomDialogResultBinding.inflate(layoutInflater)

        setImage()

        setQuestion()


        binding.btnSave.setOnClickListener {

            var name: String = binding.etAnswerName.text.toString()

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
               R.drawable.gaur_kala_in_karakalpakstan
            }
            1->{
                R.drawable.tupra_kala_in_karakalpakstan
            }
            2->{
                R.drawable.ustyurt_platasi
            }
            3->{
                R.drawable.mizdahkan
            }
            4->{
                R.drawable.chilpak
            }
            5->{
                R.drawable.kizil_kala
            }
            6->{
                R.drawable.ayaz_kala
            }
            7->{
                R.drawable.djanbas_kala
            }
            8->{
                R.drawable.visit_koi_krylgan_kala
            }
            else ->{
                R.drawable.guldursun_kala
            }
        }
    }


}