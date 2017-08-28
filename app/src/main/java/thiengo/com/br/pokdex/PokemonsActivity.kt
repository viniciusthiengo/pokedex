package thiengo.com.br.pokdex

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.Spannable
import android.text.SpannableString
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pokemons.*
import kotlinx.android.synthetic.main.app_bar_pokemons.*
import thiengo.com.br.pokdex.data.Mock
import thiengo.com.br.pokdex.util.CustomTypefaceSpan


class PokemonsActivity :
        AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemons)
        setSupportActionBar(toolbar)
        applyToolbarCustomFont()

        fab.setOnClickListener { customSnackbar( it ) }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        customFontNavigationViewMenu()
        initRecycler()
    }

    private fun initRecycler() {
        rv_pokemons.setHasFixedSize(true)

        val mLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        rv_pokemons.layoutManager = mLayoutManager

        rv_pokemons.adapter = PokemonsAdapter(this, Mock.getPokemons())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.pokemons, menu)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }

    fun customSnackbar( view: View ) {
        val snackBar = Snackbar
            .make(
                view,
                "Ainda falta implementar uma ação",
                Snackbar.LENGTH_LONG)
            .setAction("Ação", null)

        val tv = snackBar.getView().findViewById<TextView>(android.support.design.R.id.snackbar_text)
        val font = ResourcesCompat.getFont(this@PokemonsActivity, R.font.pokemon_solid)
        tv.typeface = font

        snackBar.show()
    }

    fun applyToolbarCustomFont() {
        for (i in 0..toolbar.childCount - 1) {
            val view = toolbar.getChildAt(i)
            if (view is TextView) {
                if (view.text == toolbar.title) {
                    view.typeface = ResourcesCompat.getFont(this, R.font.pokemon_solid)
                    break
                }
            }
        }
    }

    private fun customFontNavigationViewMenu() {
        val menu = nav_view.menu
        for (i in 0..menu.size() - 1) {
            val menuItem = menu.getItem(i)
            setCustomFontMenuItem(menuItem)

            if( menuItem.subMenu != null ){
                val subMenu = menuItem.subMenu
                for (j in 0..subMenu.size() - 1) {
                    val menuItem2 = subMenu.getItem(j)
                    setCustomFontMenuItem(menuItem2)
                }
            }
        }
    }

    private fun setCustomFontMenuItem(menuItem: MenuItem) {
        val font = ResourcesCompat.getFont(this, R.font.pokemon_adaptado)
        val textItem = SpannableString(menuItem.getTitle())
        textItem.setSpan(
            CustomTypefaceSpan(font ?: Typeface.DEFAULT),
            0,
            textItem.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE )
        menuItem.setTitle(textItem)
    }
}
