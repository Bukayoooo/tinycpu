/**
 * 连接CPU和存储器的包装类（wrapper）
 * author: huang zheng
 * date: 24/9/2024
 */
package fetch

import chisel3._
import chisel.util._

class Top extends Module {
    val io = IO(new Bundle {
        val exit = Output(Bool())
    })

    val core = Module(new Core())
    val memory = Module(new Memory())

    core.io.imem <> memory.io.imem

    io.exit := core.io.exit
}