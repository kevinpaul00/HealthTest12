package com.example.healthtest1

import android.app.AlertDialog
import android.content.ClipData
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_address.view.*
import kotlinx.android.synthetic.main.fragment_dependent.view.*
import kotlinx.android.synthetic.main.lo_patient.view.*

class patientAdapter(mCtx:Context,val patients:ArrayList<Patient>) :RecyclerView.Adapter<patientAdapter.ViewHolder>(){

    val mCtx=mCtx
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txtpid=itemView.txtpid
        val txtpname=itemView.txtpname
        val btnaadd=itemView.btnaadd
        val btnadep=itemView.btnadep
        val btndel=itemView.btndel
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): patientAdapter.ViewHolder {
       val v=LayoutInflater.from(p0.context).inflate(R.layout.lo_patient,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
           return patients.size
    }

    override fun onBindViewHolder(p0: patientAdapter.ViewHolder, p1: Int) {
               val patient:Patient=patients[p1]
               p0.txtpid.text=patient.pid.toString()
               p0.txtpname.text=patient.f_name.toString()

     p0.btndel.setOnClickListener {
       val f_name=patient.f_name
         var alertDialog=AlertDialog.Builder(mCtx)
             .setTitle("Warning")
             .setMessage("Are u sure to delete: $f_name?")
             .setPositiveButton("yes",DialogInterface.OnClickListener { dialog, which ->
                 if(Main3LActivity.dbHandler.deletePatient(patient.pid)){
                     patients.removeAt(p1)
                     notifyItemRemoved(p1)
                     notifyItemRangeChanged(p1,patients.size)
                     Toast.makeText(mCtx,"Deleted",Toast.LENGTH_SHORT).show()
                 }else
                     Toast.makeText(mCtx,"Error Deleting",Toast.LENGTH_SHORT).show()
             })
             .setNegativeButton("no", DialogInterface.OnClickListener { dialog, which ->  })
             .setIcon(R.drawable.ic_warning_black_24dp)
             .show()
     }
      p0.btnaadd.setOnClickListener {
            val inflater=LayoutInflater.from(mCtx)
            val view=inflater.inflate(R.layout.fragment_address,null)

            val number:TextView=view.findViewById(R.id.editText)
            val street:TextView=view.findViewById(R.id.editText2)
            val area:TextView=view.findViewById(R.id.editText3)
            val city:TextView=view.findViewById(R.id.editText4)

            number.text= patient.number
            street.text=patient.street
            area.text=patient.area
            city.text=patient.city
            val builder=AlertDialog.Builder(mCtx)
                .setTitle("Address")
                .setView(view)
                .setPositiveButton("Add",DialogInterface.OnClickListener { dialog, which ->
                    val isUpdate=Main3LActivity.dbHandler.updateAddress(
                        patient.pid,
                         view.editText.text.toString(),
                         view.editText2.text.toString(),
                         view.editText3.text.toString(),
                         view.editText4.text.toString())
                   if(isUpdate==true){
                       patients[p1].number=view.editText.text.toString()
                       patients[p1].street=view.editText2.text.toString()
                       patients[p1].area=view.editText3.text.toString()
                       patients[p1].city=view.editText4.text.toString()
                       notifyDataSetChanged()
                        Toast.makeText(mCtx,"added address",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(mCtx,"error adding address",Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->  })
                val alert=builder.create()
                 alert.show()
        }

        p0.btnadep.setOnClickListener {
            val inflater=LayoutInflater.from(mCtx)
            val view=inflater.inflate(R.layout.fragment_dependent,null)

            val dname:TextView=view.findViewById(R.id.addPDepName)
            val drelation:TextView=view.findViewById(R.id.editText6)
            val darea:TextView=view.findViewById(R.id.editText7)
            val dphno:TextView=view.findViewById(R.id.editText8)

            dname.text= patient.dname
            drelation.text=patient.drelation
            darea.text=patient.darea
            dphno.text=patient.dphno
            val builder=AlertDialog.Builder(mCtx)
                .setTitle("Add Dependent")
                .setView(view)
                .setPositiveButton("Add",DialogInterface.OnClickListener { dialog, which ->
                    val isUpdate=Main3LActivity.dbHandler.updateDep(
                        patient.pid,
                        view.addPDepName.text.toString(),
                        view.editText6.text.toString(),
                        view.editText7.text.toString(),
                        view.editText8.text.toString())
                    if(isUpdate==true){
                        patients[p1].dname=view.addPDepName.text.toString()
                        patients[p1].drelation=view.editText6.text.toString()
                        patients[p1].darea=view.editText7.text.toString()
                        patients[p1].dphno=view.editText8.text.toString()
                        notifyDataSetChanged()
                        Toast.makeText(mCtx,"added dependent",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(mCtx,"error adding dependent",Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->  })
            val alert=builder.create()
            alert.show()
        }

    }


}