package sbproject.design.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import sbproject.design.BaseActivity
import sbproject.design.R
import sbproject.design.backdrop.BackDropActivity
import sbproject.design.bottomappbar.BottomAppBarActivity
import sbproject.design.textinputlayout.TextInputLayoutActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_backdropactivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, BackDropActivity::class.java))
        }
        btn_bottomappbaractivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, BottomAppBarActivity::class.java))
        }
        btn_textinputlayoutactivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, TextInputLayoutActivity::class.java))
        }
    }
}
