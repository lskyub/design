package sbproject.design.backdrop

import android.graphics.Paint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.android.synthetic.main.activity_backdrop.*
import sbproject.design.R
import sbproject.design.backdrop.custom.BackdropBehavior
import sbproject.design.dpToPx
import sbproject.design.findBehavior

class BackDropActivity : AppCompatActivity(), MenuItem.OnMenuItemClickListener {

    private lateinit var backdropBehavior: BackdropBehavior

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backdrop)

        ShapeAppearanceModel().apply {
            topLeftCorner = RoundedCornerTreatment(dpToPx(this@BackDropActivity, 24f))
            topRightCorner = RoundedCornerTreatment(dpToPx(this@BackDropActivity, 24f))

            MaterialShapeDrawable(this).apply {
                setTint(ContextCompat.getColor(this@BackDropActivity, R.color.white))
                paintStyle = Paint.Style.FILL
                fl_content.background = this
            }
        }

        backdropBehavior = fl_root_content.findBehavior()
        backdropBehavior.attachBackLayout(backLayout.id)

        var menu: Menu = navigationView.menu
        setMainTitle("BackDropView")
        addMenu(menu, R.drawable.ic_assignment_ind, "온라인")
        addMenu(menu, R.drawable.ic_forum, "채팅")
        addMenu(menu, R.drawable.ic_recent_actors, "임직원 정보")
        addMenu(menu, R.drawable.ic_line_style, "조직원 구성도")
        addMenu(menu, R.drawable.ic_description, "내 문서")
        addMenu(menu, R.drawable.ic_rate_review, "결제문서")
    }

    fun addMenu(menu: Menu, itemid: Int, title: String) {
        var item = menu.add(0, itemid, 0, title)
        item.icon = sbproject.design.getDrawable(resources, itemid)
        item.setOnMenuItemClickListener(this)
    }

    private fun setMainTitle(title: String) {
        toolbar?.let {
            tv_title.text = title
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return true
    }
}