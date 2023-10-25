package at.fh.js.ims.banking

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment

class NoEmptyInputs: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.emptyinputs, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        return builder.create()
    }
}