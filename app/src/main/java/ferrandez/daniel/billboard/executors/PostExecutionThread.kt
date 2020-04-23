package ferrandez.daniel.billboard.executors

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}