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
    val context = LocalContext.current
    var shouldShowDialog by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("글자를 적으세요") }
    var horScrol = rememberScrollState()

    var lst = remember { mutableStateListOf(String()) }
    lst.clear()
    AlertDialogSample2(shouldShowDialog) {
        shouldShowDialog = false
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
//            .wrapContentSize(Alignment.Center),

    ) {
        Row {
            OutlinedTextField(
                value = text,
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier.width(230.dp),
                onValueChange = { text = it.toString() },//,label = { Text("Label") }
                maxLines = 2
            )
            Button(onClick = {
                lst.add("$text")
            }) {
                Text(text = "추가")
            }
            Button(onClick = {
                shouldShowDialog = true
                lst.clear()
            }) {
                Text(text = "초기화")
            }
        }
        LazyColumn (
            Modifier.horizontalScroll(horScrol)
        ){

            items(lst.size) { item ->
                Row {
                    Text(text = "${lst[item]}", modifier = Modifier.width(300.dp))
                    Button(onClick = {
                        lst.removeAt(item)
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
                Text(text = "모든 데이터 삭제됨.")
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