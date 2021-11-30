package com.facu.bartender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.emailtxt
import kotlinx.android.synthetic.main.activity_main.pass
import kotlinx.android.synthetic.main.activity_registro.*

class registro : AppCompatActivity() {

    // instancia a la base d e datos
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //autenticacion con firebase
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integration to Firebase complete")
        analytics.logEvent("InitScreen", bundle)

        val registro=findViewById<Button>(R.id.logout)
        registro.setOnClickListener {
            registro()
        }

        val back=findViewById<Button>(R.id.edit)
        back.setOnClickListener {
           back()
        }
    }

    fun registro()
    {
        if (emailtxt.text.isNotEmpty() && pass.text.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                emailtxt.text.toString(),
                pass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {

                    guardar()
                    showlogin()

                } else {
                    showalert()
                }
            }
        }
    }

    fun back()
    {
        val login = Intent(this,MainActivity::class.java).apply {}
        startActivity(login)
    }

    private fun showalert()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error in user register")
        builder.setPositiveButton("Ok", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showlogin()
    {
        val login = Intent(this,MainActivity::class.java).apply {}
        startActivity(login)
    }

    fun guardar()
    {
        db.collection("users").document(emailtxt.text.toString()).set(
            hashMapOf("name" to nametxt.text.toString()))
    }

}