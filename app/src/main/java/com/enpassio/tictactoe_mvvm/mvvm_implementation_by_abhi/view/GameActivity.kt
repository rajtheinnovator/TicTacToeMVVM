package com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.enpassio.tictactoe_mvvm.R
import com.enpassio.tictactoe_mvvm.databinding.ActivityGameBinding
import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.model.Player
import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.utilities.StringUtility.isNullOrEmpty
import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.viewmodel.GameViewModel


class GameActivity : AppCompatActivity() {
    private var gameViewModel: GameViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("my_tag", "onCreate of Activity called")
        promptForPlayers()
    }

    fun promptForPlayers() {
        Log.d("my_tag", "promptForPlayers of Activity called")
        val dialog = GameBeginDialog.newInstance(this)
        dialog.setCancelable(false)
        dialog.show(supportFragmentManager, GAME_BEGIN_DIALOG_TAG)
    }

    fun onPlayersSet(player1: String, player2: String) {
        Log.d("my_tag", "onPlayersSet of Activity called")
        initDataBinding(player1, player2)
    }

    private fun initDataBinding(player1: String, player2: String) {
        Log.d("my_tag", "initDataBinding of Activity called")
        val activityGameBinding = DataBindingUtil.setContentView<ActivityGameBinding>(this, R.layout.activity_game)
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        gameViewModel!!.init(player1, player2)
        activityGameBinding.gameViewModel = gameViewModel
        setUpOnGameEndListener()
    }

    private fun setUpOnGameEndListener() {
        Log.d("my_tag", "setUpOnGameEndListener of Activity called")
        gameViewModel!!.winner.observe(this, Observer<Player> { this.onGameWinnerChanged(it) })
    }

    @VisibleForTesting
    fun onGameWinnerChanged(winner: Player?) {
        Log.d("my_tag", "onGameWinnerChanged of Activity called")
        val winnerName = if (winner == null || isNullOrEmpty(winner.name)) NO_WINNER else winner.name
        val dialog = GameEndDialog.newInstance(this, winnerName)
        dialog.setCancelable(false)
        dialog.show(supportFragmentManager, GAME_END_DIALOG_TAG)
    }

    companion object {

        private val GAME_BEGIN_DIALOG_TAG = "game_dialog_tag"
        private val GAME_END_DIALOG_TAG = "game_end_dialog_tag"
        private val NO_WINNER = "No one"
    }
}