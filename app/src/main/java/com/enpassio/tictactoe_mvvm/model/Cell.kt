package com.enpassio.tictactoe_mvvm.model

import com.enpassio.tictactoe_mvvm.utilities.StringUtility

class Cell(var player: Player?) {

    val isEmpty: Boolean
        get() = player == null || StringUtility.isNullOrEmpty(player!!.value)
}