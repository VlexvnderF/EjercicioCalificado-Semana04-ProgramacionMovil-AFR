package com.flores.ejerciciocalificado

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.flores.ejerciciocalificado.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

const val PARAMETER_EXTRA_NAME = "nombre"
const val PARAMETER_EXTRA_GMAIL = "correo"
const val PARAMETER_EXTRA_OFFICE = "oficina"
const val PARAMETER_EXTRA_PHONE = "telefono"
const val ACTIVITY_A_REQUEST=991
const val ACTIVITY_B_REQUEST=992

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        listenerCallButtons()

        val extras = this.intent.extras

        if (extras != null) {
            if (extras.get(PARAMETER_EXTRA_NAME) != null) {
                binding.textView2.text = extras.getString(PARAMETER_EXTRA_NAME)
            }

            if (extras.get(PARAMETER_EXTRA_GMAIL) != null) {
                binding.textView4.text = extras.getString(PARAMETER_EXTRA_GMAIL)
            }

            if (extras.get(PARAMETER_EXTRA_OFFICE) != null) {
                binding.textView6.text = extras.getString(PARAMETER_EXTRA_OFFICE)
            }
            if (extras.get(PARAMETER_EXTRA_PHONE) != null) {
                binding.textView8.text = extras.getString(PARAMETER_EXTRA_PHONE)
            }
        }
    }
    fun sendExplicit(view: android.view.View) {
        val nombre = binding.textView2.text.toString()
        val correo = binding.textView4.text.toString()
        val oficina = binding.textView6.text.toString()
        val telf = binding.textView8.text.toString()

        validateInputFields(nombre, correo, oficina, telf)
        goDetailActivity(nombre, correo, oficina, telf)
    }


    private fun goDetailActivity(nombre: String, correo: String, oficina: String,telefono: String) {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("correo", correo)
        intent.putExtra("oficina", oficina)
        intent.putExtra("telefono", telefono)
        startActivity(intent)
    }


    private fun validateInputFields(nombre: String, correo: String, oficina: String,telefono: String) {
        if (nombre.isBlank() && correo.isBlank() && oficina.isBlank() && telefono.isBlank()) return
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "requestCode:$requestCode")
        Log.d(TAG, "resultCode:$resultCode")
        Log.d(TAG, "data:" + android.R.attr.data)

        when (requestCode) {
            ACTIVITY_A_REQUEST -> Log.d(TAG, "Regresamos del Activity A")
            ACTIVITY_B_REQUEST -> {
                Log.d(TAG, "Regresando a la vista principal")
                if (resultCode === RESULT_OK) {
                    val valor: String = data?.extras?.getString("valor").toString()
                    Toast.makeText(this, "valor: $valor", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "valor: $valor")
                }
                val extras = data?.extras
                if (extras != null) {
                    if (extras.get(PARAMETER_EXTRA_NAME) != null) {
                        textView2.setText(extras.getString(PARAMETER_EXTRA_NAME))
                    }

                    if (extras.get(PARAMETER_EXTRA_GMAIL) != null) {
                        textView4.setText(extras.getString(PARAMETER_EXTRA_GMAIL))
                    }

                    if (extras.get(PARAMETER_EXTRA_OFFICE) != null) {
                        textView6.setText(extras.getString(PARAMETER_EXTRA_OFFICE))
                    }

                    if (extras.get(PARAMETER_EXTRA_PHONE) != null) {
                        textView8.setText(extras.getString(PARAMETER_EXTRA_PHONE))
                    }
                }
            }
        }
    }
    private fun listenerCallButtons(){
        binding.Llamar.setOnClickListener{
            val telefono = textView8.text.toString()
            val call = Uri.parse("tel:$telefono")
            val intent = Intent(Intent.ACTION_DIAL, call)
            startActivity(intent)
        }

        binding.AbrirWhatsApp.setOnClickListener{
            try {
                val text = "Esto es una prank"
                val telefono = textView8.text.toString()
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("http://api.whatsapp.com/send?phone=51$telefono&text=$text")
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.enviarMensaje.setOnClickListener{
            val nombre = textView2.text.toString()
            val telefono = textView8.text.toString()
            val uri = Uri.parse("smsto: $telefono")
            val it = Intent(Intent.ACTION_SENDTO,uri)
            it.putExtra("sms_body", "Hola $nombre que bonita es la distinta cuando sé que volveras")
            startActivity(it)

        }

    }




}