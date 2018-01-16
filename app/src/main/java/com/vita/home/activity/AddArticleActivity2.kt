package com.vita.home.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.vita.home.R
import kotlinx.android.synthetic.main.activity_add_article2.*
import kotlinx.android.synthetic.main.app_bar_add_article2.*

class AddArticleActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article2)

        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupFab()
        setupNavView()
    }

    private fun setupToolbar() {
        setSupportActionBar(tb_add_article2)

        val ab = supportActionBar
        ab!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        ab.setDisplayHomeAsUpEnabled(true)

        val toggle = ActionBarDrawerToggle(
                this, drawer_add_article2, tb_add_article2, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_add_article2.setDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupNavView()
            = nav_view_add_article2.setNavigationItemSelectedListener(this@AddArticleActivity2)

    private fun setupFab() =
            fab_in_add_article2.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_add_article2) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add_article2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> {
                // Do nothing
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        val drawer = findViewById(R.id.drawer_add_article2) as DrawerLayout
        drawer.closeDrawer(GravityCompat.END)
        return true
    }
}
