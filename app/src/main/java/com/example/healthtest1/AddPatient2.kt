package com.example.healthtest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_patient.*
import kotlinx.android.synthetic.main.fragment_people.*

class AddPatient2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_people)

        button2.setOnClickListener{
            val intent = Intent(this, AddressActivity2::class.java)
            startActivity(intent)
        }
    }
}
