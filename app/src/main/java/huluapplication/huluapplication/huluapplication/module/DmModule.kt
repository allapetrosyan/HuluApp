package huluapplication.huluapplication.huluapplication.module

import android.content.Context
import huluapplication.huluapplication.huluapplication.constant.Constants.Companion.BASE_URL
import huluapplication.huluapplication.huluapplication.network.ApiInterface
import huluapplication.huluapplication.huluapplication.repo.Repository
import huluapplication.huluapplication.huluapplication.repo.RepositoryImpl
import huluapplication.huluapplication.huluapplication.viewmodel.MyViewModel
import huluapplication.huluapplication.huluapplication.viewmodel.ViewModelForActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DmModule {

    companion object {
        val apiModule = module {
            fun provideRetrofitApi(retrofit: Retrofit): ApiInterface {
                return retrofit.create(ApiInterface::class.java)
            }
            single { provideRetrofitApi(get()) }
        }

        val viewModelModule = module {
            viewModel {
                MyViewModel(repository = get())
            }
        }
        val viewModelModule2 = module {
            viewModel {
                ViewModelForActivity(repository = get())
            }
        }


        val repositoryModule = module {
            fun provideRepository(
                api: ApiInterface,
                context: Context,
            ): Repository {
                return RepositoryImpl(api)
            }
            single { provideRepository(get(), androidContext()) }
        }


        val networkModule = module {

            fun provideHttpClient(): OkHttpClient {
                val okHttpClientBuilder = OkHttpClient.Builder()

                if (BuildConfig.DEBUG) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
                }
                okHttpClientBuilder.build()
                return okHttpClientBuilder.build()
            }

            fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    // .client(client) //todo optimmal param
                    .build()
            }

            single { provideHttpClient() }
            single {
                val baseUrl = BASE_URL
                provideRetrofit(get(), baseUrl)
            }
        }

    }

}