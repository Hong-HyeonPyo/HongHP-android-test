package honghp.android.compose.material3

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import honghp.android.compose.R
//import rocklike.compose.weather.calcBaseDateAndTime
import rocklike.compose.weather.requestWeatherApi


@Composable
fun HomeScreen() {
    var catp2 = painterResource(R.drawable.cat2)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
            .wrapContentSize(Alignment.Center)
    ) {
        Image(painter = catp2, contentDescription = "3")
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )
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
    var lst = remember { mutableStateListOf<Weather>() }
    var lstSiz = remember { mutableIntStateOf(lst.size) }
    var dummy = remember { mutableStateOf(callWeatherApi(lst)) }

    Toast.makeText(context, "불러오는중", Toast.LENGTH_SHORT).show()
//    Log.i("홍", "불러오는 중")

//    callWeatherApi(lst)
//    Text(
//        text = "날씨",
//        style = MaterialTheme.typography.titleLarge,
//        color = Color.White,
//      modifier = Modifier.align(Alignment.CenterHorizontally),
//        textAlign = TextAlign.Center
//    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Magenta)
            .wrapContentSize(Alignment.Center)
    ) {
        var sunImg = painterResource(R.drawable.sun)
        var cloudImg = painterResource(R.drawable.cloud)
        var veryCloud = painterResource(R.drawable.gmfla)
        Row {
            Button(onClick = {
                Toast.makeText(context, "호출 시작.", Toast.LENGTH_SHORT).show()
                Log.i("홍", "버튼 클릭함. $context")
                lst.clear()
                callWeatherApi(lst)
            }) {
                Text(text = "바꾸는 버튼")
            }
            Button(onClick = {
                Toast.makeText(context,"버튼이 클릭됬다",Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "토스트 버튼")
            }
        }
        LazyColumn {
            items(lst.size) { lst2 ->
                Row {
                    Text(
                        text = lst[lst2].date.substring(0 until 4) + '-' + lst[lst2].date.substring(
                            4 until 6
                        ) + '-' + lst[lst2].date.substring(6 until 8),
                        maxLines = 1, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(130.dp), fontSize = 20.sp
                    )
                    Text(
                        text = lst[lst2].time.substring(0 until 2) + "시",
                        maxLines = 1, fontSize = 20.sp,
                        modifier = Modifier.width(60.dp)
                    )
                    Text(
                        text = lst[lst2].temp.toString() + "c", maxLines = 1, fontSize = 20.sp,
                        modifier = Modifier.width(60.dp)
                    )
//                    Text(
//                        text = lst[lst2].sky, maxLines = 1, fontSize = 20.sp,
//                        modifier = Modifier.width(60.dp)
//                    )
                    if (lst[lst2].sky.toInt() > 3) {
                        Image(
                            painter = veryCloud, contentDescription = "3",
                            Modifier
                                .height(60.dp)
                                .width(70.dp)
                        )
                    } else if (lst[lst2].sky.toInt() > 1) {
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

fun callWeatherApi(lst: SnapshotStateList<Weather>) {
    requestWeatherApi { success, r, ex ->
        Log.i("홍", "$success => ${r} ${r?.response?.body?.items?.item?.size} $ex")
        lst.clear()
        val items = r?.response?.body?.items?.item;
        val list2 = items?.filter { it.category == "TMP" }
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
//                val dmy = r?.response?.body?.items?.item?.filter { it.category == "TMP" }
//                    ?.filter { it.fcstValue.toInt()>10 }?.map { it.fcstValue.toInt() }//map으로 바꾼다
//                    ?.toList()
//                Log.i("홍", "$dmy")
        if (list2 != null) {
            lst.addAll(list2)
        }
    }
}
