package com.example.healthtest1

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.healthtest1.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_add_patient.*
import kotlinx.android.synthetic.main.app_bar_main.*

class AddPatient : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)


        //toolbar.title = "Tab Layout"
        setSupportActionBar(toolbar)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        view_pager.adapter = fragmentAdapter

        tabs.setupWithViewPager(view_pager)
        /*
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()


        }
        */
    }
}