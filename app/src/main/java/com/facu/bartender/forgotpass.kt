package com.facu.bartender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class forgotpass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpass)


        val login=findViewById<Button>(R.id.edit)
        login.setOnClickListener {
            recuperar()
        }

    }

    fun recuperar()
    {
        val email = emailtxt.text.toString()

        if(emailtxt.text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailtxt.text.toString()).matches())
        {
            showalert()
            return
        }

        sendEmail(email)
    }

    private fun showalert()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error with restore password")
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun sendEmail(email: String)
    {
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {

               Toast.makeText(this,"Email Send", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
            }
        }

    }
}