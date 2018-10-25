package com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.view

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.enpassio.tictactoe_mvvm.R


class GameEndDialog : DialogFragment() {

    private var rootView: View? = null
    private var activity: GameActivityAbhishek? = null
    private var winnerName: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("my_tag", "onCreateDialog of GameEndDialog called")
        initViews()
        val alertDialog = AlertDialog.Builder(getContext()!!)
            .setView(rootView)
            .setCancelable(false)
            .setPositiveButton(R.string.done) { dialog, which -> onNewGame() }
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        return alertDialog
    }

    private fun initViews() {
        Log.d("my_tag", "initViews of GameEndDialog called")
        rootView = LayoutInflater.from(getContext())
            .inflate(R.layout.game_end_dialog, null, false)
        (rootView!!.findViewById(R.id.tv_winner) as TextView).text = winnerName
    }

    private fun onNewGame() {
        Log.d("my_tag", "onNewGame of GameEndDialog called")
        dismiss()
        activity!!.promptForPlayers()
    }

    companion object {
        fun newInstance(activity: GameActivityAbhishek, winnerName: String): GameEndDialog {
            Log.d("my_tag", "newInstance of GameEndDialog called")
            val dialog = GameEndDialog()
            dialog.activity = activity
            dialog.winnerName = winnerName
            return dialog
        }
    }
}