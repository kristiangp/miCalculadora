package com.example.myapplication3

import CalculadoraViewModel
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    var number: Int = 1
    lateinit var viewModel: CalculadoraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(CalculadoraViewModel::class.java)
        setContent()
    }

    fun setContent() {
        binding.btnCero.setOnClickListener(this)
        binding.btnUno.setOnClickListener(this)
        binding.btnDos.setOnClickListener(this)
        binding.btnTres.setOnClickListener(this)
        binding.btnCuatro.setOnClickListener(this)
        binding.btnCinco.setOnClickListener(this)
        binding.btnSeis.setOnClickListener(this)
        binding.btnSiete.setOnClickListener(this)
        binding.btnOcho.setOnClickListener(this)
        binding.btnNueve.setOnClickListener(this)
        binding.btnBorrar.setOnClickListener(this)
        binding.btnAC.setOnClickListener(this)
        binding.btnPunto.setOnClickListener(this)
        binding.btnIgual.setOnClickListener(this)
        binding.btnAbrirParentesis.setOnClickListener(this)
        binding.btnCerrarParentesis.setOnClickListener(this)
        binding.btnDivision.setOnClickListener { viewModel.onClickDivision() }
        binding.btnMultiplicacion.setOnClickListener { viewModel.onClickMultiplicacion() }
        binding.btnSuma.setOnClickListener { viewModel.onClickSuma() }
        binding.btnResta.setOnClickListener { viewModel.onClickResta() }
    }

    fun actualizarTextView() {
        binding.tvResultado.text = viewModel.getInput()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCero,
            R.id.btnUno,
            R.id.btnDos,
            R.id.btnTres,
            R.id.btnCuatro,
            R.id.btnCinco,
            R.id.btnSeis,
            R.id.btnSiete,
            R.id.btnOcho,
            R.id.btnNueve -> {
                val buttonText = (v as Button).text.toString()
                viewModel.agregarNumero(buttonText)
                actualizarTextView()
            }

            R.id.btnSuma,
            R.id.btnResta,
            R.id.btnMultiplicacion,
            R.id.btnDivision -> {
                val buttonText = (v as Button).text.toString()
                viewModel.agregarOperador(buttonText)
                actualizarTextView()
            }

            R.id.btnAC -> {
                viewModel.reiniciarCalculadora()
                actualizarTextView()
            }

            R.id.btnBorrar -> {
                viewModel.borrarUltimoCaracter()
                actualizarTextView()
            }

            R.id.btnIgual -> {
                val resultado = viewModel.calcularResultado(viewModel.getInput())
                binding.tvResultado.text = resultado
            }

            R.id.btnAbrirParentesis,
                R.id.btnCerrarParentesis -> {
                val buttonText = (v as Button).text.toString()
                viewModel.agregarCaracter(buttonText)
                actualizarTextView()
                }
        }
    }

}
