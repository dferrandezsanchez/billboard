package ferrandez.daniel.billboard.ferrandez.daniel.billboard.usecases

import ferrandez.daniel.billboard.executors.PostExecutionThread
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.mappers.asDataEntity
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.mappers.asUIEntity
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.data.repositories.MoviesRepository
import ferrandez.daniel.remote.executors.JobExecutor
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class SetMovieWantToWatchUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val postExecutionThread: PostExecutionThread,
    private val jobExecutor: JobExecutor
) {

    fun execute(movie : UIMovie): Completable =
        moviesRepository.setWantToWatch(movie.asDataEntity())
            .subscribeOn(jobExecutor.getScheduler())
            .observeOn(postExecutionThread.getScheduler())
}