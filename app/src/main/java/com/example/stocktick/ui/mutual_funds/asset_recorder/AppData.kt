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

    fun getDataListCardModels(category: Int) : DataListCardModel = when(category) {
        FIXED_DEPOSIT -> {
            DataListCardModel(
                title_label = "Amount Invested",
                particular1_label = "Bank Name      : ",
                particular2_label = "Maturity Date  : "
            )
        }
        BANK_ACCOUNT -> {
            DataListCardModel(
                title_label =       "Bank Name  ",
                particular1_label = "Branch Address : ",
                particular2_label = "Account Number : (Last 4 digit) "
            )
        }
        MUTUAL_FUND -> {
            DataListCardModel(
                title_label       = "Purchase Amount",
                particular1_label = "Schema Name     : ",
                particular2_label = "Return Absolute : "
            )
        }
        LOCKER -> {
            DataListCardModel(
                title_label       = "Bank Name",
                particular1_label = "Locker Number :",
                particular2_label = "Bank Address  :"
            )
        }
        INDIAN_EQUITY -> {
            DataListCardModel(
                title_label       = "Purchase Price",
                particular1_label = "Instrument Name :",
                particular2_label = "Broker Name     :"
            )
        }
        US_EQUITY -> {
            DataListCardModel(
                title_label       = "Purchase Price",
                particular1_label = "Instrument Name :",
                particular2_label = "Broker Name     :"
            )
        }
        CRYPTO -> {
            DataListCardModel(
                title_label       = "Purchase Price",
                particular1_label = "Instrument Name :",
                particular2_label = "Broker Name     :"
            )
        }
        BONDS -> {
            DataListCardModel(
                title_label       = "Purchase Price",
                particular1_label = "Instrument Name :",
                particular2_label = "Broker Name     :"
            )
        }
        OFF_MARKET -> {
            DataListCardModel(
                title_label       = "Purchase Price",
                particular1_label = "Instrument Name :",
                particular2_label = "Broker Name     :"
            )
        }
        AIF -> {
            DataListCardModel(
                title_label       = "Invested Amount",
                particular1_label = "Aif Name :",
                particular2_label = "Amc Name :"
            )
        }
        PMS -> {
            DataListCardModel(
                title_label       = "Invested Amount",
                particular1_label = "Pms Name :",
                particular2_label = "Amc Name :"
            )
        }
        EPF -> {
            DataListCardModel(
                title_label       = "Balance",
                particular1_label = "Epf No       : (Last 4 digit) ",
                particular2_label = "Nominee Name :"
            )
        }
        PPF -> {
            DataListCardModel(
                title_label       = "Balance",
                particular1_label = "Bank Name      :",
                particular2_label = "Branch Address :"
            )
        }
        REAL_ESTATE -> {
            DataListCardModel(
                title_label       = "Invested Amount",
                particular1_label = "Property Name    :",
                particular2_label = "Location pin-code :"
            )
        }
        NPS_TIER_1 -> {
            DataListCardModel(
                title_label       = "Current Balance " ,
                particular1_label = "Bank Details :",
                particular2_label = "PRAN         : (last 4 digit) "
            )
        }
        OTHERS -> {
            DataListCardModel(
                title_label       = "Purchase Value",
                particular1_label = "Instrument Name : ",
                particular2_label = "No of Units     : "
            )
        }
        LIFE_INSURANCE -> {
            DataListCardModel(
                title_label       = "Sum Assured",
                particular1_label = "Plan Name      : ",
                particular2_label = "Premium Amount :"
            )
        }
        HEALTH_INSURANCE -> {
            DataListCardModel(
                title_label       = "Sum Assured",
                particular1_label = "Plan Name      : ",
                particular2_label = "Premium Amount :"
            )
        }
        MOTOR_INSURANCE -> {
            DataListCardModel(
                title_label       = "Plan Name",
                particular1_label = "Policy Number  : ",
                particular2_label = "Premium Amount : "
            )
        }
        LOAN -> {
            DataListCardModel(
                title_label       = "Loan Amount",
                particular1_label = "Bank Name  : ",
                particular2_label = "EMI Amount : "
            )
        }


        else -> {
            DataListCardModel(
                title_label = "No Title",
                particular1_label = "No Value Found",
                particular2_label =  "No Value Found"
            )
        }
    }




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
        BANK_ACCOUNT -> { listOf(EditDetailsModel(
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
        MUTUAL_FUND -> { listOf(EditDetailsModel(
            label = "Scheme Name",
            hint = "SCHEME NAME "
        ),
            EditDetailsModel(
                label = "Purchase Amount",
                hint = "PURCHASE AMOUNT IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Purchase NAV",
                hint = "PURCHASE NAV "
            ),
            EditDetailsModel(
                label = "Current NAV",
                hint = "CURRENT NAV "
            ),
            EditDetailsModel(
                label = "Current Amount",
                hint = "CURRENT AMOUNT IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Div Amount",
                hint = "DIV AMOUNT IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Gain Short Term",
                hint = "GAIN SHORT TERM "
            ),
            EditDetailsModel(
                label = "Gain Long Term",
                hint = "GAIN LONG TERM "
            ),
            EditDetailsModel(
                label = "Return Absolute",
                hint = "RETURN ABSOLUTE IN %",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Return CAGR",
                hint = "RETURN CAGR IN %",
                inputType = InputType.TYPE_CLASS_NUMBER
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
        LOCKER -> { listOf(EditDetailsModel(
            label = "Bank Name",
            hint = "BANK NAME "
        ),
            EditDetailsModel(
                label = "Bank Address",
                hint = "BANK ADDRESS "
            ),
            EditDetailsModel(
                label = "Locker Number",
                hint = "LOCKER NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Locker Holder Name",
                hint = "LOCKER HOLDER NAME "
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
        INDIAN_EQUITY -> { listOf(EditDetailsModel(
            label = "Broker Name",
            hint = "BROKER NAME "
        ),
            EditDetailsModel(
                label = "Account Number",
                hint = "ACCOUNT NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Instrument Name",
                hint = "INSTRUMENT NAME "
            ),
            EditDetailsModel(
                label = "Qty",
                hint = "QTY "
            ),
            EditDetailsModel(
                label = "Purchase Price",
                hint = "PURCHASE PRICE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Purchase Value",
                hint = "PURCHASE VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Market Price",
                hint = "MARKET PRICE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Market Value",
                hint = "MARKET VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
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
        US_EQUITY -> { listOf(EditDetailsModel(
            label = "Broker Name",
            hint = "BROKER NAME "
        ),
            EditDetailsModel(
                label = "Account Number",
                hint = "ACCOUNT NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Instrument Name",
                hint = "INSTRUMENT NAME "
            ),
            EditDetailsModel(
                label = "Qty",
                hint = "QTY "
            ),
            EditDetailsModel(
                label = "Purchase Price",
                hint = "PURCHASE PRICE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Purchase Value",
                hint = "PURCHASE VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Market Price",
                hint = "MARKET PRICE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Market Value",
                hint = "MARKET VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
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
        CRYPTO -> { listOf(EditDetailsModel(
            label = "Broker Name",
            hint = "BROKER NAME "
        ),
            EditDetailsModel(
                label = "Account Number",
                hint = "ACCOUNT NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Instrument Name",
                hint = "INSTRUMENT NAME "
            ),
            EditDetailsModel(
                label = "Qty",
                hint = "QTY "
            ),
            EditDetailsModel(
                label = "Purchase Price",
                hint = "PURCHASE PRICE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Purchase Value",
                hint = "PURCHASE VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Market Price",
                hint = "MARKET PRICE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Market Value",
                hint = "MARKET VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
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
        BONDS -> { listOf(EditDetailsModel(
            label = "Broker Name",
            hint = "BROKER NAME "
        ),
            EditDetailsModel(
                label = "Account Number",
                hint = "ACCOUNT NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Instrument Name",
                hint = "INSTRUMENT NAME "
            ),
            EditDetailsModel(
                label = "Qty",
                hint = "QTY "
            ),
            EditDetailsModel(
                label = "Purchase Price",
                hint = "PURCHASE PRICE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Purchase Value",
                hint = "PURCHASE VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Market Price",
                hint = "MARKET PRICE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Market Value",
                hint = "MARKET VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
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
        OFF_MARKET -> { listOf(EditDetailsModel(
            label = "Broker Name",
            hint = "BROKER NAME "
        ),
            EditDetailsModel(
                label = "Account Number",
                hint = "ACCOUNT NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Instrument Name",
                hint = "INSTRUMENT NAME "
            ),
            EditDetailsModel(
                label = "Qty",
                hint = "QTY "
            ),
            EditDetailsModel(
                label = "Purchase Price",
                hint = "PURCHASE PRICE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Purchase Value",
                hint = "PURCHASE VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Market Price",
                hint = "MARKET PRICE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Market Value",
                hint = "MARKET VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
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
        AIF -> { listOf(EditDetailsModel(
            label = "AMC NAME",
            hint = "AMC NAME "
        ),
            EditDetailsModel(
                label = "PMS/AIF NAME",
                hint = "PMS/AIF NAME "
            ),
            EditDetailsModel(
                label = "Invested Value",
                hint = "INVESTED VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Current Amount",
                hint = "CURRENT AMOUNT IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Account Number",
                hint = "ACCOUNT NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Invested Date",
                hint = "INVESTED DATE "
            ),
            EditDetailsModel(
                label = "Return Absolute",
                hint = "RETURN ABSOLUTE IN %",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Return CAGR",
                hint = "RETURN CAGR IN %",
                inputType = InputType.TYPE_CLASS_NUMBER
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
        PMS -> { listOf(EditDetailsModel(
            label = "AMC NAME",
            hint = "AMC NAME "
        ),
            EditDetailsModel(
                label = "PMS/AIF NAME",
                hint = "PMS/AIF NAME "
            ),
            EditDetailsModel(
                label = "Invested Value",
                hint = "INVESTED VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Current Amount",
                hint = "CURRENT AMOUNT IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Account Number",
                hint = "ACCOUNT NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Invested Date",
                hint = "INVESTED DATE "
            ),
            EditDetailsModel(
                label = "Return Absolute",
                hint = "RETURN ABSOLUTE IN %",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Return CAGR",
                hint = "RETURN CAGR IN %",
                inputType = InputType.TYPE_CLASS_NUMBER
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
        EPF -> { listOf(EditDetailsModel(
            label = "EPF No",
            hint = "EPF NO "
        ),
            EditDetailsModel(
                label = "Balance",
                hint = "BALANCE "
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
        PPF -> { listOf(EditDetailsModel(
            label = "Bank Name",
            hint = "BANK NAME "
        ),
            EditDetailsModel(
                label = "Branch Address",
                hint = "BRANCH ADDRESS "
            ),
            EditDetailsModel(
                label = "PPF Account No",
                hint = "PPF ACCOUNT NO "
            ),
            EditDetailsModel(
                label = "Balance",
                hint = "BALANCE "
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
        REAL_ESTATE -> { listOf(EditDetailsModel(
            label = "Property Name",
            hint = "PROPERTY NAME "
        ),
            EditDetailsModel(
                label = "Invested Value",
                hint = "INVESTED VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Investment Date",
                hint = "INVESTMENT DATE "
            ),
            EditDetailsModel(
                label = "Current Value",
                hint = "CURRENT VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Property Type",
                hint = "PROPERTY TYPE "
            ),
            EditDetailsModel(
                label = "Property Size",
                hint = "PROPERTY SIZE "
            ),
            EditDetailsModel(
                label = "Location - Pincode",
                hint = "LOCATION - PINCODE "
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
        NPS_TIER_1 -> { listOf(EditDetailsModel(
            label = "PRAN",
            hint = "PRAN "
        ),
            EditDetailsModel(
                label = " Current Balance",
                hint = " CURRENT BALANCE "
            ),
            EditDetailsModel(
                label = "Type of Account ",
                hint = "TYPE OF ACCOUNT  "
            ),
            EditDetailsModel(
                label = "Nominee Details",
                hint = "NOMINEE DETAILS "
            ),
            EditDetailsModel(
                label = "Bank Details",
                hint = "BANK DETAILS "
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
        OTHERS -> { listOf(EditDetailsModel(
            label = "Instrument Name",
            hint = "INSTRUMENT NAME "
        ),
            EditDetailsModel(
                label = "Purchase Value",
                hint = "PURCHASE VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Current Current",
                hint = "CURRENT CURRENT "
            ),
            EditDetailsModel(
                label = "Purchase Date",
                hint = "PURCHASE DATE "
            ),
            EditDetailsModel(
                label = "No of Units",
                hint = "NO OF UNITS "
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
        LIFE_INSURANCE -> { listOf(EditDetailsModel(
            label = "Plan Name",
            hint = "PLAN NAME "
        ),
            EditDetailsModel(
                label = "Policy Number",
                hint = "POLICY NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Policy Holder's Name",
                hint = "POLICY HOLDER'S NAME "
            ),
            EditDetailsModel(
                label = "Sum Assured ",
                hint = "SUM ASSURED  "
            ),
            EditDetailsModel(
                label = "Policy Start Date",
                hint = "POLICY START DATE "
            ),
            EditDetailsModel(
                label = "Policy Maturity Date",
                hint = "POLICY MATURITY DATE "
            ),
            EditDetailsModel(
                label = "Policy Term (Years)",
                hint = "POLICY TERM (YEARS) "
            ),
            EditDetailsModel(
                label = "Premium Payment Term (Years)",
                hint = "PREMIUM PAYMENT TERM (YEARS) "
            ),
            EditDetailsModel(
                label = "Premium Amount",
                hint = "PREMIUM AMOUNT IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Premium Payment Frequency ",
                hint = "PREMIUM PAYMENT FREQUENCY  "
            ),
            EditDetailsModel(
                label = "Renewal Date",
                hint = "RENEWAL DATE "
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
        HEALTH_INSURANCE -> { listOf(EditDetailsModel(
            label = "Plan Name",
            hint = "PLAN NAME "
        ),
            EditDetailsModel(
                label = "Policy Number",
                hint = "POLICY NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Policy Holder's Name",
                hint = "POLICY HOLDER'S NAME "
            ),
            EditDetailsModel(
                label = "Family Member Covered",
                hint = "FAMILY MEMBER COVERED "
            ),
            EditDetailsModel(
                label = "Sum Assured ",
                hint = "SUM ASSURED  "
            ),
            EditDetailsModel(
                label = "Policy Start Date",
                hint = "POLICY START DATE "
            ),
            EditDetailsModel(
                label = "Renewal Date",
                hint = "RENEWAL DATE "
            ),
            EditDetailsModel(
                label = "Premium Amount",
                hint = "PREMIUM AMOUNT IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "TPA Contact Number",
                hint = "TPA CONTACT NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Company Help Desk Number",
                hint = "COMPANY HELP DESK NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
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
        MOTOR_INSURANCE -> { listOf(EditDetailsModel(
            label = "Plan Name",
            hint = "PLAN NAME "
        ),
            EditDetailsModel(
                label = "Policy Number",
                hint = "POLICY NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Insured Declared Value",
                hint = "INSURED DECLARED VALUE IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Type of Cover",
                hint = "TYPE OF COVER "
            ),
            EditDetailsModel(
                label = "Policy Holder's Name",
                hint = "POLICY HOLDER'S NAME "
            ),
            EditDetailsModel(
                label = "Policy Start Date",
                hint = "POLICY START DATE "
            ),
            EditDetailsModel(
                label = "Premium Amount",
                hint = "PREMIUM AMOUNT IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Renewal Date",
                hint = "RENEWAL DATE "
            ),
            EditDetailsModel(
                label = "Company Helpdesk Number",
                hint = "COMPANY HELPDESK NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
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
        LOAN -> { listOf(EditDetailsModel(
            label = "Bank Name",
            hint = "BANK NAME "
        ),
            EditDetailsModel(
                label = "Loan Account Number",
                hint = "LOAN ACCOUNT NUMBER ",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Loan Amount",
                hint = "LOAN AMOUNT IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "Asset Name",
                hint = "ASSET NAME "
            ),
            EditDetailsModel(
                label = "Asset Type",
                hint = "ASSET TYPE "
            ),
            EditDetailsModel(
                label = "Loan Start Date",
                hint = "LOAN START DATE "
            ),
            EditDetailsModel(
                label = "Loan Tenure",
                hint = "LOAN TENURE "
            ),
            EditDetailsModel(
                label = "EMI Amount",
                hint = "EMI AMOUNT IN INR",
                inputType = InputType.TYPE_CLASS_NUMBER
            ),
            EditDetailsModel(
                label = "ROI",
                hint = "ROI "
            ),
            EditDetailsModel(
                label = "ECS Bank",
                hint = "ECS BANK "
            ),
            EditDetailsModel(
                label = "Insurance Protect Loan",
                hint = "INSURANCE PROTECT LOAN "
            ),
            EditDetailsModel(
                label = "Current Outstanding",
                hint = "CURRENT OUTSTANDING "
            ),
        )
        }

        else -> listOf()
    }


}