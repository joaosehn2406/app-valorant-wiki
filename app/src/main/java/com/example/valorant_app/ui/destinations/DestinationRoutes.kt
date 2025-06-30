package com.example.valorant_app.ui.destinations

import kotlinx.serialization.Serializable

interface Route {
    val route: String
}

@Serializable
object HomePageRoute : Route {
    override val route: String
        get() = "HomePageRoute"
}


@Serializable
object WeaponRoute : Route {
    override val route: String
        get() = "WeaponRoute"
}

@Serializable
object AgentRoute : Route {
    override val route: String
        get() = "AgentRoute"
}

@Serializable
object InitialPageRoute : Route {
    override val route: String
        get() = "InitialPageRoute"
}
