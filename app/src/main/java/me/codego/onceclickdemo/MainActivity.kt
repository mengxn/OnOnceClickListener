package me.codego.onceclickdemo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.codego.view.OnOnceClickListener
import me.codego.view.setOnOnceClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.first_btn).setOnOnceClickListener(500) {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.last_btn).setOnOnceClickListener(OnOnceClickListener.Strategy.LAST, 500) {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.throttle_btn).setOnOnceClickListener(OnOnceClickListener.Strategy.THROTTLE, 500) {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        }
    }
}