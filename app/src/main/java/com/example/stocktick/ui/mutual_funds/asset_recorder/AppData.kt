package com.example.stocktick.ui.mutual_funds.asset_recorder

import android.text.InputType
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.DataListCardModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.EditDetailsModel

/**
 * This file will have all the static data in asset recorder section*/
object AppData {
    //This integers will be used to reference the corresponding asset record
    const val FIXED_DEPOSIT = 2001
    const val BANK_ACCOUNT = 2002
    const val MUTUAL_FUND = 2003
    const val LOCKER = 2004
    const val INDIAN_EQUITY = 2005
    const val US_EQUITY = 2006
    const val CRYPTO = 2007
    const val BONDS = 2008
    const val OFF_MARKET = 2009
    const val AIF = 2010
    const val PMS = 2011
    const val EPF = 2012
    const val PPF = 2013
    const val REAL_ESTATE = 2014
    const val NPS_TIER_1 = 2015
    const val OTHERS = 2016
    const val LIFE_INSURANCE = 2017
    const val HEALTH_INSURANCE = 2018
    const val MOTOR_INSURANCE = 2019
    const val LOAN = 2020

    /*
    The DataListing page's adapter needs to update the layout with values
    for example, the title label, particular 1 (say broker name)
    So, I think a map would be ideal for this for easy reference
    */

    val dataListCardModels: Map<Int, DataListCardModel> = mapOf(
        Pair(
            FIXED_DEPOSIT,
            DataListCardModel(
                title_label = "Amount Invested",
                particular1_label = "Bank Name      : ",
                particular2_label = "Maturity Date  : "
            )
        ),
        Pair(
            BANK_ACCOUNT,
            DataListCardModel(
                title_label = "Bank Name",
                particular1_label = "Account Type               : ",
                particular2_label = "Last 4 digits of bank acc. : "
            )
        )

    )

    fun getEditDetailsTemplate(category: Int): List<EditDetailsModel> = when (category) {
        FIXED_DEPOSIT -> {
            listOf(
                EditDetailsModel(
                    label = "Bank Name",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "Branch Name",
                    hint = "YOUR BRANCH NAME"
                ), EditDetailsModel(
                    label = "Deposit Holder's Name",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "Amount Invested",
                    hint = "YOUR BANK NAME",
                    inputType = InputType.TYPE_CLASS_NUMBER
                ), EditDetailsModel(
                    label = "Investment Date",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "Investment Duration",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "Rate of Interest",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "Maturity Date",
                    hint = "YOUR BANK NAME",
                    inputType = InputType.TYPE_DATETIME_VARIATION_DATE
                ), EditDetailsModel(
                    label = "Maturity Amount",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "Nominee Name",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "Relationship",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "% Allocation",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "Nominee Name",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "Relationship",
                    hint = "YOUR BANK NAME"
                ), EditDetailsModel(
                    label = "% Allocation ",
                    hint = "YOUR BANK NAME"
                )
            )
        }
        BANK_ACCOUNT -> {
            listOf(
                EditDetailsModel(
                    label = "Bank Name",
                    hint = "BANK NAME "
                ),
                EditDetailsModel(
                    label = "Branch Address",
                    hint = "BRANCH ADDRESS "
                ),
                EditDetailsModel(
                    label = "Account Holder Name",
                    hint = "ACCOUNT HOLDER NAME "
                ),
                EditDetailsModel(
                    label = "Last 4 Digit of Account Number",
                    hint = "LAST 4 DIGIT OF ACCOUNT NUMBER ",
                    inputType = InputType.TYPE_CLASS_NUMBER
                ),
                EditDetailsModel(
                    label = "Account Type",
                    hint = "ACCOUNT TYPE "
                ),
                EditDetailsModel(
                    label = "Nominee Name",
                    hint = "NOMINEE NAME "
                ),
                EditDetailsModel(
                    label = "Relationship",
                    hint = "RELATIONSHIP "
                ),
                EditDetailsModel(
                    label = "Allocation Percent",
                    hint = "ALLOCATION PERCENT IN %",
                    inputType = InputType.TYPE_CLASS_NUMBER
                ),
                EditDetailsModel(
                    label = "Nominee Name2 ",
                    hint = "NOMINEE NAME2  "
                ),
                EditDetailsModel(
                    label = "Relationship2",
                    hint = "RELATIONSHIP2 "
                ),
                EditDetailsModel(
                    label = "Allocation Percent2",
                    hint = "ALLOCATION PERCENT2 IN %",
                    inputType = InputType.TYPE_CLASS_NUMBER
                ),
            )

        }
        else -> listOf()
    }


}