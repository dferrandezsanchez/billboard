package ferrandez.daniel.billboard.ferrandez.daniel.billboard.usecases

import ferrandez.daniel.billboard.executors.PostExecutionThread
import ferrandez.daniel.data.model.MovieEntity
import ferrandez.daniel.data.repositories.MoviesRepository
import ferrandez.daniel.remote.executors.JobExecutor
import io.reactivex.Flowable
import javax.inject.Inject


class BindAllWantedToWatchMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val postExecutionThread: PostExecutionThread,
    private val jobExecutor: JobExecutor
) {

    fun execute(): Flowable<List<MovieEntity>> =
        moviesRepository.bindAllWantedToWatchMovies()
            .subscribeOn(jobExecutor.getScheduler())
            .observeOn(postExecutionThread.getScheduler())
}