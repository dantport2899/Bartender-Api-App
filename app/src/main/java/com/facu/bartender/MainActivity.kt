package com.facu.bartender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.facu.bartender.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //autenticacion con firebase
        val analytics:FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        val registro=findViewById<Button>(R.id.logout)
        registro.setOnClickListener {
            registro()
        }

        val login=findViewById<Button>(R.id.edit)
        login.setOnClickListener {
            iniciarsesion()
        }

        val forgot=findViewById<Button>(R.id.forgotPassword)
        forgot.setOnClickListener {
            val forgot = Intent(this,forgotpass::class.java)
            startActivity(forgot)
        }

    }

    fun registro() {
        val registro = Intent(this,registro::class.java).apply {}
        startActivity(registro)
    }

    fun iniciarsesion() {

        if (emailtxt.text.isNotEmpty() && pass.text.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                emailtxt.text.toString(),
                pass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {

                    showHome(it.result?.user?.email?:"")

                } else {
                    showalert()
                }
            }
        }

    }

    private fun showalert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al iniciar Sesion")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String)
    {
        val homeIntent = Intent(this,inicio::class.java).apply {
            putExtra("email",email)

        }
        startActivity(homeIntent)
    }
}