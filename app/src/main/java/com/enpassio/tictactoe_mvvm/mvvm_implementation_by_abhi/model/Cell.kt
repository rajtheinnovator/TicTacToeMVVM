package com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.model

import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_abhi.utilities.StringUtility

class Cell(var player: Player?) {

    val isEmpty: Boolean
        get() = player == null || StringUtility.isNullOrEmpty(player!!.value)
}