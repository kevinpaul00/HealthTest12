package com.example.healthtest1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPagerAdapter (fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
       return when (position){
           0 -> PeopleFragment()
           1 -> MedicationFragment()
           2 -> DependentFragment()
           3 -> AddressFragment()
           else -> {
               return PeopleFragment()
           }
       }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "People"
            1 -> "Medication"
            2 -> "Dependents"
            3 -> "Address"
            else -> {
                return " "
            }
        }

    }
}