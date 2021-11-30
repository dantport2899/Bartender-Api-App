package com.facu.bartender

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "menu.db"
        private const val TBL_DRINKS = "tbl_drinks"
        private const val ID = "id"
        private const val NAME = "name"
        private const val PRICE = "price"


    }


    override fun onCreate(db: SQLiteDatabase?) {
       val createTblDrinks = ("CREATE TABLE " + TBL_DRINKS + "(" + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + PRICE + " TEXT"+")")
        db?.execSQL(createTblDrinks)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_DRINKS")

        onCreate(db)
    }

    fun insertDrink(std: DrinkModel): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(PRICE, std.price)

        val success = db.insert(TBL_DRINKS, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllDrinks(): ArrayList<DrinkModel>{
        val stdList: ArrayList<DrinkModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_DRINKS"
        val db = this. readableDatabase

        val cursor: Cursor?
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch(e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id:Int
        var name: String
        var price: String

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                price = cursor.getString(cursor.getColumnIndex("price"))
                val std = DrinkModel(id = id, name = name, price = price)
                stdList.add(std)
            }while (cursor.moveToNext())
        }
        return stdList
    }

    fun updateDrink(std: DrinkModel): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(PRICE, std.price)

        val success = db.update(TBL_DRINKS, contentValues, "id=" + std.id, null)
        db.close()
        return success
    }

    fun deleteDrinkByID(id: Int): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID,id)
        val success = db.delete(TBL_DRINKS, "id="+id, null)
        db.close()
        return success
    }


    //23:41--29:53 si quiero agregar la opcion de actulizar la db debo agregar  un boton en el fragment_descripcion_By_Ingrediente
    // oh agregar dos textbox en la pantalla de activity_orden una para id y otra para precio

}