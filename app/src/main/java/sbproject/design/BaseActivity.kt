package sbproject.design

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity : AppCompatActivity() {
    protected val disposables by lazy {
        CompositeDisposable()
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}