package ee.nx01.tonclient.tvm

import ee.nx01.tonclient.TonClient


class TvmModule(private val tonClient: TonClient) {
    /**
    Emulates all the phases of contract execution locally
    Performs all the phases of contract execution on Transaction Executor - the same component that is used on Validator Nodes.
    Can be used for contract debugging, to find out the reason why a message was not delivered successfully. Validators throw away the failed external inbound messages (if they failed bedore ACCEPT) in the real network. This is why these messages are impossible to debug in the real network. With the help of run_executor you can do that. In fact, process_message function performs local check with run_executor if there was no transaction as a result of processing and returns the error, if there is one.
    Another use case to use run_executor is to estimate fees for message execution. Set AccountForExecutor::Account.unlimited_balance to true so that emulation will not depend on the actual balance. This may be needed to calculate deploy fees for an account that does not exist yet. JSON with fees is in fees field of the result.
    One more use case - you can produce the sequence of operations, thus emulating the sequential contract calls locally. And so on.
    Transaction executor requires account BOC (bag of cells) as a parameter. To get the account BOC - use net.query method to download it from GraphQL API (field boc of account) or generate it with abi.encode_account method.

    Also it requires message BOC. To get the message BOC - use abi.encode_message or abi.encode_internal_message.

    If you need this emulation to be as precise as possible (for instance - emulate transaction with particular lt in particular block or use particular blockchain config, downloaded from a particular key block - then specify execution_options parameter.

    If you need to see the aborted transaction as a result, not as an error, set skip_transaction_check to true.
     */
    suspend fun runExecutor(params: ParamsOfRunExecutor): ResultOfRunExecutor {
        return tonClient.request("tvm.run_executor", params)
    }

    suspend fun runTvm(params: ParamsOfRunTvm): ResultOfRunTvm {
        return tonClient.request("tvm.run_tvm", params)
    }

    /**
    ## run_get

    Executes getmethod and returns data from TVM stack
     */
    suspend fun runGet(params: ParamsOfRunGet): ResultOfRunGet {
        return tonClient.request("tvm.run_get", params)
    }
}
