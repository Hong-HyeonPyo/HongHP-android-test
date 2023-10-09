package honghp.android.compose.material3

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import honghp.android.compose.R

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
                    Toast.makeText(context, "버튼 클릭됨", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "버튼")
                }
                Text(text = "에엒따")
            }
        }
    }
}
