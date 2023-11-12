package honghp.android.compose.material3

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import honghp.android.compose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatScreen() {
    var lst = mutableListOf<String>("1", "2", "3")
    var catp = painterResource(R.drawable.cat)
    var MainHorScrol = rememberScrollState()
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
    var shouldShowDialog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Column(Modifier.horizontalScroll(MainHorScrol)) {
        LazyColumn( ) {
            items(lst.size) {
                Column {
                    Image(painter = catp, contentDescription = "3")
                    Button(onClick = {
                        Toast.makeText(context, "버튼 클릭됨", Toast.LENGTH_SHORT).show()
                    }) {
                        Text(text = "버튼")
                    }
                    Text(text = "에엒따")
                }
            }
            item{
                Text(text = "에엒따")
                Text(text = "에엒따")
                Button(onClick = {

                }) {
                    Text(text ="함정")
                }
                Text(text = "에엒따")
            }

        }
    }
}
