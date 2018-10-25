package com.enpassio.tictactoe_mvvm.mvvm_implementation_by_greta.model

import com.enpassio.tictactoe_mvvm.mvvm_implementation_by_greta.utilities.StringUtility

/**
 * Created by Greta Grigutė on 2018-10-25.
 */

class Cell(var player: Player?) {

    val isEmpty: Boolean
        get() = player == null || StringUtility.isNullOrEmpty(player!!.value)
}