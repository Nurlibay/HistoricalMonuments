package uz.texnopos.historicalmonuments.presenter.search

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.berdaqgargabayuli.utils.inflate
import uz.texnopos.berdaqgargabayuli.utils.onClick
import uz.texnopos.historicalmonuments.R
import uz.texnopos.historicalmonuments.data.entity.Monument
import uz.texnopos.historicalmonuments.databinding.ItemBinding

class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.MainViewHolder>() {

    var models: List<Monument> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onClickItem: (monument: Monument, pos: Int) -> Unit = { _, _ -> }

    fun setOnClickItem(onClickItem: (monument: Monument, pos: Int) -> Unit) {
        this.onClickItem = onClickItem
    }

    inner class MainViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun populateModel(monument: Monument, position: Int) {
            binding.apply {
                tvMonument.text = monument.name
                itemMonument.onClick {
                    onClickItem.invoke(monument, position)
                }

                ivMonument.setImageResource(SearchFragment.list[position])

//                val imageResName = monument.picture
//                ivMonument.setImageResource(binding.root.context.resources.getIdentifier(imageResName, "drawable", binding.root.context.packageName))
//
//                Glide
//                    .with(binding.root.context)
//                    .load(binding.root.context.resources.getIdentifier(imageResName, "drawable", binding.root.context.packageName))
//                    .into(ivMonument)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = parent.inflate(R.layout.item)
        val binding = ItemBinding.bind(itemView)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.populateModel(models[position], position)
    }

    override fun getItemCount() = models.size

    fun filterWordList(s: String, wordList: List<Monument>) {
        val filteredList: MutableList<Monument> = mutableListOf()
        for (word in wordList) {
            if (word.name.lowercase().contains(s)) {
                filteredList.add(word)
            }
        }
        models = filteredList
    }
}