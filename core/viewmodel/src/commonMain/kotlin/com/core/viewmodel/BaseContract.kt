package com.core.viewmodel

interface ViewAction

interface ViewEffect

interface ViewState {
    val pageLoading: Boolean
        get() = false
}