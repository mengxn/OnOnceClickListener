package me.codego.onceclickdemo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.codego.view.OnOnceClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.click_btn).setOnClickListener(OnOnceClickListener {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        })
    }
}