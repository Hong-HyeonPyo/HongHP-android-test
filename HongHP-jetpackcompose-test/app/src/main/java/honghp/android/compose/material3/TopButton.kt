package honghp.android.compose.material3

import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavHostController) {
    val context = LocalContext.current
    var shouldShowDialog by remember {
        mutableStateOf(false)
    }
    CenterAlignedTopAppBar(
        title = {
            Text(
                "홍현표",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
//            IconButton(onClick = { /* doSomething() */ }) {
//                Icon(
//                    imageVector = Icons.Filled.Menu,
//                    contentDescription = "Localized description"
//                )
//            }
            AlertDialogSample(shouldShowDialog) {
                shouldShowDialog = false
//                Toast.makeText(context, "닫힘", Toast.LENGTH_SHORT).show()
            }

            TheDropDownMenu(navController)
        },
        actions = {
            IconButton(onClick = {
                shouldShowDialog = true
            }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Share",
                )
            }
        },

        )
}
private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
@Composable
fun AlertDialogSample(
    shouldShowDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (shouldShowDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "이 앱에 관해서")
            },
            text = {
                Text(text = "이 앱은 미완성이며 여러가지 기능을 추가할 예정입니다")
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

val screens = listOf(
    ContentsMapper.HomeScreen, ContentsMapper.Weather, ContentsMapper.Notification,
    ContentsMapper.Screen2,ContentsMapper.toDoList,ContentsMapper.theGame
)

@Composable
fun TheDropDownMenu(navController: NavHostController) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = !expanded }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More"
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        screens.forEach { screen ->
            DropdownMenuItem(
                text = { Text(screen.title.toString()) },
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}