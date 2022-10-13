package com.ihebchiha.tech_test.data.remote

import com.ihebchiha.tech_test.domain.model.Permissions

data class PermissionsDto(
    val admin: Boolean,
    val maintain: Boolean,
    val push: Boolean,
    val triage: Boolean,
    val pull: Boolean
){
    fun toPermissions(): Permissions{
        return Permissions(
            admin, maintain, push, triage, pull
        )
    }
}
