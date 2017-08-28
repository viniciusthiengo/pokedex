package thiengo.com.br.pokdex.util

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan


class CustomTypefaceSpan(typeFace: Typeface) : TypefaceSpan("") {

    val newTypeFace = typeFace

    override fun updateDrawState(paint: TextPaint) {
        applyCustomTypeFace(paint, newTypeFace)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newTypeFace)
    }

    private fun applyCustomTypeFace(paint: Paint, typeface: Typeface) {
        val styleAnterior: Int
        val typefaceAnterior = paint.getTypeface()

        if (typefaceAnterior == null) {
            styleAnterior = 0
        } else {
            styleAnterior = typefaceAnterior.getStyle()
        }

        /* PARA VERIFICAR A COMPATIBILIDADE DE ESTILOS */
        val fake = styleAnterior and typeface.style.inv()

        /*
         * VERIFICA SE A FONTE MAIS ATUAL JÁ ESTÁ DE ACORDO
         * COM A ANTERIOR EM TERMOS DE "TEXTO EM NEGRITO",
         * CASO NÃO, ATUALIZA.
         * */
        if (fake and Typeface.BOLD != 0) {
            paint.setFakeBoldText(true)
        }

        /*
         * VERIFICA SE A FONTE MAIS ATUAL JÁ ESTÁ DE ACORDO
         * COM A ANTERIOR EM TERMOS DE "TEXTO EM ITÁLICO",
         * CASO NÃO, ATUALIZA.
         * */
        if (fake and Typeface.ITALIC != 0) {
            paint.setTextSkewX(-0.25f)
        }

        /* APLICA A FONTE */
        paint.setTypeface(typeface)
    }
}