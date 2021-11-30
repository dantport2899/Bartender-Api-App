package com.facu.bartender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facu.bartender.databinding.ActivityInicioBinding
import com.google.firebase.firestore.FirebaseFirestore



class inicio : AppCompatActivity() {


    //instancia a la base de datos
    private val db = FirebaseFirestore.getInstance()


    private lateinit var binding: ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")


        setup(email ?: "")

        binding.ByNameBTN.setOnClickListener{bebidaPorNombre(email ?: "")}
        binding.ByIngredientBTN.setOnClickListener{bebidaPorIngrediente(email ?: "")}
        binding.RandomCocktailBTN.setOnClickListener{bebidaRandom(email ?: "")}
        binding.btnhistorial.setOnClickListener{historial(email ?: "")}
        binding.CuentaBTN.setOnClickListener{Cuenta(email ?: "")}
        binding.profile.setOnClickListener{profile(email ?: "")}
    }

    fun Cuenta(s: String) {
        val intent = Intent(this, OrdenActivity::class.java).apply {
            putExtra("email",s)
        }
        startActivity(intent)
    }

    fun bebidaRandom(s: String){
        val intent = Intent(this, ByRandomActivity::class.java).apply {
            putExtra("email",s)
        }
        startActivity(intent)
    }

    fun bebidaPorNombre(s: String)  {
        val intent = Intent(this, ByNameActivity::class.java).apply {
            putExtra("email",s)
        }
        startActivity(intent)
    }
    fun bebidaPorIngrediente(s: String)  {
        val intent = Intent(this, ActivityByIngredient::class.java).apply {
            putExtra("email",s)
        }
        startActivity(intent)
    }

    fun historial(s: String)  {
        val intent = Intent(this, Bills::class.java).apply {
            putExtra("email",s)
        }
        startActivity(intent)
    }

    fun profile(s: String)  {
        val profile = Intent(this, usuariodatos::class.java).apply {
            putExtra("email",s)
        }

        startActivity(profile)
    }


    fun setup(s: String)
    {
        db.collection("users").document(s).get().addOnSuccessListener{
            title = "Welcome " + it.get("name") as String?
        }
    }
}