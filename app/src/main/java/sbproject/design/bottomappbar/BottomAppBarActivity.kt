package sbproject.design.bottomappbar

import android.graphics.Paint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_bottomapp_bar.*
import sbproject.design.InterfaceCommunicator
import sbproject.design.R
import sbproject.design.bottomappbar.dialog.BottomSheetDrawerDialog
import sbproject.design.bottomappbar.dialog.BottomSheetSettingDialog
import sbproject.design.dpToPx

class BottomAppBarActivity : AppCompatActivity(), InterfaceCommunicator {

    override fun sendRequest(value: Int) {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottomapp_bar)
        setSupportActionBar(bab)

        ShapeAppearanceModel().apply {
            topLeftCorner = RoundedCornerTreatment(dpToPx(this@BottomAppBarActivity, 24f))
            topRightCorner = RoundedCornerTreatment(dpToPx(this@BottomAppBarActivity, 24f))

            MaterialShapeDrawable(this).apply {
                setTint(ContextCompat.getColor(this@BottomAppBarActivity, R.color.white))
                paintStyle = Paint.Style.FILL
                fl_content.background = this
            }
        }

        fab.setOnClickListener {
            Snackbar.make(root, "플로팅 버튼 클릭", Snackbar.LENGTH_SHORT).setAnchorView(fab).show()
        }

        bab.setNavigationOnClickListener {
            val bottomSheetDialog = BottomSheetDrawerDialog.getInstance()
            bottomSheetDialog.show(supportFragmentManager, "drawer")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_person -> {
                val bottomSheetDialog = BottomSheetSettingDialog.getInstance()
                bottomSheetDialog.show(supportFragmentManager, "setting")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
