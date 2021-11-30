package com.facu.bartender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facu.bartender.data.DatosRecibo
import com.facu.bartender.ui.AdapterRecibo
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_reciboview.*

class reciboview : AppCompatActivity() {

    private lateinit var  db : FirebaseFirestore
    private lateinit var  recyclerView2: RecyclerView
    private lateinit var  adapter : AdapterRecibo
    private lateinit var  reciboarraylist : ArrayList<DatosRecibo>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciboview)

        val bundle:Bundle? = intent.extras
        val date:String? = bundle?.getString("date")
        val email:String? = bundle?.getString("email")
        val total:String? = bundle?.getString("total")
        val tip:String? = bundle?.getString("pricetip")
        val bill:String? = bundle?.getString("pricebill")

        db =  FirebaseFirestore.getInstance()

        recyclerView2 = findViewById(R.id.recicler2)
        recyclerView2.layoutManager= LinearLayoutManager(this)
        recyclerView2.setHasFixedSize(true)


        reciboarraylist = arrayListOf<DatosRecibo>()

        adapter = AdapterRecibo(reciboarraylist)

        recyclerView2.adapter = adapter


        textView3.setText(total)
        textView4.setText(date)
        textView6.setText(tip)
        textView8.setText(bill)

        getbilldata(date ?: "",email ?: "")
    }

    private fun getbilldata(date: String?,s: String?){
        if (date != null) {
            db.collection("sell").whereEqualTo("user", s).whereEqualTo("date", date).
            addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if(error != null){
                        Log.e("Firestore Error",error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!){
                        if(dc.type == DocumentChange.Type.ADDED){
                            reciboarraylist.add(dc.document.toObject(DatosRecibo::class.java))

                        }
                    }

                    adapter.notifyDataSetChanged()
                }

            })
        }
    }
}