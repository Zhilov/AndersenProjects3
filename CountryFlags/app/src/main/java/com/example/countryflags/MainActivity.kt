package com.example.countryflags

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {

    private lateinit var listview: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listview = findViewById(R.id.countries_list)

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.country_names))
        listview.adapter = adapter

        listview.setOnItemClickListener { _, _, position, _ ->
            run {
                when (position) {
                    0 -> changeFragment<FragmentAustria>()
                    1 -> changeFragment<FragmentPoland>()
                    2 -> changeFragment<FragmentItaly>()
                    3 -> changeFragment<FragmentColombia>()
                    4 -> changeFragment<FragmentMadagascar>()
                    5 -> changeFragment<FragmentThailand>()
                    6 -> changeFragment<FragmentDenmark>()
                    7 -> changeFragment<FragmentSwitzerland>()
                }
            }
        }
    }

    private inline fun <reified Fragment : androidx.fragment.app.Fragment> changeFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<Fragment>(R.id.fragment_container)
        }
    }
}