package honghp.android.compose.material3

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import org.xml.sax.DTDHandler
import kotlin.random.Random

val scrim = listOf(
    ContentsMapper.HomeScreen, ContentsMapper.Weather, ContentsMapper.Notification,
    ContentsMapper.Screen2, ContentsMapper.toDoList, ContentsMapper.theGame
)

@Composable
fun TheGame(navController: NavHostController) {
    val data = listOf("☕️", "🙂", "🥛", "🎉", "📐", "🎯", "🧩", "😄")
    var dt = mutableSetOf<Int>()
    var dmy = true
    var dumy: Boolean
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
//            .wrapContentSize(Alignment.Center),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(8) { item ->
                var a = 9
                a = Random.nextInt(1, 9)
                dumy = true
                if (dt.size < 1) {// 안에 없다면
                    dt.add(a)
                    dumy = false
                } else {
                    var newDt = makeRandom(dt)
                    dt.add(newDt)
                    a = newDt
                }
                dt.add(a)
                Card(
//                    elevation = 4.dp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
//                        .width(50.dp)
                        .height(100.dp)
                        .padding(2.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),

                    ) {
                    Text(
                        text = "${a}",
                        fontSize = 42.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .border(1.dp, Color.Cyan),
                    )
                }
            }
        }
        Button(onClick = {
            navController.navigate(ContentsMapper.theGame.route) {//scrim[6].route
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
//            Log.d("홍", dt.toString() + "끝")
        }) {
            Text(text = "스타")
        }
    }
}

fun makeRandom(dt: MutableSet<Int>): Int {
    var siz = dt.size
    var a = Random.nextInt(1, 9)
    while (dt.size == siz) {
        dt.add(a)
        Log.d("홍", dt.toString() + "그리고" + a.toString())
        if (dt.size == siz) {
            a = Random.nextInt(1, 9)
        }
    }
    return a
}