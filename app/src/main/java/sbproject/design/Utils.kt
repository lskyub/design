package sbproject.design

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun <T : CoordinatorLayout.Behavior<*>> View.findBehavior(): T = layoutParams.run {
    if (this !is CoordinatorLayout.LayoutParams) throw IllegalArgumentException("View's layout params should be CoordinatorLayout.LayoutParams")

    (layoutParams as CoordinatorLayout.LayoutParams).behavior as? T
        ?: throw IllegalArgumentException("Layout's behavior is not current behavior")
}

/**
 * DP 를 픽셀로 변환하는 메소드.
 *
 * @param context the context
 * @param dp      dp
 * @return dp 에서 변환된 픽셀 값.
 */
fun dpToPx(context: Context, dp: Float): Float {
    val displayMetrics = context.resources.displayMetrics
    return dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)
}

fun getDrawable(res: Resources, id: Int): Drawable {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        res.getDrawable(id, null)
    } else {
        res.getDrawable(id)
    }
}