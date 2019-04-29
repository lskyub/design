package sbproject.design.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_bottom_sheet_drawer.view.*
import sbproject.design.InterfaceCommunicator
import sbproject.design.R

open class BottomSheetDrawerDialog : BottomSheetDialogFragment(), View.OnClickListener {
    companion object {
        private var dialog: BottomSheetDrawerDialog? = null
        fun getInstance(): BottomSheetDrawerDialog {
            dialog?.let {
                return it
            }.let {
                return BottomSheetDrawerDialog()
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
        var view = inflater.inflate(R.layout.dialog_bottom_sheet_drawer, container, false)
        view.ll_dashboard.setOnClickListener(this)
        view.ll_group_work.setOnClickListener(this)
        view.ll_group.setOnClickListener(this)
        view.ll_record_voice_over.setOnClickListener(this)
        view.ll_recent_actors.setOnClickListener(this)
        view.ll_description.setOnClickListener(this)
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