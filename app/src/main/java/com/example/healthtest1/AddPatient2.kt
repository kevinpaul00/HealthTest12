package com.example.healthtest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_people.*

class AddPatient2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var dbHandler : DBHandler



        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_people)


        dbHandler = DBHandler(this,null,null,1)

        button2.setOnClickListener{
               val patient=Patient()
            patient. pid= addPId.text.toString()
            patient.f_name= addPFName.text.toString()
            patient. m_name= addPMidName.text.toString()
            patient. l_name= addPLName.text.toString()
            patient. gender= addPGender.text.toString()
            patient. ph_no= addPPhone.text.toString()
            patient.dob= addPDate.text.toString()
            patient.number= ""
                patient.street=  ""
                patient.area=  ""
                patient.street=  ""
            patient.city=""
            patient.dname=""
            patient.drelation=""
            patient.darea=""
            patient.dphno=""
            dbHandler.AddPatient(this,patient)

            this.addPId.setText("")
            this.addPFName.setText("")
            this.addPMidName.setText("")
            this.addPLName.setText("")
            this.addPGender.setText("")
            this.addPPhone.setText("")
            this.addPDate.setText("")
            val intent = Intent(this, Main3LActivity::class.java)
            startActivity(intent)
        }
    }



}




