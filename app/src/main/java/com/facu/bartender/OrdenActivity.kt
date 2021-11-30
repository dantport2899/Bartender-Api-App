package com.facu.bartender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facu.bartender.ui.AdapterMenu
import kotlinx.android.synthetic.main.activity_orden.*

class OrdenActivity : AppCompatActivity() {

    private lateinit var editPrice: EditText
    private lateinit var editName: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnPasarApagar: Button
    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var showPrecio: TextView
    private var adapter: AdapterMenu?=null
    private var std: DrinkModel? = null
    var CuentaActual: ArrayList<String> = ArrayList()
    var NombresCuentaActual: ArrayList<String> = ArrayList()
    private var hola = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orden)
        initView()
        sqLiteHelper= SQLiteHelper(this)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")

        MostrarBebidas.setOnClickListener{getDrinks()}
        btnUpdate.setOnClickListener{updateDrink()}
        btnPasarApagar.setOnClickListener{pasarApagar(email ?: "")}


        initRecyclerView()
        adapter?.setOnclickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            editName.setText(it.name)
            editPrice.setText(it.price)
            std = it
        }


        adapter?.setOnClickDeleteItem {
            deleteDrink(it.id)
        }

        adapter?.setOnClickAgregarItem {
            ObtenerPrecio(it.price,it.name)
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

    private fun pasarApagar(s: String) {
        val intent = Intent(this, Recibo_Propina::class.java).apply {
            putExtra("email",s)
        }
        intent.putExtra("Recibo_Data", CuentaActual)
        intent.putExtra("Recibo_Name", NombresCuentaActual)
        intent.putExtra("Recibo_Precio",hola.toString())

        startActivity(intent)
    }

    private fun ObtenerPrecio(PrecioCoctel: String, CoctelName: String) {
        hola += PrecioCoctel.toInt()
        CuentaActual.add(PrecioCoctel)
        NombresCuentaActual.add(CoctelName)

        //Log.e("ALEX","$CuentaActual")
        showPrecio.text = "$hola"
    }

    private fun deleteDrink(id:Int){
        if(id == null) return

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you want to delete the coctel from the menu?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){dialog,_->
            sqLiteHelper.deleteDrinkByID(id)
            getDrinks()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){dialog,_->

            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun getDrinks() {
        val stdlist = sqLiteHelper.getAllDrinks()
        Log.e("ALEX","$stdlist")
        adapter?.addItems(stdlist)

    }

    private fun updateDrink(){
        val precio = editPrice.text.toString()
        val name = editName.text.toString()
        if(precio == std?.price){
            Toast.makeText(this, "No se cambio el registro...", Toast.LENGTH_SHORT).show()
            return
        }

        if (std == null) return
        val std = DrinkModel(id = std!!.id, name = name,price = precio)
        val status =sqLiteHelper.updateDrink(std)
        if (status >-1){
            clearEditText()
            getDrinks()
        }else{
            Toast.makeText(this, "La actulizacion del coctel fallo", Toast.LENGTH_SHORT).show()
        }

    }

    private fun clearEditText(){
        editName.setText("")
        editPrice.setText("")
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterMenu()
        recyclerView.adapter=adapter

    }

    private fun initView(){
        recyclerView = findViewById(R.id.RecyclerMenu)
        editPrice=findViewById(R.id.editPrice)
        editName=findViewById(R.id.editNameET)
        btnUpdate=findViewById(R.id.ActulizarBebida)
        btnPasarApagar=findViewById(R.id.PasarApagar)
        showPrecio=findViewById(R.id.precioTXTShower)
    }
}