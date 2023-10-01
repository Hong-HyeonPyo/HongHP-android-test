package honghp.android.compose.material3

import android.util.Log
import android.widget.Space
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import honghp.android.compose.R
import kotlin.random.Random

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

class A(
    var txt: String,
    var whn: String,
    var wtr: Int,
    var rin: Int
)

@Composable
fun FavouriteScreen() {
    var lst = remember { mutableStateListOf<A>() }
    var lstSiz = remember { mutableIntStateOf(lst.size) }
    var dmy = A("날씨", "시각", 30, 100)
    lst.add(dmy)
    for (i in 0..24) {
        dmy = A("서울", "$i:00", Random.nextInt(1, 50), Random.nextInt(0, 100))
        lst.add(dmy)
    }
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
        var noSunImg = painterResource(R.drawable.nosun)
        var nomalSunImg = painterResource(R.drawable.gmfla)
        Button(onClick = {
            for (i in 0..lst.size-1) {
                lst.removeAt(0)
            }
            if(lst.size <3) {
                var dmy = A("위치", "시각", 30, 100)
                lst.add(dmy)
                for (i in 0..24) {
                    dmy = A("서울", "$i:00", Random.nextInt(1, 50), Random.nextInt(0, 100))
                    lst.add(dmy)
                }
            }
        }) {
            Text(text = "버튼")
        }
        LazyColumn {
            items(lst.size) { lst2 ->
                Row {
                    Text(
                        text = lst[lst2].txt, maxLines = 1, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(70.dp), fontSize = 20.sp
                    )
                    Text(
                        text = lst[lst2].whn, maxLines = 1, fontSize = 20.sp,
                        modifier = Modifier.width(70.dp)
                    )
                    Text(
                        text = lst[lst2].wtr.toString() + "c", maxLines = 1, fontSize = 20.sp,
                        modifier = Modifier.width(70.dp)
                    )
                    Text(
                        text = lst[lst2].rin.toString() + "%", maxLines = 1, fontSize = 20.sp,
                        modifier = Modifier.width(70.dp)
                    )
//                    Text(text =  lst[lst2].wtr.toString())
                    if (lst[lst2].rin > 70) {
                        Image(
                            painter = noSunImg, contentDescription = "3",
                            Modifier
                                .height(70.dp)
                                .width(70.dp)
                        )
                    } else if (lst[lst2].rin > 30) {
                        Image(
                            painter = nomalSunImg, contentDescription = "3",
                            Modifier
                                .height(70.dp)
                                .width(70.dp)
                        )
                    } else {
                        Image(
                            painter = sunImg, contentDescription = "3",
                            Modifier
                                .height(70.dp)
                                .width(70.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatScreen() {
    var lst = mutableListOf<String>("1", "2", "3")
    var catp = painterResource(R.drawable.cat)
    var shouldShowDialog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    LazyColumn() {
        items(lst.size) {
            Column {
                Image(painter = catp, contentDescription = "3")
                Button(onClick = {
                    Toast.makeText(context, "닫힘", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "버튼")
                }
                Text(text = "에엒따")
            }
        }
    }
}