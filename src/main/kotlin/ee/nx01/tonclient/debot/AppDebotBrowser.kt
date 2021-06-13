package ee.nx01.tonclient.debot

interface AppDebotBrowser {
    fun log(params: ParamsOfAppDebotBrowserLog)
    fun switch(params: ParamsOfAppDebotBrowserSwitch)
    fun switchCompleted()
    fun showAction(params: ParamsOfAppDebotBrowserShowAction)
    fun input(params: ParamsOfAppDebotBrowserInput): ResultOfAppDebotBrowserInput
    fun getSigningBox(): ResultOfAppDebotBrowserGetSigningBox
    fun invokeDebot(params: ParamsOfAppDebotBrowserInvokeDebot)
    fun send(params: ParamsOfAppDebotBrowserSend)
    fun approve(params: ParamsOfAppDebotBrowserApprove): ResultOfAppDebotBrowserApprove
}