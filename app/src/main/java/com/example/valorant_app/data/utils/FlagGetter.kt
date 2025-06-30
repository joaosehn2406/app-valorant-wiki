package com.example.valorant_app.data.utils

import com.example.valorant_app.data.entities.AgentCard

data class AgentCountry(
    val countryName: String,
    val countryIso2: String
)


private val agentCountryMap: Map<String, AgentCountry> = mapOf(
    "Brimstone" to AgentCountry("United States", "us"),
    "Viper"      to AgentCountry("United States", "us"),
    "Gekko"      to AgentCountry("United States", "us"),
    "Reyna"      to AgentCountry("Mexico",        "mx"),
    "Raze"       to AgentCountry("Brazil",        "br"),
    "Astra"      to AgentCountry("Ghana",         "gh"),
    "Cypher"     to AgentCountry("Morocco",       "ma"),
    "Chamber"    to AgentCountry("France",        "fr"),
    "Phoenix"    to AgentCountry("United Kingdom","gb"),
    "Deadlock"   to AgentCountry("Norway",        "no"),
    "Breach"     to AgentCountry("Sweden",        "se"),
    "Killjoy"    to AgentCountry("Germany",       "de"),
    "Sova"       to AgentCountry("Russia",        "ru"),
    "Fade"       to AgentCountry("Turkey",        "tr"),
    "Harbor"     to AgentCountry("India",         "in"),
    "Sage"       to AgentCountry("China",         "cn"),
    "Jett"       to AgentCountry("South Korea",   "kr"),
    "Skye"       to AgentCountry("Australia",     "au"),
    "Yoru"       to AgentCountry("Japan",         "jp"),
    "Neon"       to AgentCountry("Philippines",   "ph"),
    "KAY/O"      to AgentCountry("Unknown",       "un"),
    "Omen"       to AgentCountry("Unknown",       "un")
)

fun String.toAgentCountry(): AgentCountry? =
    agentCountryMap[this]

fun AgentCard.getCountryInfo(): AgentCountry? =
    this.displayName.toAgentCountry()
