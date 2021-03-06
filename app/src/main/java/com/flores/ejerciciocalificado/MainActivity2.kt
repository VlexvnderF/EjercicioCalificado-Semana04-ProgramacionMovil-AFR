package com.flores.ejerciciocalificado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.flores.ejerciciocalificado.databinding.ActivityMain2Binding
import com.flores.ejerciciocalificado.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main2.*
const val PARAMETER_EXTRA_NAME2 = "nombre"
const val PARAMETER_EXTRA_GMAIL2 = "correo"
const val PARAMETER_EXTRA_OFFICE2 = "oficina"
const val PARAMETER_EXTRA_PHONE2 = "telefono"

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        listenerGuardarDatos()
        val extras = this.intent.extras

        if (extras != null) {
            if (extras.get(PARAMETER_EXTRA_NAME2) != null) {
                ed_nom.setText(extras.getString(PARAMETER_EXTRA_NAME2))
            }

            if (extras.get(PARAMETER_EXTRA_GMAIL2) != null) {
                ed_correo.setText(extras.getString(PARAMETER_EXTRA_GMAIL2))
            }

            if (extras.get(PARAMETER_EXTRA_OFFICE2) != null) {
                ed_ofi.setText(extras.getString(PARAMETER_EXTRA_OFFICE2))
            }

            if (extras.get(PARAMETER_EXTRA_PHONE2) != null) {
                ed_tel.setText(extras.getString(PARAMETER_EXTRA_PHONE2))
            }
        }
    }
    private fun listenerGuardarDatos(){
        binding.GuardarDatos.setOnClickListener{
            val nombre = ed_nom.text.toString()
            val correo = ed_correo.text.toString()
            val oficina = ed_ofi.text.toString()
            val telefono = ed_tel.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("nombre", nombre)
            intent.putExtra("correo", correo)
            intent.putExtra("oficina", oficina)
            intent.putExtra("telefono", telefono)
            startActivity(intent)
        }
    }






}