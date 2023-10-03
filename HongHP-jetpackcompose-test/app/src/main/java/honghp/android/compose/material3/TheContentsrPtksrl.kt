package honghp.android.compose.material3

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rPtksrl() {
    val context = LocalContext.current
    var text by remember { mutableStateOf("2") }
    var text2 by remember { mutableStateOf("5") }
    Log.d("홍", text.toString())
    Log.d("홍", text2.toString())
    var value = remember { text.toInt() * text2.toInt() }
    var Rvalue = remember { mutableStateOf(value.toString()) }
    Log.d("홍", value.toString())
    Log.d("홍", "E")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "계산기",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )
    }
    Column {
        Row {
            OutlinedTextField(
                value = text,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier.width(200.dp),
                onValueChange = { text = it.toString() }//,label = { Text("Label") }
            )
            TextField(
                value = text2,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { text2 = it.toString() }//,label = { Text("Label") }
//                ,color= Color.Black
            )
        }
        Row {
            Button(onClick = {
                Toast.makeText(context,"곱하기 버튼 클릭됨",Toast.LENGTH_SHORT)
                value = text.toInt() * text2.toInt()
                Rvalue.value = value.toString()
                Log.d("홍", value.toString())
            }) {
                Text(text = "곱하기")
            }
            Button(onClick = {
                value = text.toInt() / text2.toInt()
                Rvalue.value = value.toString()
                Log.d("홍", value.toString())
            }) {
                Text(text = "나누기")
            }
            Button(onClick = {
                value = text.toInt() + text2.toInt()
                Rvalue.value = value.toString()
                Log.d("홍", value.toString())
            }) {
                Text(text = "더하기")
            }
            Button(onClick = {
                value = text.toInt() - text2.toInt()
                Rvalue.value = value.toString()
                Log.d("홍", value.toString())
            }) {
                Text(text = "빼기")
            }
        }

        Text(text = Rvalue.value, fontSize = 20.sp, color = Color.Blue,
            textAlign = TextAlign.Center)

    }
}