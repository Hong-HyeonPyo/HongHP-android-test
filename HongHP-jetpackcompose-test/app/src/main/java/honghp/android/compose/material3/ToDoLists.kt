package honghp.android.compose.material3

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoList() {
//    val context = LocalContext.current
    var shouldShowDialog by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val lst = rememberSaveable { mutableStateOf(listOf<String>()) }

//    lst.clear()
    AlertDialogSample2(shouldShowDialog) {
        shouldShowDialog = false
        lst.value = listOf()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
//            .wrapContentSize(Alignment.Center),
    ) {
        Row {
            OutlinedTextField(
                value =text,
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier.width(230.dp),
                onValueChange = { text = it.toString() },//,label = { Text("Label") }
                maxLines = 2,
                placeholder = { Text("입력하세요.", color=Color.LightGray) },
            )
            Button(onClick = {
                lst.value = listOf( *lst.value.toTypedArray() , text  )
            }) {
                Text(text = "추가")
            }
            Button(onClick = {
                shouldShowDialog = true
//                lst.value= listOf<String>()
            }) {
                Text(text = "초기화")
            }
        }
        LazyColumn(
        ) {
            items(lst.value.size) { idx ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${lst.value[idx]}", modifier = Modifier.width(300.dp))
                    Button(onClick = {
                        val tmp = lst.value.toMutableList()
                        tmp.removeAt(idx)
                        lst.value = tmp
//                        lst.removeAt(item)
                    }) {
                        Text(text = "삭제")
                    }
                }
            }
        }
    }
}
@Composable
fun AlertDialogSample2(
    shouldShowDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (shouldShowDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "#경고")
            },
            text = {
                Text(text = "기록이 초기화 되었습니다.")
            },
            confirmButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text(text = "확인")
                }
            }
        )
    }
}