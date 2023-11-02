package com.example.listc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.listc.models.CountryModel

class EditCountryActivity : AppCompatActivity() {
    private lateinit var AcceptEdt:Button
    private lateinit var CancelEdt:Button
    private lateinit var nameEdt:EditText
    private lateinit var populationEdt:EditText
    private lateinit var descriptionEdt:EditText

    private val viewModel: CountryModel by lazy {
        val provider= ViewModelProvider(this)
        provider.get(CountryModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_country)
        nameEdt=findViewById(R.id.etNameEdit)
        populationEdt=findViewById(R.id.etPopulationEdit)
        descriptionEdt=findViewById(R.id.etDescriptionEdit)
        AcceptEdt=findViewById(R.id.btnAcceptEdit)
        CancelEdt=findViewById(R.id.btnCancelEdit)
        val index = intent.getIntExtra("Index",-1)
        if(index==-1){
            nameEdt.setText("Ошибка индекса")
            populationEdt.setText("Ошибка индекса")
            descriptionEdt.setText("Ошибка индекса")
        }
        else{
            nameEdt.setText(viewModel.CountryList[index].name)
            populationEdt.setText(viewModel.CountryList[index].population)
            descriptionEdt.setText(viewModel.CountryList[index].description)
        }
        AcceptEdt.setOnClickListener {
            val editIntent = Intent().apply {
            putExtra("name", nameEdt.text.toString())
            putExtra("population", populationEdt.text.toString())
            putExtra("description", descriptionEdt.text.toString())
         }
            setResult(RESULT_OK, editIntent)
            finish()
        }

        CancelEdt.setOnClickListener { finish() }

    }
}