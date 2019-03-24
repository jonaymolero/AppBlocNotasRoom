package net.azarquiel.bloc_de_notas.model

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet

class EditTextLineas(context: Context, attrs: AttributeSet) : android.support.v7.widget.AppCompatEditText(context, attrs) {

    private val paint: Paint

    init {
        paint = Paint()
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // nº lineas = Altura de la view / Altura de linea en un EditText
        val count = height / lineHeight

        // Baseline va a ser la coordenada "y" para pintar las líneas
        var baseline = lineHeight + 4

        for (i in 0 until count) {
            // pintamos la linea de left a right en la posicion de y=baseline
            // más uno para que las letras no tapen la linea
            canvas.drawLine(0f, (baseline + 1).toFloat(), width.toFloat(), (baseline + 1).toFloat(), paint)
            // preparamos la próxima baseline
            baseline += lineHeight
        }
    }
}