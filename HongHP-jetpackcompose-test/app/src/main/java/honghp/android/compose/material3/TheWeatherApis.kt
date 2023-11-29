package honghp.android.compose.weather

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


const val SERVICE_KEY =
    "fksdflasdkfqwkfqwkfqwlelkadkfaskdfkqfklqwekwqlerkqwerkwelrkqw=";

interface WeatherApi {
    @GET("/1360000/VilageFcstInfoService_2.0/getVilageFcst")
    fun shorPrediction(
        @Query("serviceKey") serviceKey: String = SERVICE_KEY,
        @Query("pageNo") pageNo: Int = 1, @Query("numOfRows") numOfRows: Int = 1000,
        @Query("dataType") dataType: String = "json",
        @Query("base_date") base_date: String, @Query("base_time") base_time: String,
        @Query("nx") nx: Int = 63, @Query("ny") ny: Int = 126,
    ): Call<WeatherResult>
}

object RetrofitWeatherApi {
    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://apis.data.go.kr")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private val _weatherApi = retrofit.create(WeatherApi::class.java)
    val weatherApi
        get() = _weatherApi
}

data class WeatherItem(
    val baseDate: String, val baseTime: String, val category: String,
    val fcstDate: String, val fcstTime: String, val fcstValue: String,
    val nx: Int, val ny: Int
)

data class WeatherItems(var item: List<WeatherItem>)
data class WeatherBody(
    val dataType: String,
    val items: WeatherItems,
    val pageNo: Int,
    val numOfRows: Int,
    val totalCount: Int
)

data class WeatherHeader(val resultCode: String, val resultMsg: String)
data class WeatherResponse(val header: WeatherHeader, val body: WeatherBody)
data class WeatherResult(val response: WeatherResponse)


private fun safeStringToInt() {
    val k = "없음".toIntOrNull() ?: 99
    println("=== $k")
}

val yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd")!!

// yyyyMMdd HHmm
private fun calcBaseDateAndTime(): Pair<String, String> {
//    var now = ZonedDateTime.now()
    val koreaZoneId = ZoneId.of("Asia/Seoul")
    val now = ZonedDateTime.now(koreaZoneId)
//    Log.i("hong", "now => $now")
    var nowHour = now.hour
    // 02, 05, 08, 11, 14, 17, 20, 23
    var hour = if (nowHour < 2) {
        23
    } else {
        ((nowHour + 1) / 3) * 3 - 1
    }
    var hourStr = if (hour < 10) {
        "0$hour"
    } else {
        hour.toString()
    }
    hourStr = hourStr + "00"
    val dateStr = if (nowHour < 2) {
        yyyyMMdd.format(now.plusDays(-1))
    } else {
        yyyyMMdd.format(now)
    }
    return Pair(dateStr, hourStr)
}

fun main() {
    val (a, b) = calcBaseDateAndTime()
    println("== $a , $b")
    println("=================")
    requestWeatherApi { success, r, ex ->

        println("=== success:$success , result:${r?.response?.body?.items?.item?.size} , exception:$ex")
    }
}
fun requestWeatherApi(action: (Boolean, WeatherResult?, Throwable?) -> Unit) {
    val (a, b) = calcBaseDateAndTime()
    requestWeatherApi(a, b, action)
}
private fun requestWeatherApi(
    baseDate: String,
    baseTime: String,
    action: (Boolean, WeatherResult?, Throwable?) -> Unit
) {
    Log.i("홍", "baseDate:$baseDate , baseTime:$baseTime")
    println("=== baseDate:$baseDate , baseTime:$baseTime")
    val weatherApi = RetrofitWeatherApi.weatherApi
    val call = weatherApi.shorPrediction(
        base_date = baseDate, base_time = baseTime,
    )
    call.enqueue(object : Callback<WeatherResult> {
        override fun onResponse(//api
            call: Call<WeatherResult>,
            response: Response<WeatherResult>
        ) {
            println("== ${response.isSuccessful} => ${response.body()}")
            action(response.isSuccessful, response.body()!!, null)
        }
        override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
            println("== exception : $t ")
            t.printStackTrace()
            action(false, null, t)
        }
    })
}