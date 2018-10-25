package com.enpassio.tictactoe_mvvm

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.view.GameActivityAbhishek
import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_greta.view.GameActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.abhishek_activity_main)
        val buttonGreta: Button = findViewById(R.id.button_greta_example)
        buttonGreta.setOnClickListener { startActivity(Intent(this, GameActivity::class.java)) }
        val buttonAbhishek: Button = findViewById(R.id.button_abhishek_example)
        buttonAbhishek.setOnClickListener { startActivity(Intent(this, GameActivityAbhishek::class.java)) }
    }
}
