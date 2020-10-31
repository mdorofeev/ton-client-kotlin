package ee.nx01.tonclient.tvm

import ee.nx01.tonclient.TonClient


class TvmModule(private val tonClient: TonClient) {
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
