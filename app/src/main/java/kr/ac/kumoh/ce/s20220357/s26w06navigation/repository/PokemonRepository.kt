package kr.ac.kumoh.ce.s20220357.s26w06navigation.repository

import kr.ac.kumoh.ce.s20220357.s26w06navigation.Pokemon

interface PokemonRepository {
    suspend fun getAllPokemon(): List<Pokemon>
    suspend fun getPokemonById(id: Int): Pokemon?
}