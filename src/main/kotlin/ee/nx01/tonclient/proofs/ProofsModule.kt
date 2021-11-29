package ee.nx01.tonclient.proofs

import ee.nx01.tonclient.TonClient

class ProofsModule(private val tonClient: TonClient) {
    /**
     * Proves that a given block's data, which is queried from TONOS API, can be trusted.
    This function checks block proofs and compares given data with the proven. If the given data differs from the proven, the exception will be thrown. The input param is a single block's JSON object, which was queried from DApp server using functions such as net.query, net.query_collection or net.wait_for_collection. If block's BOC is not provided in the JSON, it will be queried from DApp server (in this case it is required to provide at least id of block).
    Please note, that joins (like signatures in Block) are separated entities and not supported, so function will throw an exception in a case if JSON being checked has such entities in it.
    If cache_in_local_storage in config is set to true (default), downloaded proofs and master-chain BOCs are saved into the persistent local storage (e.g. file system for native environments or browser's IndexedDB for the web); otherwise all the data is cached only in memory in current client's context and will be lost after destruction of the client.
     */
    suspend fun proofBlockData(params: ParamsOfProofBlockData) {
        return tonClient.request("proofs.proof_block_data", params)
    }

    /**
     * Proves that a given transaction's data, which is queried from TONOS API, can be trusted.
    This function requests the corresponding block, checks block proofs, ensures that given transaction exists in the proven block and compares given data with the proven. If the given data differs from the proven, the exception will be thrown. The input parameter is a single transaction's JSON object (see params description), which was queried from TONOS API using functions such as net.query, net.query_collection or net.wait_for_collection.
    If transaction's BOC and/or block_id are not provided in the JSON, they will be queried from TONOS API (in this case it is required to provide at least id of transaction).
    Please note, that joins (like account, in_message, out_messages, etc. in Transaction entity) are separated entities and not supported, so function will throw an exception in a case if JSON being checked has such entities in it.
    If cache_in_local_storage in config is set to true (default), downloaded proofs and master-chain BOCs are saved into the persistent local storage (e.g. file system for native environments or browser's IndexedDB for the web); otherwise all the data is cached only in memory in current client's context and will be lost after destruction of the client.
    For more information about proofs checking, see description of proof_block_data function.
     */
    suspend fun proofTransactionData(params: ParamsOfProofTransactionData) {
        return tonClient.request("proofs.proof_transaction_data", params)
    }

}

data class ParamsOfProofBlockData(
    val block: Any
)

data class ParamsOfProofTransactionData(
    val transaction: Any
)