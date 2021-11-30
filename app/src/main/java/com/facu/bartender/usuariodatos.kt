package com.facu.bartender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_usuariodatos.emailtxt
import kotlinx.android.synthetic.main.activity_usuariodatos.nametxt

class usuariodatos : AppCompatActivity() {

    //instancia a la base de datos
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuariodatos)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")

        setup(email ?: "")

        val close=findViewById<Button>(R.id.logout)
        close.setOnClickListener {
            close()
        }

        val restore=findViewById<Button>(R.id.restorepass)
        restore.setOnClickListener {
            val restore = Intent(this,forgotpass::class.java)
            startActivity(restore)
        }

        val edit=findViewById<Button>(R.id.edit)
        edit.setOnClickListener {
            editprofile(email ?: "")
        }
    }


    fun close()
    {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun setup(email: String)
    {
        db.collection("users").document(email).get().addOnSuccessListener{
            title = "Perfil de " + it.get("name") as String?
            emailtxt.setText(email as String?)
            nametxt.setText(it.get("name") as String?)

        }
    }

    fun editprofile(emailactual: String)
    {
        val email = emailtxt.text.toString()
        val user = FirebaseAuth.getInstance().getCurrentUser()

        if(emailtxt.text.isEmpty() || nametxt.text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailtxt.text.toString()).matches())
        {
            showalert()
            return
        }



        user!!.updateEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                   Toast.makeText(this,"User email address updated.", Toast.LENGTH_SHORT).show()
                    db.collection("users").document(emailactual).delete()

                    db.collection("users").document(emailtxt.text.toString()).set(
                        hashMapOf("name" to nametxt.text.toString()))

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

    }

    private fun showalert()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error with the update")
        builder.setPositiveButton("Ok", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}