package honghp.android.compose.material3

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewGame() {

    @Composable
    fun Aame(
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
}

