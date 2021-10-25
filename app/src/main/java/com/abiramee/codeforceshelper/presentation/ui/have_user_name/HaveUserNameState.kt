package com.abiramee.codeforceshelper.presentation.ui.have_user_name

import com.abiramee.codeforceshelper.domain.model.CheckUser

data class HaveUserNameState (
    val isLoading: Boolean = false,
    val data: CheckUser? = null,
    val error: String = "",

)