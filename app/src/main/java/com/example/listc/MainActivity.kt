package com.example.listc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.listc.data.Countries
import com.example.listc.models.CountryModel

class MainActivity : AppCompatActivity() {
    private lateinit var name:TextView
    private lateinit var population:TextView
    private lateinit var description:TextView
    private lateinit var Next:ImageButton
    private lateinit var Prev:ImageButton
    private lateinit var Add:Button
    private lateinit var Change:Button
    private lateinit var Delete:Button
    private var AddLauncher:ActivityResultLauncher<Intent>? = null
    private var EditLauncher:ActivityResultLauncher<Intent>? = null
    val currentApiVersion = android.os.Build.VERSION.SDK_INT

    private val viewModel: CountryModel by lazy {
        val provider= ViewModelProvider(this)
        provider.get(CountryModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name=findViewById(R.id.tvName)
        population=findViewById(R.id.tvPopulation)
        description=findViewById(R.id.tvDescription)
        Add=findViewById(R.id.btnAdd)
        Change=findViewById(R.id.btnChange)
        Delete=findViewById(R.id.btnDelete)
        Next=findViewById(R.id.btnNext)
        Prev=findViewById(R.id.btnPrevious)
        AddLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode== RESULT_OK){
                    var nameNew=it.data?.getStringExtra("name")
                    var populationNew=it.data?.getStringExtra("population")
                    var descriptionNew=it.data?.getStringExtra("description")
                    var newCountry=Countries(nameNew!!,populationNew!!,descriptionNew!!)
                    viewModel.addCountry(newCountry)
            }
        }
        EditLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode== RESULT_OK){
                var nameNew=it.data?.getStringExtra("name")
                var populationNew=it.data?.getStringExtra("population")
                var descriptionNew=it.data?.getStringExtra("description")
                viewModel.ChangeCountry(viewModel.getCurrentIndex,nameNew!!,populationNew!!,descriptionNew!!)
                updateData()
            }
        }
        Add.setOnClickListener {
            AddLauncher?.launch(Intent(this@MainActivity, AddCountryActivity::class.java))
            updateData()
        }

        Change.setOnClickListener {
            var intent = Intent(this@MainActivity, EditCountryActivity::class.java)
            val value = viewModel.getCurrentIndex
            intent.putExtra("Index", value)
            EditLauncher?.launch(intent)
        }

        Delete.setOnClickListener {
            if(viewModel.CountryList.isNotEmpty()){
            viewModel.deleteCountry(viewModel.getCurrentIndex)
                viewModel.moveToPrev()
            updateData()
         }
        }

        Next.setOnClickListener {
            if(viewModel.CountryList.isNotEmpty()){
            viewModel.moveToNext()
            updateData()
        }}
        Prev.setOnClickListener {
            if(viewModel.CountryList.isNotEmpty()){
            viewModel.moveToPrev()
            updateData()}
        }



    }
    fun updateData(){
        if(viewModel.CountryList.isEmpty()){
            name.setText("")
            description.setText("")
            population.setText("")
        }
        else{
        name.setText(viewModel.currentCountryName)
        description.setText(viewModel.currentCountryDescription)
        population.setText(viewModel.currentCountryPopulation)
     }
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

}