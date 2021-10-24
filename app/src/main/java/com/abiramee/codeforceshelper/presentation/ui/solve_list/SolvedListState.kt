package com.abiramee.codeforceshelper.presentation.ui.solve_list

import com.abiramee.codeforceshelper.domain.model.Status

data class SolvedListState(
    val isLoading: Boolean = false,
    val data : Status? = null,
    val error: String = ""
)