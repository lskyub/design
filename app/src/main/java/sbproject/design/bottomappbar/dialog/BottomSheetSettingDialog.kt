package sbproject.design.bottomappbar.dialog

import sbproject.design.R

open class BottomSheetSettingDialog : BaseBottomSheetDialog() {
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

    override fun getLayoutRes(): Int {
        return R.layout.dialog_bottom_sheet_setting
    }
}