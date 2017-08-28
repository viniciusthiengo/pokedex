package thiengo.com.br.pokdex

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import thiengo.com.br.pokdex.domain.Pokemon

/**
 * Created by viniciusthiengo on 27/08/17.
 */
class PokemonsAdapter(
        private val context: Context,
        private val pokemons: List<Pokemon>) :
        RecyclerView.Adapter<PokemonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int) : PokemonsAdapter.ViewHolder {

        val v = LayoutInflater
                .from(context)
                .inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        var ivPokemon: ImageView
        var tvNumero: TextView
        var tvNome: TextView
        var tvTipo: TextView

        init {
            itemView.setOnClickListener(this)
            ivPokemon = itemView.findViewById(R.id.iv_pokemon)
            tvNumero = itemView.findViewById(R.id.tv_numero)
            tvNome = itemView.findViewById(R.id.tv_nome)
            tvTipo = itemView.findViewById(R.id.tv_tipo)
        }

        fun setData(pokemon: Pokemon) {
            ivPokemon.setImageResource( pokemon.imagemRes )
            tvNumero.text = pokemon.numero.toString().padStart(3, '0')
            tvNome.text = pokemon.nome
            tvTipo.text = pokemon.getTipoFormatado()
        }

        override fun onClick(view: View?) {
            val intent = Intent(context, DetalhesActivity::class.java)
            intent.putExtra(Pokemon.KEY, pokemons[adapterPosition])
            context.startActivity(intent)
        }
    }
}