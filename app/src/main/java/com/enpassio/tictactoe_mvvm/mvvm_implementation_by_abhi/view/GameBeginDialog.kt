package com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.view

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import com.enpassio.tictactoe_mvvm.R


class GameBeginDialog : DialogFragment() {

    private var player1Layout: TextInputLayout? = null
    private var player2Layout: TextInputLayout? = null

    private var player1EditText: TextInputEditText? = null
    private var player2EditText: TextInputEditText? = null

    private var player1: String? = null
    private var player2: String? = null

    private var rootView: View? = null
    private var activity: GameActivity? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initViews()
        val alertDialog = AlertDialog.Builder(getContext()!!)
            .setView(rootView)
            .setTitle(R.string.game_dialog_title)
            .setCancelable(false)
            .setPositiveButton(R.string.done, null)
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        alertDialog.setOnShowListener({ dialog -> onDialogShow(alertDialog) })
        return alertDialog
    }

    private fun initViews() {
        rootView = LayoutInflater.from(getContext())
            .inflate(R.layout.game_begin_dialog, null, false)

        player1Layout = rootView!!.findViewById(R.id.layout_player1)
        player2Layout = rootView!!.findViewById(R.id.layout_player2)

        player1EditText = rootView!!.findViewById(R.id.et_player1)
        player2EditText = rootView!!.findViewById(R.id.et_player2)
        addTextWatchers()
    }

    private fun onDialogShow(dialog: AlertDialog) {
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener({ v -> onDoneClicked() })
    }

    private fun onDoneClicked() {
        if (isAValidName(player1Layout, player1) and isAValidName(player2Layout, player2)) {
            activity!!.onPlayersSet(player1!!, player2!!)
            dismiss()
        }
    }

    private fun isAValidName(layout: TextInputLayout?, name: String?): Boolean {
        if (TextUtils.isEmpty(name)) {
            layout!!.isErrorEnabled = true
            layout.error = getString(R.string.game_dialog_empty_name)
            return false
        }

        if (player1 != null && player2 != null && player1!!.equals(player2!!, ignoreCase = true)) {
            layout!!.isErrorEnabled = true
            layout.error = getString(R.string.game_dialog_same_names)
            return false
        }

        layout!!.isErrorEnabled = false
        layout.error = ""
        return true
    }

    private fun addTextWatchers() {
        player1EditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                player1 = s.toString()
            }
        })
        player2EditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                player2 = s.toString()
            }
        })
    }

    companion object {

        fun newInstance(activity: GameActivity): GameBeginDialog {
            val dialog = GameBeginDialog()
            dialog.activity = activity
            return dialog
        }
    }
}