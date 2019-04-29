package sbproject.design.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_bottom_sheet_setting.view.*
import sbproject.design.InterfaceCommunicator
import sbproject.design.R

open class BottomSheetSettingDialog : BottomSheetDialogFragment(), View.OnClickListener {
    companion object {
        private var dialog: BottomSheetSettingDialog? = null
        fun getInstance(): BottomSheetSettingDialog {
            dialog?.let {
                return it
            }.let {
                return BottomSheetSettingDialog()
            }
        }
    }

    var listener: InterfaceCommunicator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            listener = context as InterfaceCommunicator
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.dialog_bottom_sheet_setting, container, false)
        view.ll_notifications.setOnClickListener(this)
        view.ll_logout.setOnClickListener(this)
        view.ll_setting.setOnClickListener(this)
        view.ll_version.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        v?.id?.let {
            listener?.sendRequest(it)
        }.let {
            listener?.sendRequest(-1)
        }
        dismiss()
    }
}