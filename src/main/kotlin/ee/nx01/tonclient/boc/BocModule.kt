package ee.nx01.tonclient.boc

import ee.nx01.tonclient.TonClient

class BocModule(private val tonClient: TonClient) {
    /**
    ## parse_message

    Parses message boc into a JSON

    JSON structure is compatible with GraphQL API message object
     */
    suspend fun parseMessage(params: ParamsOfParse): ResultOfParse {
        return tonClient.request("boc.parse_message", params)
    }

    /**
    ## parse_transaction

    Parses transaction boc into a JSON

    JSON structure is compatible with GraphQL API transaction object
     */
    suspend fun parseTransaction(params: ParamsOfParse): ResultOfParse {
        return tonClient.request("boc.parse_transaction", params)
    }

    /**
    ## parse_account

    Parses account boc into a JSON

    JSON structure is compatible with GraphQL API account object
     */
    suspend fun parseAccount(params: ParamsOfParse): ResultOfParse {
        return tonClient.request("boc.parse_account", params)
    }

    /**
    ## parse_block

    Parses block boc into a JSON

    JSON structure is compatible with GraphQL API block object
     */
    suspend fun parseBlock(params: ParamsOfParse): ResultOfParse {
        return tonClient.request("boc.parse_block", params)
    }

    suspend fun getBlockchainConfig(params: ParamsOfGetBlockchainConfig): ResultOfGetBlockchainConfig {
        return tonClient.request("boc.get_blockchain_config", params)
    }

    /**
     * #parse_shardstate
    Parses shardstate boc into a JSON

    JSON structure is compatible with GraphQL API shardstate object
     */
    suspend fun parseShardstate(params: ParamsOfParseShardstate): ResultOfParse {
        return tonClient.request("boc.parse_shardstate", params)
    }

    /**
     *  #get_code_from_tvc
    Extracts code from TVC contract image
     */
    suspend fun getCodeFromTvc(params: ParamsOfGetCodeFromTvc): ResultOfGetCodeFromTvc {
        return tonClient.request("boc.get_code_from_tvc", params)
    }

    /**
     * Calculates BOC root hash
     */
    suspend fun getBocHash(params: ParamsOfGetBocHash): ResultOfGetBocHash {
        return tonClient.request("boc.get_boc_hash", params)
    }


    /**
     * Encodes BOC from builder operations.
     */
    suspend fun encodeBoc(params: ParamsOfEncodeBoc): ResultOfEncodeBoc {
        return tonClient.request("boc.encode_boc", params)
    }

}
