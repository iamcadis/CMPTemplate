package com.core.viewmodel

interface State {
    val pageLoading: Boolean
        get() = false
}

interface Action

interface Effect