package co.edu.uan.android.storage1

import android.content.Intent
import android.media.MediaParser
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { v -> goToActivity(v) }
        readFile()

    }

    fun goToActivity(v: View) {
        val intent = Intent(this, SecondActivity::class.java)
        val REQUEST_CODE = 100
        intent.putExtra("MESSAGE", "Hello world")
        intent.putExtra("NAME", firstname.text.toString())
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.v("eltag","Result: $requestCode, $resultCode, ${data?.getStringExtra("RESULT")}")
        readFile()
    }

    fun readFile() {
        // read the file
        val input = Scanner(openFileInput("names.txt"))
        val namesList = arrayListOf<String>()
        while(input.hasNextLine()) {
            val l = input.nextLine()
            namesList.add(l)
        }
        input.close()
        // add the content in the textview
        namesView.text = namesList.joinToString(", ")
        val s = "hola"
    }
}