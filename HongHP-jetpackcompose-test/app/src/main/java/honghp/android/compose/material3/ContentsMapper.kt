package honghp.android.compose.material3

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
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
    object Favourite : ContentsMapper(
        route = "favourite_screen",
        title = "Favorite",
        icon = Icons.Outlined.Favorite
    )
    object Notification : ContentsMapper(
        route = "notification_screen",
        title = "Notification",
        icon = Icons.Outlined.ExitToApp
    )
    object Screen2 : ContentsMapper(
        route = "cat_screen",
        title = "고양이",
        icon = Icons.Outlined.AccountCircle
    )
}