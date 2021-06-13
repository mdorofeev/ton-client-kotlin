package ee.nx01.tonclient.debot

data class ParamsOfAppDebotBrowserLog(
    val msg: String
)

data class ParamsOfAppDebotBrowserSwitch(
    val contextId: Long
)

data class ParamsOfAppDebotBrowserShowAction(
    val action: DebotAction
)

data class ParamsOfAppDebotBrowserInput(
    val prompt: String
)

data class ResultOfAppDebotBrowserInput(
    val value: String
)

data class ResultOfAppDebotBrowserGetSigningBox(
    val signingBox: Long
)

data class ParamsOfAppDebotBrowserInvokeDebot(
    val debotAddr: String,
    val action: DebotAction
)

data class ParamsOfAppDebotBrowserSend(
    val message: String
)

data class ParamsOfAppDebotBrowserApprove(
    val activity: DebotActivity
)

data class ResultOfAppDebotBrowserApprove(
    val approved: Boolean
)


enum class AppDebotBrowserMessageType {
    Log, Switch, SwitchCompleted, ShowAction, Input, GetSigningBox, InvokeDebot, Send, Approve
}

data class ParamsOfAppDebotBrowser(
    val type: AppDebotBrowserMessageType,
    val msg: String? = null,
    val contextId: Long? = null,
    val action: DebotAction? = null,
    val prompt: String? = null,
    val debotAddr: String? = null,
    val message: String? = null,
    val activity: DebotActivity? = null
)

data class DebotActivity(
    val type: String = "Transaction",
    val msg: String,
    val dst: String,
    val out: List<Spending> = listOf(),
    val fee: Long,
    val setcode: Boolean,
    val signkey: String,
    val signing_box_handle: Long
)

data class Spending(
    val amount: Long,
    val dst: String
)

data class ParamsOfRemove(
    val debotHandle: Long
)

data class ParamsOfSend(
    val debotHandle: Long,
    val message: String
)

data class ParamsOfExecute(
    val debotHandle: Long,
    val action: DebotAction
)

data class DebotAction(
    val description: String,
    val name: String,
    val actionType: Long,
    val to: Long,
    val attributes: String,
    val misc: String
)

data class ResultOfFetch(
    val info: DebotInfo
)

data class ParamsOfFetch(
    val address: String
)

data class ParamsOfStart(
    val debotHandle: Long
)

data class ParamsOfInit(
    val address: String
)

data class RegisteredDebot(
    val debotHandle: Long,
    val debotAbi: String,
    val info: DebotInfo
)

data class DebotInfo(
    val name: String? = null,
    val version: String? = null,
    val publisher: String? = null,
    val caption: String? = null,
    val author: String? = null,
    val support: String? = null,
    val hello: String? = null,
    val language: String? = null,
    val dabi: String? = null,
    val icon: String? = null,
    val interfaces: List<String> = listOf()
)
