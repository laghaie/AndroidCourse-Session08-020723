package com.example.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.tablayout.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val myViewPager = findViewById<ViewPager2>(R.id.myViewPager)

        val adapter = PagerAdapter(supportFragmentManager, lifecycle)
        myViewPager.adapter = adapter

        TabLayoutMediator(tabLayout, myViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Chat"
                1 -> tab.text = "Status"
                2 -> tab.text = "Calls"
            }
        }.attach()
    }
}