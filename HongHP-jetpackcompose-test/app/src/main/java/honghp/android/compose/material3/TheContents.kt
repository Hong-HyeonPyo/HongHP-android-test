package honghp.android.compose.material3

import honghp.android.compose.weather.requestWeatherApi
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import honghp.android.compose.R
//import rocklike.compose.weather.calcBaseDateAndTime


@Composable
fun HomeScreen() {
    var nabi = painterResource(R.drawable.catnabi)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
            .wrapContentSize(Alignment.Center)
    ) {
        Image(painter = nabi, contentDescription = "3",Modifier.size(350.dp,350.dp))
    }
}

data class Weather(
    var date: String,
    var time: String,
    var temp: Int,
    var sky: String,
)

//하늘상태(SKY) 코드 : 맑음(1), 구름많음(3), 흐림(4)
@Composable
fun FavouriteScreen() {
    val context = LocalContext.current
//    var lst = remember { mutableStateListOf<Weather>() }
    val lst = rememberSaveable { mutableStateOf(listOf<Weather>()) }
    val fetched = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(fetched.value){
        if(!fetched.value){
            fetched.value = true
            callWeatherApi(lst,context)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
        var sunImg = painterResource(R.drawable.sun)
        var cloudImg = painterResource(R.drawable.cloud)
        var veryCloud = painterResource(R.drawable.gmfla)
        Row {
            Button(onClick = {
                Toast.makeText(context, "호출 시작.", Toast.LENGTH_SHORT).show()
                Log.i("홍", "버튼 클릭함. $context")
//                lst.value.clear()
                callWeatherApi(lst,context)
            }) {
                Text(text = "새로고침")
            }
        }

        LazyColumn {
            items(lst.value.size) { lst2 ->
                Row {
                    Text(
                        text = lst.value[lst2].date.substring(0 until 4) + '-' + lst.value[lst2].date.substring(
                            4 until 6
                        ) + '-' + lst.value[lst2].date.substring(6 until 8),
                        maxLines = 1, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(130.dp), fontSize = 20.sp
                    )
                    Text(
                        text = lst.value[lst2].time.substring(0 until 2) + "시",
                        maxLines = 1, fontSize = 20.sp,
                        modifier = Modifier.width(60.dp)
                    )
                    Text(
                        text = lst.value[lst2].temp.toString() + "c", maxLines = 1, fontSize = 20.sp,
                        modifier = Modifier.width(60.dp)
                    )
//                    Text(
//                        text = lst[lst2].sky, maxLines = 1, fontSize = 20.sp,
//                        modifier = Modifier.width(60.dp)
//                    )
                    if (lst.value[lst2].sky.toInt() > 3) {
                        Image(
                            painter = veryCloud, contentDescription = "3",
                            Modifier
                                .height(60.dp)
                                .width(70.dp)
                        )
                    } else if (lst.value[lst2].sky.toInt() > 1) {
                        Image(
                            painter = cloudImg, contentDescription = "3",
                            Modifier
                                .height(60.dp)
                                .width(70.dp)
                        )
                    } else {
                        Image(
                            painter = sunImg, contentDescription = "3",
                            Modifier
                                .height(60.dp)
                                .width(70.dp)
                        )
                    }
                }
            }
        }
    }
}
fun callWeatherApi(lst: MutableState<List<Weather>>, context: Context) {
    requestWeatherApi { success, r, ex ->
//        Log.i("홍", "$success => ${r} ${r?.response?.body?.items?.item?.size} $ex")
//        lst.clear()
        val items = r?.response?.body?.items?.item;
        val list2 = items
            ?.filter { it.category == "TMP" }
            ?.map {
                val fcstDate = it.fcstDate
                val fcstTime = it.fcstTime

                var first = items.filter { elem -> elem.category == "SKY" }
                    .filter { elem -> elem.fcstDate == fcstDate && elem.fcstTime == fcstTime }
                    .map { elem -> elem.fcstValue }
                    .firstOrNull() ?: ""
                Weather(
                    fcstDate,
                    fcstTime,
                    it.fcstValue.toInt(),
                    first,
                )
            }

        if (list2 != null) {
            lst.value=list2
            Toast.makeText(context, "불러옴 (갯수:${lst.value.size})", Toast.LENGTH_SHORT).show()
        }
    }
}
