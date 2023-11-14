package honghp.android.compose.material3

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ContentsMapper(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object HomeScreen : ContentsMapper(
        route = "home_screen",
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Weather : ContentsMapper(
        route = "favourite_screen",
        title = "날씨",
        icon = Icons.Default.DateRange

    )

    object Notification : ContentsMapper(
        route = "notification_screen",
        title = "계산기",
        icon = Icons.Default.Edit
    )

    object Screen2 : ContentsMapper(
        route = "cat_screen",
        title = "고양이",
        icon = Icons.Outlined.Face
    )

    object toDoList : ContentsMapper(
        route = "toDoList",
        title = "ToDo",
        icon = Icons.Outlined.List
    )

    object theGame : ContentsMapper(
        route = "theGame",
        title = "Game",
        icon =   Icons.Default.Build,
    )

    object thetest2 : ContentsMapper(
        route = "TstScrn2",
        title = "테스트",
        icon = Icons.Outlined.Create
    )

    object thetest : ContentsMapper(
        route = "TstScrn",
        title = "테스트",
        icon = Icons.Outlined.Add
    )
}