package rocklike.compose

import com.google.gson.GsonBuilder

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

fun main() {
//    val list = List<Int>(5, { it+1 }) // 1,2,3,4,5
//    val l = list.map { it * 10 }.filter { it>20 }.first()
//
//    println(l)

//    println(list)
//    println( test(1,2,3,4,5) )
//    println( test( *list.toIntArray() ) )
    val call = TheApi.api.postTest()
    call.enqueue(object:Callback<Resp>{
        override fun onResponse(call: Call<Resp>, response: Response<Resp>) {
            val body = response.body()
            val headers = response.headers()
            println("=== 성공 body \n ${body}")
            println("=== 성공 header \n ${headers}")
        }

        override fun onFailure(call: Call<Resp>, t: Throwable) {
            println("=== 실패")
            t.printStackTrace()
        }
    })
}

fun test(vararg k:Int): Int {
    return k.size
}

interface TestApi {
    @GET("/get")
    fun getTest(@Query("name") name: String ): Call<HashMap<String,Any?>>

    @POST("/post")
    fun postTest( ): Call<Resp>
}

data class Resp(val json:String, val origin:String,
                val url:String, val headers:Headers,)
data class Headers(val length:String, val Referer:String)

object TheApi {
    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://httpbin.org/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    val api = retrofit.create(TestApi::class.java)
//    val testApi
//        get() = api
}