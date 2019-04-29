package sbproject.design

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import sbproject.design.custom.BackdropBehavior
import sbproject.design.dialog.BottomSheetDrawerDialog
import sbproject.design.dialog.BottomSheetSettingDialog

class MainActivity : AppCompatActivity(), InterfaceCommunicator {
    private lateinit var backdropBehavior: BackdropBehavior
    var BACKDROP_STATE_HANDLER_KEY: Int = 0

    override fun sendRequest(value: Int) {
        when (value) {
            R.id.ll_notifications -> {
                var menu = navigationView.menu
                menu.clear()
            }
            R.id.ll_logout -> {
                finish()
            }
            R.id.ll_setting -> {
                var menu = navigationView.menu
                menu.clear()
            }
            R.id.ll_version -> {
            }
            R.id.ll_dashboard -> {
                var menu = navigationView.menu
                menu.clear()
            }
            R.id.ll_group_work -> {
                var menu = navigationView.menu
                menu.clear()
            }
            R.id.ll_group -> {
                var menu = navigationView.menu
                menu.clear()
            }
            R.id.ll_record_voice_over -> {
                var menu = navigationView.menu
                menu.clear()
                var item = menu.add(0, 0, 0, "온라인")
                item.icon = resources.getDrawable(R.drawable.ic_assignment_ind)
                item = menu.add(0, 1, 0, "채팅")
                item.icon = resources.getDrawable(R.drawable.ic_forum)
            }
            R.id.ll_recent_actors -> {
                var menu = navigationView.menu
                menu.clear()
                var item = menu.add(0, 0, 0, "임직원 정보")
                item.icon = resources.getDrawable(R.drawable.ic_recent_actors)
                item = menu.add(0, 1, 0, "조직원 구성도")
                item.icon = resources.getDrawable(R.drawable.ic_line_style)
            }
            R.id.ll_description -> {
                var menu = navigationView.menu
                menu.clear()
                var item = menu.add(0, 0, 0, "내 문서")
                item.icon = resources.getDrawable(R.drawable.ic_description)
                item = menu.add(0, 1, 0, "결제문서")
                item.icon = resources.getDrawable(R.drawable.ic_description)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bab)

        ShapeAppearanceModel().apply {
            topLeftCorner = RoundedCornerTreatment(dpToPx(this@MainActivity, 24f))
            topRightCorner = RoundedCornerTreatment(dpToPx(this@MainActivity, 24f))

            MaterialShapeDrawable(this).apply {
                setTint(ContextCompat.getColor(this@MainActivity, android.R.color.white))
                paintStyle = Paint.Style.FILL
                fl_content.background = this
            }
        }

        backdropBehavior = fl_root_content.findBehavior()
        with(backdropBehavior) {
            attachBackLayout(R.id.backLayout)
        }
        with(toolbar) {
            setTitle(R.string.app_name)
        }

        backdropBehavior.addOnDropListener(object : BackdropBehavior.OnDropListener {
            override fun onDrop(dropState: BackdropBehavior.DropState, fromUser: Boolean) {
                if (!fromUser) {
                    callDialog()
                }
            }
        })

        fab.setOnClickListener {
            BACKDROP_STATE_HANDLER_KEY = 1
            callDialog()
        }

        bab.setNavigationOnClickListener {
            BACKDROP_STATE_HANDLER_KEY = 2
            callDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_person -> {
                BACKDROP_STATE_HANDLER_KEY = 3
                callDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun callDialog() {
        if (!backdropBehavior.close(true)) {
            handler.sendEmptyMessage(BACKDROP_STATE_HANDLER_KEY)
        }
    }

    private var handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                1 -> {
                    Snackbar.make(root, "플로팅 버튼 클릭", Snackbar.LENGTH_SHORT).setAnchorView(fab).show()
                }
                2 -> {
                    val bottomSheetDialog = BottomSheetDrawerDialog.getInstance()
                    bottomSheetDialog.show(supportFragmentManager, "drawer")
                }
                3 -> {
                    val bottomSheetDialog = BottomSheetSettingDialog.getInstance()
                    bottomSheetDialog.show(supportFragmentManager, "setting")
                }
            }
            BACKDROP_STATE_HANDLER_KEY = 0
        }
    }
}
