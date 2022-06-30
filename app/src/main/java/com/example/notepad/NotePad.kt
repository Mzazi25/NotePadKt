package com.example.notepad

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notepad.databinding.NotepadBinding
import com.example.notepad.ui.home.HomeFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.notepad.*

class NotePad : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: NotepadBinding
    private val noteLayoutManager by lazy {
        LinearLayoutManager(this)
    }
    private val noteRecyclerAdapter by lazy {
        NoteRecyclerAdapter(this, DataManager.notes)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NotepadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarNotePad.toolbar)

        binding.appBarNotePad.fab.setOnClickListener { view ->
            startActivity(Intent(this,MainActivity::class.java))

            displayNotes()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_note_pad)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_Notes, R.id.nav_courses, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_Notes -> displayNotes()
                R.id.nav_courses -> {
                    Snackbar.make(listItems, "Hey there", Snackbar.LENGTH_LONG)
                }

            }
            true
        }
    }

    private fun displayNotes() {
        listItems.layoutManager = noteLayoutManager
        listItems.adapter = noteRecyclerAdapter
        nav_view.menu.findItem(R.id.nav_Notes).isChecked= true
    }


    override fun onPause() {
        super.onPause()
        listItems.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.note_pad, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_note_pad)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}