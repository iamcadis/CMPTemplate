package com.core.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

private const val DEFAULT_DURATION_MILLIS = 300

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(
    towards: AnimatedContentTransitionScope.SlideDirection
) = fadeIn(
    animationSpec = tween(durationMillis = DEFAULT_DURATION_MILLIS, easing = LinearEasing)
) + slideIntoContainer(
    towards = towards,
    animationSpec = tween(durationMillis = DEFAULT_DURATION_MILLIS, easing = LinearEasing),
    initialOffset = { it }
)

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(
    towards: AnimatedContentTransitionScope.SlideDirection
) = fadeOut(
    animationSpec = tween(durationMillis = 300, easing = LinearEasing)
) + slideOutOfContainer(
    towards = towards,
    animationSpec = tween(durationMillis = DEFAULT_DURATION_MILLIS, easing = LinearEasing),
    targetOffset = { -it }
)
