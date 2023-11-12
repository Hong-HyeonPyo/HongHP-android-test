package honghp.android.compose.material3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

//========================================
@Composable
fun TstScrnMinChild(idx: Int, funchn: () -> Unit) {
    Row {
        Card {
            Text(text = "${idx}")
            Button(onClick = {
                funchn()
            }) {
                Text(text = "증가")
            }
        }
    }
}

@Composable
fun TstScrnMinChild2(idx: Int, funchn: () -> Unit) {
    Row {
        Card {
            Text(text = "${idx}")
            Button(onClick = {
                funchn()
            }) {
                Text(text = "증가")
            }
        }
    }
}

@Composable
fun TstScrnMinChild3(idx: Int, funchn: () -> Unit) {
    Row {
        Card {
            Text(text = "${idx}")
            Button(onClick = {
                funchn()
            }) {
                Text(text = "증가")
            }
        }
    }
}

@Composable
fun TstScrnMinChild4(idx: Int, funchn: () -> Unit) {
    Row {
        Card {
            Text(text = "${idx}")
            Button(onClick = {
                funchn()
            }) {
                Text(text = "증가")
            }
        }
    }
}

@Composable
fun TstScrn2() {
    var indx by remember { mutableStateOf(0) }
    var indx4 by remember { mutableStateOf(0) }
    fun indxPls() {
        indx++
    }

    var indx2 by remember { mutableStateOf(0) }
    fun indx2Pls() {
        indx2++
    }

    var indx3 by remember { mutableStateOf(0) }
    fun indx3Pls() {
        indx3++
    }

    fun indx4Pls() {
        indx4++
    }
    Row {
        Column {
            Row {
                TstScrnMinChild(indx, { indx2Pls() })
                TstScrnMinChild2(indx2, { indx3Pls() })
            }
            Row {
                TstScrnMinChild3(indx3, { indx4Pls() })
                TstScrnMinChild4(indx4, { indxPls() })
            }
        }
    }
}

// 합계구하기
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TstScreanChild(funchn: (idx: Long) -> Unit) {
    var num by remember { mutableLongStateOf(1) }
    Column {
        Card {
            Row {
                OutlinedTextField(
                    value = num.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(color = Color.Black),
                    modifier = Modifier.width(230.dp),
                    onValueChange = { num = if (it.trim() == "") 0 else it.toLong() },
                    maxLines = 1
                )
                Button(onClick = {
                    funchn(num)
                }, Modifier.size(60.dp, 60.dp)) {
                    Text(text = "증가")
                }
            }
        }
    }
}

@Composable
fun TstScrean() {
    var indx by remember { mutableLongStateOf(1) }
//    var indxLst by remember { mutableStateListOf() }//에러남

    val indxLst = remember { mutableStateListOf(0) }

    if (indxLst.size < 3) {
        for (i in 1..5) {
            indxLst.add(0)
        }
    }
    fun indxPls(plsIdx: Long) {
        indx=indx+plsIdx
    }
    Row {
        Column {
            LazyColumn {
                items(indxLst) {
                    TstScreanChild(funchn = { num -> indxPls(num) })
                }
            }
            Column {
                Text(text = Long.MAX_VALUE.toString())
                Text(text = "${indx}")
            }
        }
    }
}