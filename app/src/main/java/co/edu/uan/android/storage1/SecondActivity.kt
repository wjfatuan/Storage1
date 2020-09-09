package co.edu.uan.android.storage1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_second.*
import java.io.PrintStream
import java.io.Serializable


class SecondActivity : AppCompatActivity() {

    val data = arrayListOf("Bogota","Cali","Medellin")
    var adapter: ArrayAdapter<String> ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        myList.adapter = adapter

        val message = intent.getStringExtra("MESSAGE")
        val name = intent.getStringExtra("NAME")
        textView.text = "$message $name !!"
        saveName(name)

        btnGoBack.setOnClickListener { _ ->
            val result = Intent()
            result.putExtra("RESULT","Bye!")
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        btnMail.setOnClickListener { _ ->
            val mapsIntent = Uri.parse("geo:48.8588376,2.2768488").let { mailAddress ->
                Intent(Intent.ACTION_VIEW, mailAddress)
            }
//            val mailIntent = Intent(Intent.ACTION_SENDTO)
//            mailIntent.putExtra(Intent.EXTRA_EMAIL, "$name@uan.edu.co")
            startActivity(mapsIntent)
        }
        Log.v("LOGSTATE", "onCreate")
    }

    fun saveName(name: String?) {
        // write the name in the file
        if(name!=null) {
            val out = PrintStream(openFileOutput("names.txt", Activity.MODE_APPEND))
            out.println(name)
            out.close()
        }
        if(name!=null) {
            data.add(name)
            adapter?.notifyDataSetChanged()
        }
    }


    override fun onStart() {
        super.onStart()
        Log.v("LOGSTATE", "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.v("LOGSTATE", "onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.v("LOGSTATE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("LOGSTATE", "onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}