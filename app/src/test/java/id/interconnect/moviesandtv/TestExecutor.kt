package id.interconnect.moviesandtv

import java.util.concurrent.Executor

class TestExecutor: Executor {
    override fun execute(command: Runnable) {
        command.run()
    }
}