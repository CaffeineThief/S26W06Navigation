package kr.ac.kumoh.ce.s20220357.s26w06navigation.model

data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    val info: String,
    val image: String,
    val backimage: String? = null,
    val hp: Int? = null,
    )
