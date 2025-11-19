package com.example.comp4521_holdthatthought.navigation

sealed class Screen(val route: String, val title: String) {
    data object Home : Screen("home", "Hold That Thought")

    // Placeholder routes named by Figma node ids
    data object Node4_4573 : Screen("node_4_4573", "Screen 4:4573")
    data object Node4_4106 : Screen("node_4_4106", "Screen 4:4106")
    data object Node4_3891 : Screen("node_4_3891", "Screen 4:3891")
    data object Node13_1155 : Screen("node_13_1155", "Screen 13:1155")
    data object Node5_812 : Screen("node_5_812", "Screen 5:812")
    data object Node6_947 : Screen("node_6_947", "Screen 6:947")
    data object Node8_1053 : Screen("node_8_1053", "Screen 8:1053")
    data object Node9_1287 : Screen("node_9_1287", "Screen 9:1287")
    data object Node9_1333 : Screen("node_9_1333", "Screen 9:1333")
    data object Node8_968 : Screen("node_8_968", "Screen 8:968")

    // Additional flows
    data object AIQuestion : Screen("ai_question", "Question")
    data object AIResult : Screen("ai_result", "Result")
    data object Register : Screen("register", "Register")
    data object ShareReceiver : Screen("share_receiver", "Save Article")

    companion object {
        fun fromRoute(route: String?): Screen {
            return when (route) {
                Home.route -> Home
                Node4_4573.route -> Node4_4573
                Node4_4106.route -> Node4_4106
                Node4_3891.route -> Node4_3891
                Node13_1155.route -> Node13_1155
                Node5_812.route -> Node5_812
                Node6_947.route -> Node6_947
                Node8_1053.route -> Node8_1053
                Node9_1287.route -> Node9_1287
                Node9_1333.route -> Node9_1333
                Node8_968.route -> Node8_968
                AIQuestion.route -> AIQuestion
                AIResult.route -> AIResult
                Register.route -> Register
                ShareReceiver.route -> ShareReceiver
                else -> Home
            }
        }
    }
}
