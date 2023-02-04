package com.example.aston_final.utils

import android.content.Context

class ResourceProvider(val context: Context) {
    fun getString(str: String): String {
        return context.getString(
            context.resources.getIdentifier(
                str,
                "string",
                context.packageName
            )
        )
    }
}