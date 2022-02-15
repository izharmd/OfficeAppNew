package com.bws.officeapp.leavestatus.leavestatusviewmodel

data class LeaveStatusResponse(
    val bStatus: Boolean,
    val `data`: Data,
    val sMessage: String,
    val sStatus: String
)

data class Page(
    val CurrentPageNo: Int,
    val TotalPageCount: String,
    val TotalRecordsCount: String
)

data class Leave(
    val ApprovalStatus: String,
    val DaysOfLeave: Double,
    val FirstName: String,
    val LastName: String,
    val EmailID: String,
    val LeaveFrom: String,
    val LeaveTo: String,
    val Reason: String,
    val SlNo: Int,
    val Title: String,
    val LeaveReason: String,
    val LeaveCategoryName: String,
    val ApprovedBy: String,
    val LeaveID: Int,
    val UserID: Int
)

data class Data(
    val Leaves: List<Leave>,
    val Page: List<Page>
)