package honghp.android.compose.material3

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
    var operator by remember{ mutableStateOf(" + ") }
    var value = remember { text.toDouble() * text2.toDouble() }
    var rvalue by remember { mutableStateOf(value.toString()) }
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier.width(130.dp),
                onValueChange = { text = if (it.trim() == "") "0" else it }//,label = { Text("Label") }
            )
            Text(text=operator, fontSize = 30.sp)
            OutlinedTextField(
                value = text2,
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier.width(130.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { text2 = if (it.trim() == "") "0" else it },
                //,label = { Text("Label") } ,color= Color.Black
            )
            Text(text=" = ")
            Text(text=rvalue, fontSize = 20.sp, color = Color.Blue,)
        }
        Row (verticalAlignment = Alignment.CenterVertically){
            Button(onClick = {
//                Toast.makeText(context, "곱하기 버튼 클릭됨", Toast.LENGTH_SHORT)
                value = text.toDouble() * text2.toDouble()
                rvalue = value.toString()
                operator = " * "
                Log.d("홍", value.toString())
            }, shape = RectangleShape,) {
                Text(text = "곱하기")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = {
                Log.d("홍", "==== ${text2.toDouble()} , ${text2.toDouble()==0.0} " )
                if (text2!="0" && text2.toDouble()!=0.0){
                    value = text.toDouble() / text2.toDouble()
                }else {
                    value=0.0
                    Toast.makeText(context,"0으로는 나눌수 없습니다.", Toast.LENGTH_SHORT).show()
                }
                rvalue = value.toString()
                operator = " / "
                Log.d("홍", value.toString())
            }, shape = RectangleShape,) {
                Text(text = "나누기")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = {
                value = text.toDouble() + text2.toDouble()
                rvalue = value.toString()
                operator = " + "
                Log.d("홍", value.toString())
            }, shape = RectangleShape,) {
                Text(text = "더하기")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = {
                value = text.toDouble() - text2.toDouble()
                rvalue = value.toString()
                operator = " - "
                Log.d("홍", value.toString())
            }, shape = RectangleShape,) {
                Text(text = "빼기")
            }
        }
//        Text(
//            text = rvalue, fontSize = 20.sp, color = Color.Blue,
//            textAlign = TextAlign.Center
//        )
    }
}