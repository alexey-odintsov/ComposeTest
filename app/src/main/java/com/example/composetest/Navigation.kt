package com.example.composetest

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.composetest.ui.ChatMessagesScreen
import com.example.composetest.ui.chatlist.ChatListScreen

object Screens {
    const val CHAT_LIST = "chat_list"
    const val CHAT_MESSAGES = "chat_messages"
}

@Composable
fun NavGraph(startDestination: String = Screens.CHAT_LIST) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.CHAT_LIST) {
            ChatListScreen(navController)
        }

        composable(
            "${Screens.CHAT_MESSAGES}/{chatId}",
            arguments = listOf(navArgument("chatId") { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            ChatMessagesScreen(navController,
                chatId = arguments.getLong("chatId"),
            )
        }
    }

}