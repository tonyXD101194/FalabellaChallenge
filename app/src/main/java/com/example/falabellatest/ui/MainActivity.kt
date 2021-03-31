package com.example.falabellatest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.falabellatest.R
import com.example.falabellatest.interfaces.NavigationInterface
import com.example.falabellatest.ui.main.MainFragment

class MainActivity : AppCompatActivity(), NavigationInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        this.initializeFragments()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        val searchViewMenuItem: MenuItem = menu!!.findItem(R.id.action_search)

        val searchView = searchViewMenuItem.actionView as androidx.appcompat.widget.SearchView

        searchView.queryHint = resources.getString(R.string.fragment_main_search_hint)

        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                val fragment = supportFragmentManager.findFragmentById(R.id.container)

                if (fragment is MainFragment && query != null) {

                    fragment.searchByCode(query)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        searchView.setOnCloseListener {

            val fragment = supportFragmentManager.findFragmentById(R.id.container)

            if (fragment is MainFragment) {

                fragment.resetList()
            }

            false
        }

        return super.onPrepareOptionsMenu(menu)
    }

    private fun initializeFragments() {

        pushFragment(MainFragment.newInstance(this))
    }

    // region navigation

    override fun pushFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }

    override fun popFragment() {

        supportFragmentManager.popBackStack()
    }

    // endregion
}