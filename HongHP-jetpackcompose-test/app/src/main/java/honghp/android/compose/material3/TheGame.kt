package honghp.android.compose.material3

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen() {
    var selectedIdx by remember { mutableStateOf(-1) }
    var vv by remember { mutableStateOf(true) }
    val idxList = remember { mutableStateListOf<Int>(*List(9, { it }).toTypedArray()) }
//    val idxList = remember { mutableStateListOf<Int>(0,1,2,3,4,5,6,7,8) }
    var isSuccess by remember { mutableStateOf(false) }
    Log.d("홍","시작")
    Log.d("홍","${selectedIdx}")
    Log.d("홍","${idxList}")
    fun setSelectedIdx(idx: Int) {
        selectedIdx = idx
    }
    fun setSuccess(s: Boolean) {
        isSuccess = s
    }
    fun initIdx() {
        Log.i("hong", "initIdx() 호출")
        selectedIdx = selectedIdx + 10
    }
    Column {
        RandomizeButton(idxList, { s: Boolean -> setSuccess(s) }) {  initIdx() }

        WithTheBoxes(
            selectedIdx,
            idxList,
            { s: Boolean -> setSuccess(s) },
            { idx: Int -> setSelectedIdx(idx) }
        )
        if (isSuccess) {
            Text(
                text = "성공 !!", textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(), fontSize = 40.sp,
                color = Color.Magenta
            )
        }
    }
}
fun makeRandomList(idxList: SnapshotStateList<Int>) {
    for (idx in idxList.indices) {
        idxList[idx] = -1
    }
    val size = idxList.size

    // random값 셋팅
    for (idx in idxList.indices) {
        val r = (0..8).random()
        for (i in r..r + size) {
            val k = i % size
            if (idxList[k] == -1) {
                idxList[k] = idx
                break
            }
        }
    }
}
@Composable
fun RandomizeButton(
    idxList: SnapshotStateList<Int>,
    setSuccess: (Boolean) -> Unit,
    initIdx: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(modifier = Modifier
            //        .fillMaxWidth()
            .weight(2f)
            .padding(horizontal = 5.dp, vertical = 0.dp),
            shape = RoundedCornerShape(10),
            onClick = {
                makeRandomList(idxList)
                initIdx()
                setSuccess(false)
            }) {
            Text("섞자", fontSize = 20.sp)
        }
        Button(modifier = Modifier
            //        .fillMaxWidth()
            .weight(1f)
            .padding(horizontal = 5.dp, vertical = 0.dp),
            shape = RoundedCornerShape(10),
            onClick = {
                for (idx in idxList.indices) {
                    idxList[idx] = idx
                }
                initIdx()
            }) {
            Text("초기화", fontSize = 20.sp)
        }
    }
}

fun onClickBox(
    idx: Int,
    ctx: Context,
    idxList: SnapshotStateList<Int>,
    setSuccess: (Boolean) -> Unit
) {
    val hole = idxList.mapIndexed { idx, value -> Pair(idx, value) }
        .filter { (idx, value) -> value == 8 }.first()
    val holeMaxtrix = convertIdxToMatrix(hole.first)
    val currMaxtrix = convertIdxToMatrix(idx)

    if (holeMaxtrix.second == currMaxtrix.second) {
        // y좌표 비교
        if (holeMaxtrix.first < currMaxtrix.first) {
            for (pos in holeMaxtrix.first + 1..currMaxtrix.first) {
                replace(Pair(pos, currMaxtrix.second), -1, 0, idxList)
            }
            idxList[convertMatrixToIdx(currMaxtrix)] = 8
        }
        if (holeMaxtrix.first > currMaxtrix.first) {
            for (pos in holeMaxtrix.first - 1 downTo currMaxtrix.first) {
                replace(Pair(pos, currMaxtrix.second), 1, 0, idxList)
            }
            idxList[convertMatrixToIdx(currMaxtrix)] = 8
        }
    }
    if (holeMaxtrix.first == currMaxtrix.first) {
        // x좌표 비교
        if (holeMaxtrix.second < currMaxtrix.second) {
            for (pos in holeMaxtrix.second + 1..currMaxtrix.second) {
                replace(Pair(currMaxtrix.first, pos), 0, -1, idxList)
            }
            idxList[convertMatrixToIdx(currMaxtrix)] = 8
        }
        if (holeMaxtrix.second > currMaxtrix.second) {
            for (pos in holeMaxtrix.second - 1 downTo currMaxtrix.second) {
                replace(Pair(currMaxtrix.first, pos), 0, 1, idxList)
            }
            idxList[convertMatrixToIdx(currMaxtrix)] = 8
        }
    }
    // 순서대로 맞았으면 success를 외침
    var success = idxList.filterIndexed { idx, elem -> idx != elem }.firstOrNull() == null
    if (success) {
        setSuccess(true)
    } else {
        setSuccess(false)
    }

}

fun convertMatrixToIdx(m: Pair<Int, Int>): Int {
    return (m.first) * 3 + m.second
}

fun convertIdxToMatrix(idx: Int): Pair<Int, Int> {
    val y = idx / 3
    val x = idx % 3
    return Pair(y, x)
}

fun replace(src: Pair<Int, Int>, distanceY: Int, distanceX: Int, idxList: SnapshotStateList<Int>) {
    val theValue = idxList[convertMatrixToIdx(src)]
    if (distanceY != 0) {
        val newPos = Pair(src.first + distanceY, src.second)
        idxList[convertMatrixToIdx(newPos)] = theValue
    }
    if (distanceX != 0) {
        val newPos = Pair(src.first, src.second + distanceX)
        idxList[convertMatrixToIdx(newPos)] = theValue
    }
}

@Composable
fun WithTheBoxes(
    selectedIdx: Int,
    idxList: SnapshotStateList<Int>,
    setSuccess: (Boolean) -> Unit,
    setIdx: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp)
    ) {
        itemsIndexed(idxList) { idx, item ->
            if (item == 8) {
            } else {
                val ctx = LocalContext.current.applicationContext
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .border(
                            border = BorderStroke(2.dp, Color.DarkGray),
                            shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)
                        )
                        .background(
                            color = if (selectedIdx == item) Color.Cyan else Color.Cyan,
                            shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)
                        )
                        .padding(3.dp)
                        .size(100.dp, 100.dp)
                        .clickable {
                            setIdx(item)
                            onClickBox(idx, ctx, idxList, setSuccess)
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "${item + 1}",
                        fontSize = 40.sp,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}