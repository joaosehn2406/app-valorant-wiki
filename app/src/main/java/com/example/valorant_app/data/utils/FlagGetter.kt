package com.example.valorant_app.data.utils

import com.example.valorant_app.data.entities.card.AgentCard
import java.util.Locale
import java.util.Locale.getDefault

data class AgentCountry(
    val countryName: String,
    val countryIso2: String
)


private val agentCountryMap: Map<String, AgentCountry> = mapOf(
    "Brimstone" to AgentCountry("Estados Unidos", "us"),
    "Viper"      to AgentCountry("Estados Unidos", "us"),
    "Gekko"      to AgentCountry("Estados Unidos", "us"),
    "Reyna"      to AgentCountry("Mexico",        "mx"),
    "Raze"       to AgentCountry("Brasil",        "br"),
    "Astra"      to AgentCountry("Ghana",         "gh"),
    "Cypher"     to AgentCountry("Morrocos",       "ma"),
    "Chamber"    to AgentCountry("Franca",        "fr"),
    "Phoenix"    to AgentCountry("Reino Unido","gb"),
    "Deadlock"   to AgentCountry("Noruega",        "no"),
    "Breach"     to AgentCountry("Suecia",        "se"),
    "Killjoy"    to AgentCountry("Alemanha",       "de"),
    "Sova"       to AgentCountry("Russia",        "ru"),
    "Fade"       to AgentCountry("Turquia",        "tr"),
    "Harbor"     to AgentCountry("India",         "in"),
    "Sage"       to AgentCountry("China",         "cn"),
    "Jett"       to AgentCountry("Coreia do Sul",   "kr"),
    "Skye"       to AgentCountry("Australia",     "au"),
    "Yoru"       to AgentCountry("Japao",         "jp"),
    "Neon"       to AgentCountry("Filipinas",   "ph"),
    "KAY/O"      to AgentCountry("Desconhecido",       "un"),
    "Omen"       to AgentCountry("Unknown",       "un")
)

fun String.toAgentCountry(): AgentCountry? {
    this.trim()
    return agentCountryMap[this.trim()]
}

fun AgentCard.getCountryInfo(): AgentCountry? =
    this.displayName.toAgentCountry()
