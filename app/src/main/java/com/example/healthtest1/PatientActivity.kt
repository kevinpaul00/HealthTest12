package com.example.healthtest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_patient.*

class PatientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        floatingAddPatient.setOnClickListener{
            val intent = Intent(this, AddPatient2::class.java)
            startActivity(intent)
        }
    }

}
