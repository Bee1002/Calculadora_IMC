package com.example.calculadora_imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var pesoInput: EditText
    private lateinit var alturaInput: EditText
    private lateinit var calcularBtn: Button
    private lateinit var resultadoText: TextView
    private lateinit var estadoText: TextView
    private lateinit var imagenEstado: ImageView

    private lateinit var animationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar vistas
        pesoInput = findViewById(R.id.pesoInput)
        alturaInput = findViewById(R.id.alturaInput)
        calcularBtn = findViewById(R.id.calcularBtn)
        resultadoText = findViewById(R.id.resultadoText)
        estadoText = findViewById(R.id.estadoText)
        imagenEstado = findViewById(R.id.imagenEstado)

        // Configurar el botón de cálculo
        calcularBtn.setOnClickListener {
            calcularIMC()

    }
}
    private fun calcularIMC() {
        val pesoStr = pesoInput.text.toString()
        val alturaStr = alturaInput.text.toString()

        if (pesoStr.isNotEmpty() && alturaStr.isNotEmpty()) {
            val peso = pesoStr.toFloat()
            val altura = alturaStr.toFloat() / 100 // Convertir cm a m

            val imc = peso / (altura.pow(2))
            val resultado = "%.2f".format(imc)

            resultadoText.text = "Tu IMC es: $resultado"

            // Determinar estado de salud
            when {
                imc < 18.5 -> {
                    estadoText.text = "Bajo peso"
                    estadoText.setTextColor(getColor(R.color.azul))
                    imagenEstado.setImageResource(R.drawable.bajo_peso)
                }
                imc in 18.5..24.9 -> {
                    estadoText.text = "Peso normal"
                    estadoText.setTextColor(getColor(R.color.verde))
                    imagenEstado.setImageResource(R.drawable.peso_normal)
                }
                imc in 25.0..29.9 -> {
                    estadoText.text = "Sobrepeso"
                    estadoText.setTextColor(getColor(R.color.naranja))
                    imagenEstado.setImageResource(R.drawable.sobrepeso)
                }
                else -> {
                    estadoText.text = "Obesidad"
                    estadoText.setTextColor(getColor(R.color.rojo))
                    imagenEstado.setImageResource(R.drawable.obesidad)
                }
            }
        } else {
            resultadoText.text = "Por favor ingresa peso y altura"
            estadoText.text = ""
            imagenEstado.setImageResource(0)
        }
    }
}
