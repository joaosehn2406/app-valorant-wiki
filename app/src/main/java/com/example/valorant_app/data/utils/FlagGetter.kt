package com.example.valorant_app.data.utils

import com.example.valorant_app.data.entities.card.AgentCard

data class AgentCountry(
    val countryName: String,
    val countryIso2: String
)


private val agentCountryMap: Map<String, AgentCountry> = mapOf(
    "Brimstone" to AgentCountry("USA", "us"),
    "Viper"      to AgentCountry("USA", "us"),
    "Gekko"      to AgentCountry("USA", "us"),
    "Reyna"      to AgentCountry("MEX",        "mx"),
    "Raze"       to AgentCountry("BRA",        "br"),
    "Astra"      to AgentCountry("GHA",         "gh"),
    "Cypher"     to AgentCountry("MAR",       "ma"),
    "Chamber"    to AgentCountry("FRA",        "fr"),
    "Phoenix"    to AgentCountry("GBR","gb"),
    "Deadlock"   to AgentCountry("NOR",        "no"),
    "Breach"     to AgentCountry("SWE",        "se"),
    "Killjoy"    to AgentCountry("GER",       "de"),
    "Sova"       to AgentCountry("RUS",        "ru"),
    "Fade"       to AgentCountry("TUR",        "tr"),
    "Harbor"     to AgentCountry("IND",         "in"),
    "Sage"       to AgentCountry("CHN",         "cn"),
    "Jett"       to AgentCountry("KOR",   "kr"),
    "Skye"       to AgentCountry("AUS",     "au"),
    "Yoru"       to AgentCountry("JPN",         "jp"),
    "Neon"       to AgentCountry("PHI",   "ph"),
    "KAY/O"      to AgentCountry("N/A",       "un"),
    "Omen"       to AgentCountry("N/A",       "un")
)

fun String.toAgentCountry(): AgentCountry? {
    this.trim()
    return agentCountryMap[this.trim()]
}

fun AgentCard.getCountryInfo(): AgentCountry? =
    this.displayName.toAgentCountry()
