package ee.nx01.tonclient.net

import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.types.Block
import ee.nx01.tonclient.types.BlockFilterInput

class Blocks(private val net: NetModule) {

    suspend fun query(filter: BlockFilterInput, result: String): List<Block> {
        val response = net.query(Query("blocks", filter, result))
        return JsonUtils.mapper.readValue<BlockResponse>(response).result
    }
}

data class BlockResponse(val result: List<Block>)