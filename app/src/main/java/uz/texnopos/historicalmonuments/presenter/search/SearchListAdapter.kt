package uz.texnopos.historicalmonuments.presenter.search

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.berdaqgargabayuli.utils.inflate
import uz.texnopos.berdaqgargabayuli.utils.onClick
import uz.texnopos.historicalmonuments.R
import uz.texnopos.historicalmonuments.data.entity.Monument
import uz.texnopos.historicalmonuments.databinding.ItemLifestyleBinding

class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.SongsViewHolder>() {

    var models: List<Monument> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onClickItem: (songs: Monument) -> Unit = {}

    fun setOnClickItem(onClickItem: (songs: Monument) -> Unit) {
        this.onClickItem = onClickItem
    }

    inner class SongsViewHolder(private val binding: ItemLifestyleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun populateModel(songs: Monument) {
            binding.apply {
                tvLifeAge.text = songs.name
                itemAgeStatus.onClick {
                    onClickItem.invoke(songs)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val itemView = parent.inflate(R.layout.item_lifestyle)
        val binding = ItemLifestyleBinding.bind(itemView)
        return SongsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size
}