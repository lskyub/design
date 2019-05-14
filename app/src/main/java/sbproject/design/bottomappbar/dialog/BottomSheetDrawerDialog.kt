package sbproject.design.bottomappbar.dialog

import sbproject.design.R

open class BottomSheetDrawerDialog : BaseBottomSheetDialog() {
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

    override fun getLayoutRes(): Int {
        return R.layout.dialog_bottom_sheet_drawer
    }
}