package com.ihebchiha.tech_test.domain.model

data class Permissions (
    val admin: Boolean,
    val maintain: Boolean,
    val push: Boolean,
    val triage: Boolean,
    val pull: Boolean
)
