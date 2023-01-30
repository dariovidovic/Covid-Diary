package com.example.covid_journal

data class User(val email: String? = null, val password: String? = null, val firstSubmit: Boolean, val dayCounter: Int? = null) {
    constructor() : this("","",false, 0)

}
