package com.dicoding.applicationdicodingevent.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.applicationdicodingevent.databinding.ItemSettingBinding

class SettingsAdapter(private val settingsList: List<SettingItem>) : RecyclerView.Adapter<SettingsAdapter.SettingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val binding = ItemSettingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        val settingItem = settingsList[position]
        holder.bind(settingItem)
    }

    override fun getItemCount() = settingsList.size

    inner class SettingViewHolder(private val binding: ItemSettingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(settingFragment: SettingItem) {
            binding.apply {
                idSetting.text = settingFragment.title
                switchTheme.isChecked = settingFragment.isEnabled
                switchTheme.setOnCheckedChangeListener { _, isChecked ->
                    settingFragment.isEnabled = isChecked
                }
            }
        }
    }
}

data class SettingItem(val title: String, var isEnabled: Boolean)
