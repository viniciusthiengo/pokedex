package thiengo.com.br.pokdex.domain

import android.os.Parcel
import android.os.Parcelable


class Pokemon(
        val numero: Int,
        val nome: String,
        val imagemRes: Int,
        val tipo: Array<String>,
        val descricao: String,
        val altura: String,
        val peso: String,
        val sexo: String,
        val categoria: String,
        val habilidades: Array<String>,
        val fraquezas: Array<String>) : Parcelable {

    fun getTipoFormatado() = tipo.joinToString(", ")

    fun getHabilidadesFormatado() = habilidades.joinToString(", ")

    fun getFraquezasFormatado() = fraquezas.joinToString(", ")

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.createStringArray(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.createStringArray(),
            source.createStringArray()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(numero)
        writeString(nome)
        writeInt(imagemRes)
        writeStringArray(tipo)
        writeString(descricao)
        writeString(altura)
        writeString(peso)
        writeString(sexo)
        writeString(categoria)
        writeStringArray(habilidades)
        writeStringArray(fraquezas)
    }

    companion object {
        @JvmField val KEY = "pokemon"

        @JvmField
        val CREATOR: Parcelable.Creator<Pokemon> = object : Parcelable.Creator<Pokemon> {
            override fun createFromParcel(source: Parcel): Pokemon = Pokemon(source)
            override fun newArray(size: Int): Array<Pokemon?> = arrayOfNulls(size)
        }
    }
}