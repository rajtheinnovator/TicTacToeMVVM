package com.enpassio.tictactoe_mvvm.viewmodel

/**
 * Created by Greta Grigutė on 2018-10-25.
 */
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayMap
import android.util.Log
import com.enpassio.tictactoe_mvvm.model.Cell
import com.enpassio.tictactoe_mvvm.model.Game
import com.enpassio.tictactoe_mvvm.model.Player
import com.enpassio.tictactoe_mvvm.utilities.StringUtility.stringFromNumbers

class GameViewModel : ViewModel() {
    var cells: ObservableArrayMap <String, String>? = null
 var game: Game? = null

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