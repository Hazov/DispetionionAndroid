package com.example.data.model.auth.permissions

import com.example.domain.model.auth.permissions.PermissionsResponse

class PermissionsResponse(val permissions: List<String>) {
    fun toDomainPermissions(): PermissionsResponse {
        return PermissionsResponse(permissions)
    }
}