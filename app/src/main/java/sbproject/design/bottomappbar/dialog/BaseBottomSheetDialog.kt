package sbproject.design.bottomappbar.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import sbproject.design.InterfaceCommunicator

abstract class BaseBottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {
    var listener: InterfaceCommunicator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            listener = context as InterfaceCommunicator
        }
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(getLayoutRes(), container, false)
        var count = (view as ViewGroup).childCount
        for (i in 0 until count) {
            view.getChildAt(i).setOnClickListener(this)
        }
        return view
    }

    override fun onClick(v: View?) {
        v?.id?.let {
            listener?.sendRequest(it)
        }
        dismiss()
    }
}