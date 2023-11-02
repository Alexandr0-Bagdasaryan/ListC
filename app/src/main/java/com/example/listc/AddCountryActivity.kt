package com.example.listc

import android.app.Activity
import android.content.ClipDescription
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.listc.data.Countries
import com.example.listc.models.CountryModel


class AddCountryActivity : AppCompatActivity() {
    private lateinit var Accept:Button
    private lateinit var Cancel:Button
    private lateinit var name:EditText
    private lateinit var population:EditText
    private lateinit var description:EditText
    private val shortToast = { message: String ->
        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_SHORT
        )
    }

    private val viewModel: CountryModel by lazy {
        val provider= ViewModelProvider(this)
        provider.get(CountryModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_country)
        Accept=findViewById(R.id.btnAcceptAdd)
        Cancel=findViewById(R.id.btnCancelAdd)
        name=findViewById(R.id.tvNameAdd)
        population=findViewById(R.id.tvPopulationAdd)
        description=findViewById(R.id.tvDescriptionAdd)
        Accept.setOnClickListener {
            val country = Countries(name.text.toString(),population.text.toString(),description.text.toString())
            val editIntent = Intent().apply {
                putExtra("name", name.text.toString())
                putExtra("population", population.text.toString())
                putExtra("description", description.text.toString())
            }
            setResult(RESULT_OK, editIntent)
            finish()
        }
        
        Cancel.setOnClickListener { finish() }




    }

}
