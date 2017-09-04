package thiengo.com.br.pokdex

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.SpannableString
import android.text.Spanned
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

        fab.setOnClickListener {
            customSnackbar( it )
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        customFontNavigationViewItem()
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

    fun customSnackbar( view: View){
        val snackbar = Snackbar
                .make( view,"Ainda falta implementar uma ação", Snackbar.LENGTH_LONG)
                .setAction("Ação", null)

        val tv = snackbar.view.findViewById<TextView>( android.support.design.R.id.snackbar_text )
        tv.typeface = Typeface.create("sans-serif", Typeface.NORMAL)

        snackbar.show()
    }

    fun customFontNavigationViewItem(){
        val sansSerif = Typeface.create("sans-serif", Typeface.NORMAL)
        val pokemonSolid = ResourcesCompat.getFont(this, R.font.pokemon_solid) ?: sansSerif
        val menu = nav_view.menu

        for( i in 0..menu.size() - 1 ){
            val item = menu.getItem( i )
            setCustomFontItem( item, sansSerif )

            if( item.subMenu != null ){
                setCustomFontItem( item, pokemonSolid )
                val subMenu = item.subMenu

                for( j in 0..subMenu.size() - 1 ) {
                    val subItem = subMenu.getItem(j)
                    setCustomFontItem( subItem, sansSerif )
                }
            }
        }

    }

    fun setCustomFontItem( item: MenuItem, typeface: Typeface ){
        val textItem = SpannableString( item.title )
        textItem.setSpan(
                CustomTypefaceSpan( typeface ),
                0,
                textItem.length,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )

        item.title = textItem
    }
}


