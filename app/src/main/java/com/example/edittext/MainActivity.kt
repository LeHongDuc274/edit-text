package com.example.edittext

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edt = findViewById<EditText>(R.id.edt)
        val tv = findViewById<TextView>(R.id.tv)
        edt.filters = arrayOf()
        edt.addTextChangedListener(object : TextWatcher {
            var beforLent = 0
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    beforLent = it.length
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun afterTextChanged(p0: Editable?) {
                var afterLent = 0
                p0?.let { e ->
                    afterLent = e.length
                    if (afterLent == 21 && beforLent < afterLent) {
                        edt.filters = arrayOf(InputFilter.LengthFilter(20))
                        edt.setText(p0.delete(20, p0.length))
                        edt.setSelection(edt.length())
                        tv.setTextColor(Color.BLUE)
                        tv.text = " ${edt.text.length} / 20 kí tự"

                    } else if (afterLent > 20) {
                        edt.filters = arrayOf(InputFilter.LengthFilter(afterLent))
                        tv.setTextColor(Color.RED)
                        tv.text = "Không nhập quá 20 kí tự ${edt.text.length} / 20"

                    } else {
                        edt.filters = arrayOf()
                        tv.setTextColor(Color.BLUE)
                        tv.text = " ${edt.text.length} / 20 kí tự"
                    }
                }
            }
        })
    }
}
