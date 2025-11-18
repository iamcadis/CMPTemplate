package com.core.ui.utility

import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.mapSaver
import kotlin.collections.ArrayList

@Suppress("UNCHECKED_CAST")
val MapOfTopAppBarStateSaver = mapSaver(
    save = { originalMap: Map<String, TopAppBarState> ->
        originalMap.mapValues { entry ->
            val state = entry.value
            arrayListOf(
                state.heightOffsetLimit,
                state.heightOffset,
                state.contentOffset
            )
        }
    },
    restore = { savedMap: Map<String, Any?> ->
        val restoredMap = mutableStateMapOf<String, TopAppBarState>()

        savedMap.forEach { (key, value) ->
            val list = value as? ArrayList<Float>
            if (list != null && list.size == 3) {
                restoredMap[key] = TopAppBarState(
                    initialHeightOffsetLimit = list[0],
                    initialHeightOffset = list[1],
                    initialContentOffset = list[2]
                )
            }
        }
        restoredMap
    }
)