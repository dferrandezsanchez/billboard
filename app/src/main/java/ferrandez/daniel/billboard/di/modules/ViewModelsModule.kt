package ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ferrandez.daniel.billboard.common.ViewModelFactory
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.ViewModelKey
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.viewmodel.NowPlayingViewModel


@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingViewModel::class)
    abstract fun bindNowPlayingViewModelModel(nowPlayingViewModel: NowPlayingViewModel): ViewModel

}