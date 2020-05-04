package com.example.healthtest1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class DBHandler(
    context:Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version:Int):
    SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "hms12.db"

        const val TABLE_NAME = "patient"
        const val COLUMN_PID = "pid"
        const val COLUMN_FNAME = "f_name"
        const   val COLUMN_MNAME = "m_name"
        const   val COLUMN_LNAME = "l_name"
        const   val COLUMN_GENDER = "gender"
        const  val COLUMN_PHNO = "ph_no"
        const  val COLUMN_DOB = "dob"
        const  val COLUMN_NUMBER = "number"
        const val COLUMN_STREET = "street"
        const  val COLUMN_AREA = "area"
        const  val COLUMN_CITY = "city"
        const  val COLUMN_DNAME = "dname"
        const  val COLUMN_DRELATION = "drelation"
        const  val COLUMN_DAREA= "dlocation"
        const  val COLUMN_DPHNO = "dphno"
    }

    override fun onCreate(db: SQLiteDatabase?) {
            val CREATE_TABLE="CREATE TABLE " +TABLE_NAME + " (" +
                    COLUMN_PID + " TEXT PRIMARY KEY," +
                    COLUMN_FNAME + " TEXT," +
                    COLUMN_MNAME + " TEXT," +
                    COLUMN_LNAME + " TEXT," +
                    COLUMN_GENDER + " TEXT," +
                    COLUMN_PHNO + " TEXT," +
                    COLUMN_DOB + " TEXT," +
                    COLUMN_NUMBER + " TEXT," +
                    COLUMN_STREET+ " TEXT," +
                    COLUMN_AREA+ " TEXT," +
                    COLUMN_CITY+ " TEXT," +
                    COLUMN_DNAME+ " TEXT," +
                    COLUMN_DRELATION+ " TEXT," +
                    COLUMN_DAREA+ " TEXT," +
                    COLUMN_DPHNO+ " TEXT)"

        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getPatients(mCtx:Context):ArrayList<Patient>{
        val qry="Select * from $TABLE_NAME"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val patients=ArrayList<Patient>()
        if (cursor.count==0)
            Toast.makeText(mCtx,"No records found",Toast.LENGTH_SHORT).show() else
        {
            cursor.moveToFirst()
            while (!cursor.isAfterLast()){
                val patient=Patient()
                patient.pid=cursor.getString(cursor.getColumnIndex(COLUMN_PID))
                patient.f_name=cursor.getString(cursor.getColumnIndex(COLUMN_FNAME))
                patient.m_name=cursor.getString(cursor.getColumnIndex(COLUMN_MNAME))
                patient.l_name=cursor.getString(cursor.getColumnIndex(COLUMN_LNAME))
                patient.gender=cursor.getString(cursor.getColumnIndex(COLUMN_GENDER))
                patient.ph_no=cursor.getString(cursor.getColumnIndex(COLUMN_PHNO))
                patient.dob=cursor.getString(cursor.getColumnIndex(COLUMN_DOB))
                patient.number=cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER))
                patient.street=cursor.getString(cursor.getColumnIndex(COLUMN_STREET))
                patient.area=cursor.getString(cursor.getColumnIndex(COLUMN_AREA))
                patient.city=cursor.getString(cursor.getColumnIndex(COLUMN_CITY))
                patients.add(patient)
                cursor.moveToNext()
            }
            Toast.makeText(mCtx,"${cursor.count.toString()}Records found",Toast.LENGTH_SHORT).show()
        }

        cursor.close()
        db.close()
        return patients
    }
    fun AddPatient(mCtx: Context,patient: Patient){
        val values=ContentValues()
        values.put(COLUMN_PID,patient.pid)
        values.put(COLUMN_FNAME,patient.f_name)
        values.put(COLUMN_MNAME,patient.m_name)
        values.put(COLUMN_LNAME,patient.l_name)
        values.put(COLUMN_GENDER,patient.gender)
        values.put(COLUMN_PHNO,patient.ph_no)
        values.put(COLUMN_DOB,patient.dob)
        values.put(COLUMN_NUMBER,patient.number)
        values.put(COLUMN_STREET,patient.street)
        values.put(COLUMN_AREA,patient.area)
        values.put(COLUMN_CITY,patient.city)
        values.put(COLUMN_DNAME,patient.dname)
        values.put(COLUMN_DRELATION,patient.drelation)
        values.put(COLUMN_DAREA,patient.darea)
        values.put(COLUMN_DPHNO,patient.dphno)
        val db=this.writableDatabase
        try{
           db.insert(TABLE_NAME,null,values)
            Toast.makeText(mCtx,"added",Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(mCtx,e.message,Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun deletePatient(pid : String):Boolean{
        val qry="Delete From $TABLE_NAME where $COLUMN_PID=$pid"
        val db=this.writableDatabase
        var result:Boolean=false
        try{
           // val cursor=db.delete(TABLE_NAME,"$COLUMN_PID=?", arrayOf(pid.toString()))
            val cursor=db.execSQL(qry)
            result=true
        }catch (e:Exception){
            Log.e(ContentValues.TAG,"Error Deleting")
        }
        db.close()
        return result
    }
  fun updateAddress( pid:String,number:String,street:String,area:String,city:String):Boolean{
      val db=this.writableDatabase
      val cv=ContentValues()
    var result:Boolean=false
     cv.put(COLUMN_NUMBER,number)
       cv.put(COLUMN_STREET,street)
        cv.put(COLUMN_AREA,area)
        cv.put(COLUMN_CITY,city)
        try {
      db.update(TABLE_NAME,cv,"$COLUMN_PID=?", arrayOf(pid))
       result=true
        }catch (e:Exception){
            Log.e(ContentValues.TAG,"Error Adding")
            result=false
        }
        return result
  }

    fun updateDep( pid:String,dname:String,drelation:String,darea:String,dphno:String):Boolean{
        val db=this.writableDatabase
        val cv=ContentValues()
        var result:Boolean=false
        cv.put(COLUMN_DNAME,dname)
        cv.put(COLUMN_DRELATION,drelation)
        cv.put(COLUMN_DAREA,darea)
        cv.put(COLUMN_DPHNO,dphno)
        try {
            db.update(TABLE_NAME,cv,"$COLUMN_PID=?", arrayOf(pid))
            result=true
        }catch (e:Exception){
            Log.e(ContentValues.TAG,"Error Adding")
            result=false
        }
        return result
    }

}
