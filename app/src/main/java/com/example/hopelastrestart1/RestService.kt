import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestService {
    companion object {
        val BASE_URL = "http://rancher.exaact.co:8085/"
        var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().apply {
                    readTimeout(20, TimeUnit.SECONDS)
                    writeTimeout(20, TimeUnit.SECONDS)
                    connectTimeout(20, TimeUnit.SECONDS)
                    addInterceptor(interceptor)
                    addInterceptor { chain ->
                        var request = chain.request()
                        request = request.newBuilder()
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json")
                            .header("Authorization", "8272fcc2-20fc-45f0-8b3d-0ac5cff51a5d")
                            .build()
                        val response = chain.proceed(request)
                        response
                    }
                }
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }

            return retrofit
        }
    }
    }