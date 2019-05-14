package sbproject.design.textinputlayout

import android.content.Intent
import android.os.Bundle
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.activity_textinputlayout.*
import sbproject.design.R
import sbproject.design.BaseActivity
import sbproject.design.view.MainActivity

/**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2019, openit Inc.
 * All rights reserved.
 */
class TextInputLayoutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textinputlayout)

        mb_company.clicks()
            .subscribe {
                startActivity(Intent(this@TextInputLayoutActivity, MainActivity::class.java))
                finish()
            }.apply {
                disposables.add(this)
            }
    }
}