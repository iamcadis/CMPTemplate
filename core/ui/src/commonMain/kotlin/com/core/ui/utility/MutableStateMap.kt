package com.core.ui.utility

import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.snapshots.SnapshotStateMap

@Suppress("UNCHECKED_CAST")
val TopAppBarStateMapSaver: Saver<SnapshotStateMap<String, TopAppBarState>, Any> =
    listSaver(
        save = { map ->
            val stateSaver = TopAppBarState.Saver as Saver<TopAppBarState, List<Float>>

            map.toList().flatMap { (key, state) ->
                val savedState = with(stateSaver) { save(state) }
                listOf(key, savedState)
            }
        },
        restore = { list ->
            val stateSaver = TopAppBarState.Saver as Saver<TopAppBarState, List<Float>>

            val map = list.chunked(2).associate { chunk ->
                val key = chunk[0] as String
                val savedState = chunk[1] as List<Float>
                val state = stateSaver.restore(savedState)
                key to state!!
            }.toMap()

            SnapshotStateMap<String, TopAppBarState>().apply {
                putAll(map)
            }
        }
    )