package com.example.healthtest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main3_l.*
import kotlinx.android.synthetic.main.lo_patient.*

class Main3LActivity : AppCompatActivity() {

    companion object{
        lateinit var dbHandler: DBHandler
    }
    var PatientsList= ArrayList<Patient>()
    lateinit var adapter:RecyclerView.Adapter<*>
    lateinit var rv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3_l)

        dbHandler= DBHandler(this,null,null,1)
        viewCustomers()

        fab.setOnClickListener{
            val intent = Intent(this, AddPatient2::class.java)
            startActivity(intent)
        }
        editsearch.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var filteredpatient=ArrayList<Patient>()
                if(!editsearch.text.isEmpty()){
                    for(i in 0..PatientsList.size-1){
                    if(PatientsList.get(i).f_name!!.toLowerCase().contains(s.toString().toLowerCase()))
                        filteredpatient.add(PatientsList[i])
                    }
                    adapter=patientAdapter(this@Main3LActivity,filteredpatient)
                    rv.adapter=adapter
                }else{
                    adapter=patientAdapter(this@Main3LActivity,PatientsList)
                    rv.adapter=adapter
                }
            }
        })
    }

    private fun viewCustomers()
    {
        PatientsList= dbHandler.getPatients(this)
        adapter=patientAdapter(this,PatientsList)
        rv=findViewById(R.id.rv)
        rv.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false) as RecyclerView.LayoutManager
        rv.adapter=adapter
    }
    override fun onResume(){
        viewCustomers()
        super.onResume()
    }

}
