package com.bangkit.capstone.beangreader.presentation.screen.onboarding.model

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.bangkit.capstone.beangreader.R

data class OnBoardingItem(
    @RawRes
    val resId: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
)

object OnBoardingData {

    val onBoardingItems = listOf(
        OnBoardingItem(
            resId = R.raw.welcome,
            title = R.string.title_board_1,
            description = R.string.description_board_1
        ),
        OnBoardingItem(
            resId = R.raw.detection,
            title = R.string.title_board_2,
            description = R.string.description_board_2
        ),
        OnBoardingItem(
            resId = R.raw.quality_coffee,
            title = R.string.title_board_3,
            description = R.string.description_board_3
        ),
    )
}