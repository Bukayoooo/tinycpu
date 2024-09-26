/**
 * 描述CPU
 * author: huang zheng
 * date: 24/9/2024
 */
package fetch

import chisel3._
import chisel3.util._
import common.Consts._

class Core extends Module {
 
    val io = IO(new Bundle {
        val imem = Flipped(new ImemPortIO())
        val exit = Output(Bool())
    })

    val regfile = Mem(32, UInt(WORD_LEN.W))

    5


}

