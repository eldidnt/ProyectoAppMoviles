package dev.didnt.proyecto.servicio

import com.google.gson.GsonBuilder
import dev.didnt.proyecto.entidad.Usuario
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

object AppConstantes{
    const val BASE_URL = "http://:3001" //Añadir ip
}

interface WebService {
    @GET("/news")
    suspend fun obtenerNoticias(): Response<NoticiaResponse>
    @GET("/games")
    suspend fun obtenerJuegos(): Response<JuegoResponse>
    @POST("/login")
    suspend fun login(@Body usuario: Usuario): Response<Usuario>
    @POST("/usuarios/registrar")
    suspend fun registrarUsuario(@Body usuario: Usuario):Response<String>
    @PATCH("/usuarios/modificar/{id}")
    suspend fun modificarUsuario(@Path("id") id:Int, @Body usuario: Usuario):Response<String>
}
object RetrofitClient{
    val webService:WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}