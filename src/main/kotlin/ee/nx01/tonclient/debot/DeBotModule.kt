package ee.nx01.tonclient.debot

import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient

/**
 * Module for working with debot.
 */
class DeBotModule(private val tonClient: TonClient) {

    /**
     * Downloads debot smart contract (code and data) from blockchain and creates an instance of Debot Engine for it.
     */
    suspend fun init(params: ParamsOfInit, onResult: (result: ParamsOfAppDebotBrowser) -> Unit): RegisteredDebot {
        return tonClient.request("debot.init", params) {
            onResult(JsonUtils.read(it))
        }
    }

    /**
     * Starts the DeBot.

    Downloads debot smart contract from blockchain and switches it to
    context zero.

    This function must be used by Debot Browser to start a dialog with debot.
    While the function is executing, several Browser Callbacks can be called,
    since the debot tries to display all actions from the context 0 to the user.

    When the debot starts SDK registers `BrowserCallbacks` AppObject.
    Therefore when `debote.remove` is called the debot is being deleted and the callback is called
    with `finish`=`true` which indicates that it will never be used again.

     */
    suspend fun start(params: ParamsOfStart) {
        return tonClient.request("debot.start", params)
    }

    /**
     * Fetches DeBot metadata from blockchain.
     *
     * Downloads DeBot from blockchain and creates and fetches its metadata.
     */
    suspend fun fetch(params: ParamsOfFetch): ResultOfFetch {
        return tonClient.request("debot.fetch", params)
    }

    /**
     * Executes debot action.
     * Calls debot engine referenced by debot handle to execute input action.
     * Calls Debot Browser Callbacks if needed.
     * # Remarks
     * Chain of actions can be executed if input action generates a list of subactions.
     */
    suspend fun execute(params: ParamsOfExecute) {
        return tonClient.request("debot.execute", params)
    }

    /**
     * Sends message to Debot.
     * Used by Debot Browser to send response on Dinterface call or from other Debots.
     */
    suspend fun send(params: ParamsOfSend) {
        return tonClient.request("debot.send", params)
    }

    /**
     * Destroys debot handle.
     * Removes handle from Client Context and drops debot engine referenced by that handle.
     */
    suspend fun remove(params: ParamsOfRemove) {
        return tonClient.request("debot.remove", params)
    }

}
