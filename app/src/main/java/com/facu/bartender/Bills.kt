package com.facu.bartender

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

// los datos de cada factura se buscan por correo y por fecha y hora exacta
class Bills : AppCompatActivity() {
    // instancia a la base d e datos
    private lateinit var  db : FirebaseFirestore
    private lateinit var  recyclerView: RecyclerView
    private lateinit var  Billsadapter : Billsadapter
    private lateinit var  billarraylist : ArrayList<BillModel>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills)

        db =  FirebaseFirestore.getInstance()

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")

        recyclerView = findViewById(R.id.recicler)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        billarraylist = arrayListOf<BillModel>()

        Billsadapter = Billsadapter(billarraylist)

        recyclerView.adapter = Billsadapter



        getbilldata(email ?: "")

        Billsadapter?.setOnClickDeleteItem {
            deleteBill(it.date,email ?: "")
        }

        Billsadapter?.setOnClickViewItem {
            viewBill(it.date,it.pricebill,it.pricetip,it.totalprice,email ?: "")
        }



    }

    private fun deleteBill(date: String?,s: String){
        if(date == null) return

        val builder = AlertDialog.Builder(this)
            builder.setMessage("Do you want to delete this bill?")
            builder.setCancelable(true)
            builder.setPositiveButton("Yes"){dialog,_->

                db.collection("bills").document(date)
                .delete()
                .addOnSuccessListener { Log.d(TAG, "Bill successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting Bill", e) }

            db.collection("sell").document(date)
                .delete()
                .addOnSuccessListener { Log.d(TAG, "Bill successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting Bill", e) }

            billarraylist.clear()
            getbilldata(s)
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){dialog,_->

            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun viewBill(date: String?,pricebill: String?, pricetip: String?, total: String?,s: String){
        if(date == null) return

        val intent = Intent(this, reciboview::class.java).apply {
            putExtra("date",date)
            putExtra("email",s)
            putExtra("total",total)
            putExtra("pricebill",pricebill)
            putExtra("pricetip",pricetip)

        }
        startActivity(intent)

    }

    private fun getbilldata(s: String){

        db.collection("bills").whereEqualTo("user", s).
        addSnapshotListener(object : EventListener<QuerySnapshot>{
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
                        billarraylist.add(dc.document.toObject(BillModel::class.java))
                    }
                }

                Billsadapter.notifyDataSetChanged()
            }

        })


    }


}