package com.facu.bartender

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facu.bartender.data.DatosRecibo
import com.facu.bartender.databinding.ActivityReciboPropinaBinding
import com.facu.bartender.ui.AdapterRecibo
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.emailtxt
import kotlinx.android.synthetic.main.activity_registro.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Recibo_Propina : AppCompatActivity() {

    private lateinit var binding: ActivityReciboPropinaBinding
    var CuentaShower: ArrayList<String> = ArrayList()
    var NombresCuentaShower: ArrayList<String> = ArrayList()
    private lateinit var newArraylISTAfAV: ArrayList<DatosRecibo>
    private  lateinit var favRecyclerView: RecyclerView
    private lateinit var precio: String

    // instancia a la base de datos
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityReciboPropinaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()

        val bundlee:Bundle? = intent.extras
        val email:String? = bundlee?.getString("email")


        bundle.putString("message", "Integracion de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        CuentaShower = intent.getSerializableExtra("Recibo_Data") as ArrayList<String>
        NombresCuentaShower = intent.getSerializableExtra("Recibo_Name") as ArrayList<String>
        precio = intent.getSerializableExtra("Recibo_Precio") as String
        favRecyclerView = findViewById(R.id.RecycleRecibo)
        favRecyclerView.layoutManager= LinearLayoutManager(this)
        newArraylISTAfAV = arrayListOf<DatosRecibo>()
        obtenerdata()
        try{binding.precioTXTShowerRecibo.text = precio}catch (e: Exception){}
        binding.BTNAgregarPropina.setOnClickListener{agregarpropina()}

        binding.ConPropina.text = "0"
        binding.PrecioConPropina.text=  precio


        val pagar=findViewById<Button>(R.id.btnpay)
        pagar.setOnClickListener {
            addBill(email ?: "")
        }
    }

    //implementacion del boton home
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navmenu, menu)

        return super.onCreateOptionsMenu(menu)
    }
    //implementacion del boton home
    private fun gohome(s: String){

        val home = Intent(this,inicio::class.java).apply {
            putExtra("email",s)
        }
        startActivity(home)
        finish()
    }
    //implementacion del boton home
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")

        when (item.itemId){
            R.id.btnhome -> {
                gohome(email ?: "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun agregarpropina() {
        try {
            val porcentaje = binding.porcentajePropina.text.toString()
            val aa = porcentaje.toInt()
            val tt = (precio.toInt()*aa)/100
            val tyyy = tt+precio.toInt()
            binding.ConPropina.text = tt.toString()
            binding.PrecioConPropina.text=tyyy.toString()
        }catch (e: Exception){
            Toast.makeText(this, "No ah dado ningun valor a la propina", Toast.LENGTH_SHORT).show()}

    }

    private fun obtenerdata() {
        for(i in CuentaShower.indices) {

            val recibos = DatosRecibo(NombresCuentaShower[i],CuentaShower[i])
            newArraylISTAfAV.add(recibos)

        }
        val adap = AdapterRecibo(newArraylISTAfAV)
        favRecyclerView.adapter = adap
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date { return Calendar.getInstance().time
    }


    private fun addBill(s: String) {
        val sdf = getCurrentDateTime()
        val date= sdf.toString("yyyy-MM-dd HH:mm:ss")
        val pricebill= precio
        val pricetip = binding.ConPropina.text.toString()
        val totalprice = binding.PrecioConPropina.text.toString()

        val user = s

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you want to conclude this bill?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){dialog,_->

            db.collection("bills").document(date).set(
                hashMapOf("date" to date, "pricebill" to pricebill,"pricetip" to pricetip,"totalprice" to totalprice,"user" to user, "recibo" to newArraylISTAfAV))

            for(i in CuentaShower.indices) {



                db.collection("sell").add(
                    hashMapOf("NameRecibo" to NombresCuentaShower[i], "PrecioRecibo" to CuentaShower[i],"user" to user,"date" to date,)
                )

            }

            val orden = Intent(this,OrdenActivity::class.java).apply {
                putExtra("email",s)
            }
            startActivity(orden)
            finish()

            dialog.dismiss()
        }
        builder.setNegativeButton("No"){dialog,_->

            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()





    }


}