package com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayMap
import android.util.Log
import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.model.Cell
import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.model.Game
import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.model.Player
import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.utilities.StringUtility.stringFromNumbers


class GameViewModel : ViewModel() {

    var cells: ObservableArrayMap<String, String>? = null
    private var game: Game? = null

    val winner: LiveData<Player>
        get() = game!!.winner

    fun init(player1: String, player2: String) {
        game = Game(player1, player2)
        cells = ObservableArrayMap()
    }

    fun onClickedCellAt(row: Int, column: Int) {
        Log.d("my_tag", "onClickedCellAt of GameViewModel called")
        if (game?.cells?.get(row)?.get(column) != null) {
            game!!.cells!![row][column] = Cell(game!!.currentPlayer)
            cells?.set(stringFromNumbers(row, column), game!!.currentPlayer!!.value)
            Log.d("my_tag", "value is: " + game!!.currentPlayer!!.value)
            if (game!!.hasGameEnded())
                game!!.reset()
            else
                game!!.switchPlayer()
        }
    }

}