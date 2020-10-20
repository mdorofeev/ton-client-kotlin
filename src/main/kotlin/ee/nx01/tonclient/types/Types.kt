package ee.nx01.tonclient.types

/**
 * Due to GraphQL limitations big numbers are returned as a string.
 * You can specify format used to string representation for big integers.
 */
enum class BigIntFormat(val label: String) {
    Hex("HEX"),
    Dec("DEC");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): BigIntFormat? {
            return values().find { it.label == label }
        }
    }
}

data class StringFilterInput(
    val eq: String? = null,
    val ne: String? = null,
    val gt: String? = null,
    val lt: String? = null,
    val ge: String? = null,
    val le: String? = null,
    val `in`: Iterable<String>? = null,
    val notIn: Iterable<String>? = null
)

data class BooleanFilterInput(
    val eq: Boolean? = null,
    val ne: Boolean? = null,
    val gt: Boolean? = null,
    val lt: Boolean? = null,
    val ge: Boolean? = null,
    val le: Boolean? = null,
    val `in`: Iterable<Boolean>? = null,
    val notIn: Iterable<Boolean>? = null
)

data class IntFilterInput(
    val eq: Int? = null,
    val ne: Int? = null,
    val gt: Int? = null,
    val lt: Int? = null,
    val ge: Int? = null,
    val le: Int? = null,
    val `in`: Iterable<Int>? = null,
    val notIn: Iterable<Int>? = null
)

data class FloatFilterInput(
    val eq: Float? = null,
    val ne: Float? = null,
    val gt: Float? = null,
    val lt: Float? = null,
    val ge: Float? = null,
    val le: Float? = null,
    val `in`: Iterable<Float>? = null,
    val notIn: Iterable<Float>? = null
)

enum class InMsgTypeEnum(val label: String) {
    External("External"),
    Ihr("Ihr"),
    Immediately("Immediately"),
    Final("Final"),
    Transit("Transit"),
    DiscardedFinal("DiscardedFinal"),
    DiscardedTransit("DiscardedTransit");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): InMsgTypeEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class OutMsgTypeEnum(val label: String) {
    External("External"),
    Immediately("Immediately"),
    OutMsgNew("OutMsgNew"),
    Transit("Transit"),
    DequeueImmediately("DequeueImmediately"),
    Dequeue("Dequeue"),
    TransitRequired("TransitRequired"),
    DequeueShort("DequeueShort"),
    None("None");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): OutMsgTypeEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class MessageTypeEnum(val label: String) {
    Internal("Internal"),
    ExtIn("ExtIn"),
    ExtOut("ExtOut");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): MessageTypeEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class MessageProcessingStatusEnum(val label: String) {
    Unknown("Unknown"),
    Queued("Queued"),
    Processing("Processing"),
    Preliminary("Preliminary"),
    Proposed("Proposed"),
    Finalized("Finalized"),
    Refused("Refused"),
    Transiting("Transiting");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): MessageProcessingStatusEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class BlockProcessingStatusEnum(val label: String) {
    Unknown("Unknown"),
    Proposed("Proposed"),
    Finalized("Finalized"),
    Refused("Refused");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): BlockProcessingStatusEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class SplitTypeEnum(val label: String) {
    None("None"),
    Split("Split"),
    Merge("Merge");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): SplitTypeEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class AccountStatusEnum(val label: String) {
    Uninit("Uninit"),
    Active("Active"),
    Frozen("Frozen"),
    NonExist("NonExist");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): AccountStatusEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class TransactionTypeEnum(val label: String) {
    Ordinary("Ordinary"),
    Storage("Storage"),
    Tick("Tick"),
    Tock("Tock"),
    SplitPrepare("SplitPrepare"),
    SplitInstall("SplitInstall"),
    MergePrepare("MergePrepare"),
    MergeInstall("MergeInstall");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): TransactionTypeEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class TransactionProcessingStatusEnum(val label: String) {
    Unknown("Unknown"),
    Preliminary("Preliminary"),
    Proposed("Proposed"),
    Finalized("Finalized"),
    Refused("Refused");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): TransactionProcessingStatusEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class AccountStatusChangeEnum(val label: String) {
    Unchanged("Unchanged"),
    Frozen("Frozen"),
    Deleted("Deleted");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): AccountStatusChangeEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class ComputeTypeEnum(val label: String) {
    Skipped("Skipped"),
    Vm("Vm");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): ComputeTypeEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class SkipReasonEnum(val label: String) {
    NoState("NoState"),
    BadState("BadState"),
    NoGas("NoGas");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): SkipReasonEnum? {
            return values().find { it.label == label }
        }
    }
}

enum class BounceTypeEnum(val label: String) {
    NegFunds("NegFunds"),
    NoFunds("NoFunds"),
    Ok("Ok");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): BounceTypeEnum? {
            return values().find { it.label == label }
        }
    }
}

data class OtherCurrencyValueArgs(
    val format: BigIntFormat? = null
)

data class OtherCurrency(
    val currency: Float?,
    val value: String?
)

data class ExtBlkRefEnd_LtArgs(
    val format: BigIntFormat? = null
)

data class ExtBlkRef(
    val end_lt: String?,
    val seq_no: Float?,
    val root_hash: String?,
    val file_hash: String?
)

data class MsgEnvelopeFwd_Fee_RemainingArgs(
    val format: BigIntFormat? = null
)

data class MsgEnvelope(
    val msg_id: String?,
    val next_addr: String?,
    val cur_addr: String?,
    val fwd_fee_remaining: String?
)

data class InMsgIhr_FeeArgs(
    val format: BigIntFormat? = null
)

data class InMsgFwd_FeeArgs(
    val format: BigIntFormat? = null
)

data class InMsgTransit_FeeArgs(
    val format: BigIntFormat? = null
)

data class InMsg(
    val msg_type: Int?,
    val msg_type_name: InMsgTypeEnum?,
    val msg_id: String?,
    val ihr_fee: String?,
    val proof_created: String?,
    val in_msg: MsgEnvelope?,
    val fwd_fee: String?,
    val out_msg: MsgEnvelope?,
    val transit_fee: String?,
    val transaction_id: String?,
    val proof_delivered: String?
)

data class OutMsgImport_Block_LtArgs(
    val format: BigIntFormat? = null
)

data class OutMsgNext_Addr_PfxArgs(
    val format: BigIntFormat? = null
)

data class OutMsg(
    val msg_type: Int?,
    val msg_type_name: OutMsgTypeEnum?,
    val msg_id: String?,
    val transaction_id: String?,
    val out_msg: MsgEnvelope?,
    val reimport: InMsg?,
    val imported: InMsg?,
    val import_block_lt: String?,
    val msg_env_hash: String?,
    val next_workchain: Int?,
    val next_addr_pfx: String?
)

data class BlockValueFlowTo_Next_BlkArgs(
    val format: BigIntFormat? = null
)

data class BlockValueFlowExportedArgs(
    val format: BigIntFormat? = null
)

data class BlockValueFlowFees_CollectedArgs(
    val format: BigIntFormat? = null
)

data class BlockValueFlowCreatedArgs(
    val format: BigIntFormat? = null
)

data class BlockValueFlowImportedArgs(
    val format: BigIntFormat? = null
)

data class BlockValueFlowFrom_Prev_BlkArgs(
    val format: BigIntFormat? = null
)

data class BlockValueFlowMintedArgs(
    val format: BigIntFormat? = null
)

data class BlockValueFlowFees_ImportedArgs(
    val format: BigIntFormat? = null
)

data class BlockValueFlow(
    val to_next_blk: String?,
    val to_next_blk_other: Iterable<OtherCurrency>?,
    val exported: String?,
    val exported_other: Iterable<OtherCurrency>?,
    val fees_collected: String?,
    val fees_collected_other: Iterable<OtherCurrency>?,
    val created: String?,
    val created_other: Iterable<OtherCurrency>?,
    val imported: String?,
    val imported_other: Iterable<OtherCurrency>?,
    val from_prev_blk: String?,
    val from_prev_blk_other: Iterable<OtherCurrency>?,
    val minted: String?,
    val minted_other: Iterable<OtherCurrency>?,
    val fees_imported: String?,
    val fees_imported_other: Iterable<OtherCurrency>?
)

data class BlockAccountBlocksTransactionsLtArgs(
    val format: BigIntFormat? = null
)

data class BlockAccountBlocksTransactionsTotal_FeesArgs(
    val format: BigIntFormat? = null
)

data class BlockAccountBlocksTransactions(
    val lt: String?,
    val transaction_id: String?,
    val total_fees: String?,
    val total_fees_other: Iterable<OtherCurrency>?
)

data class BlockAccountBlocks(
    val account_addr: String?,
    val transactions: Iterable<BlockAccountBlocksTransactions>?,
    val old_hash: String?,
    val new_hash: String?,
    val tr_count: Int?
)

data class BlockStateUpdate(
    val new: String?,
    val new_hash: String?,
    val new_depth: Int?,
    val old: String?,
    val old_hash: String?,
    val old_depth: Int?
)

data class BlockMasterShardHashesDescrStart_LtArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterShardHashesDescrEnd_LtArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterShardHashesDescrFees_CollectedArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterShardHashesDescrFunds_CreatedArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterShardHashesDescr(
    val seq_no: Float?,
    val reg_mc_seqno: Float?,
    val start_lt: String?,
    val end_lt: String?,
    val root_hash: String?,
    val file_hash: String?,
    val before_split: Boolean?,
    val before_merge: Boolean?,
    val want_split: Boolean?,
    val want_merge: Boolean?,
    val nx_cc_updated: Boolean?,
    val flags: Int?,
    val next_catchain_seqno: Float?,
    val next_validator_shard: String?,
    val min_ref_mc_seqno: Float?,
    val gen_utime: Float?,
    val gen_utime_string: String?,
    val split_type: Int?,
    val split_type_name: SplitTypeEnum?,
    val split: Float?,
    val fees_collected: String?,
    val fees_collected_other: Iterable<OtherCurrency>?,
    val funds_created: String?,
    val funds_created_other: Iterable<OtherCurrency>?
)

data class BlockMasterShardHashes(
    val workchain_id: Int?,
    val shard: String?,
    val descr: BlockMasterShardHashesDescr?
)

data class BlockMasterShardFeesFeesArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterShardFeesCreateArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterShardFees(
    val workchain_id: Int?,
    val shard: String?,
    val fees: String?,
    val fees_other: Iterable<OtherCurrency>?,
    val create: String?,
    val create_other: Iterable<OtherCurrency>?
)

data class BlockMasterPrevBlkSignatures(
    val node_id: String?,
    val r: String?,
    val s: String?
)

data class BlockMasterConfigP6(
    val mint_new_price: String?,
    val mint_add_price: String?
)

data class BlockMasterConfigP7(
    val currency: Float?,
    val value: String?
)

data class BlockMasterConfigP8(
    val version: Float?,
    val capabilities: String?
)

data class ConfigProposalSetup(
    val min_tot_rounds: Int?,
    val max_tot_rounds: Int?,
    val min_wins: Int?,
    val max_losses: Int?,
    val min_store_sec: Float?,
    val max_store_sec: Float?,
    val bit_price: Float?,
    val cell_price: Float?
)

data class BlockMasterConfigP11(
    val normal_params: ConfigProposalSetup?,
    val critical_params: ConfigProposalSetup?
)

data class BlockMasterConfigP12(
    val workchain_id: Int?,
    val enabled_since: Float?,
    val actual_min_split: Int?,
    val min_split: Int?,
    val max_split: Int?,
    val active: Boolean?,
    val accept_msgs: Boolean?,
    val flags: Int?,
    val zerostate_root_hash: String?,
    val zerostate_file_hash: String?,
    val version: Float?,
    val basic: Boolean?,
    val vm_version: Int?,
    val vm_mode: String?,
    val min_addr_len: Int?,
    val max_addr_len: Int?,
    val addr_len_step: Int?,
    val workchain_type_id: Float?
)

data class BlockMasterConfigP14Masterchain_Block_FeeArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterConfigP14Basechain_Block_FeeArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterConfigP14(
    val masterchain_block_fee: String?,
    val basechain_block_fee: String?
)

data class BlockMasterConfigP15(
    val validators_elected_for: Float?,
    val elections_start_before: Float?,
    val elections_end_before: Float?,
    val stake_held_for: Float?
)

data class BlockMasterConfigP16(
    val max_validators: Int?,
    val max_main_validators: Int?,
    val min_validators: Int?
)

data class BlockMasterConfigP17Min_StakeArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterConfigP17Max_StakeArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterConfigP17Min_Total_StakeArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterConfigP17(
    val min_stake: String?,
    val max_stake: String?,
    val min_total_stake: String?,
    val max_stake_factor: Float?
)

data class BlockMasterConfigP18Bit_Price_PsArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterConfigP18Cell_Price_PsArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterConfigP18Mc_Bit_Price_PsArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterConfigP18Mc_Cell_Price_PsArgs(
    val format: BigIntFormat? = null
)

data class BlockMasterConfigP18(
    val utime_since: Float?,
    val utime_since_string: String?,
    val bit_price_ps: String?,
    val cell_price_ps: String?,
    val mc_bit_price_ps: String?,
    val mc_cell_price_ps: String?
)

data class GasLimitsPricesGas_PriceArgs(
    val format: BigIntFormat? = null
)

data class GasLimitsPricesGas_LimitArgs(
    val format: BigIntFormat? = null
)

data class GasLimitsPricesSpecial_Gas_LimitArgs(
    val format: BigIntFormat? = null
)

data class GasLimitsPricesGas_CreditArgs(
    val format: BigIntFormat? = null
)

data class GasLimitsPricesBlock_Gas_LimitArgs(
    val format: BigIntFormat? = null
)

data class GasLimitsPricesFreeze_Due_LimitArgs(
    val format: BigIntFormat? = null
)

data class GasLimitsPricesDelete_Due_LimitArgs(
    val format: BigIntFormat? = null
)

data class GasLimitsPricesFlat_Gas_LimitArgs(
    val format: BigIntFormat? = null
)

data class GasLimitsPricesFlat_Gas_PriceArgs(
    val format: BigIntFormat? = null
)

data class GasLimitsPrices(
    val gas_price: String?,
    val gas_limit: String?,
    val special_gas_limit: String?,
    val gas_credit: String?,
    val block_gas_limit: String?,
    val freeze_due_limit: String?,
    val delete_due_limit: String?,
    val flat_gas_limit: String?,
    val flat_gas_price: String?
)

data class BlockLimitsBytes(
    val underload: Float?,
    val soft_limit: Float?,
    val hard_limit: Float?
)

data class BlockLimitsGas(
    val underload: Float?,
    val soft_limit: Float?,
    val hard_limit: Float?
)

data class BlockLimitsLtDelta(
    val underload: Float?,
    val soft_limit: Float?,
    val hard_limit: Float?
)

data class BlockLimits(
    val bytes: BlockLimitsBytes?,
    val gas: BlockLimitsGas?,
    val lt_delta: BlockLimitsLtDelta?
)

data class MsgForwardPricesLump_PriceArgs(
    val format: BigIntFormat? = null
)

data class MsgForwardPricesBit_PriceArgs(
    val format: BigIntFormat? = null
)

data class MsgForwardPricesCell_PriceArgs(
    val format: BigIntFormat? = null
)

data class MsgForwardPrices(
    val lump_price: String?,
    val bit_price: String?,
    val cell_price: String?,
    val ihr_price_factor: Float?,
    val first_frac: Int?,
    val next_frac: Int?
)

data class BlockMasterConfigP28(
    val shuffle_mc_validators: Boolean?,
    val mc_catchain_lifetime: Float?,
    val shard_catchain_lifetime: Float?,
    val shard_validators_lifetime: Float?,
    val shard_validators_num: Float?
)

data class BlockMasterConfigP29(
    val new_catchain_ids: Boolean?,
    val round_candidates: Float?,
    val next_candidate_delay_ms: Float?,
    val consensus_timeout_ms: Float?,
    val fast_attempts: Float?,
    val attempt_duration: Float?,
    val catchain_max_deps: Float?,
    val max_block_bytes: Float?,
    val max_collated_bytes: Float?
)

data class ValidatorSetListWeightArgs(
    val format: BigIntFormat? = null
)

data class ValidatorSetList(
    val public_key: String?,
    val weight: String?,
    val adnl_addr: String?
)

data class ValidatorSetTotal_WeightArgs(
    val format: BigIntFormat? = null
)

data class ValidatorSet(
    val utime_since: Float?,
    val utime_since_string: String?,
    val utime_until: Float?,
    val utime_until_string: String?,
    val total: Int?,
    val total_weight: String?,
    val list: Iterable<ValidatorSetList>?
)

data class BlockMasterConfigP39(
    val adnl_addr: String?,
    val temp_public_key: String?,
    val seqno: Float?,
    val valid_until: Float?,
    val signature_r: String?,
    val signature_s: String?
)

data class BlockMasterConfig(
    val p0: String?,
    val p1: String?,
    val p2: String?,
    val p3: String?,
    val p4: String?,
    val p6: BlockMasterConfigP6?,
    val p7: Iterable<BlockMasterConfigP7>?,
    val p8: BlockMasterConfigP8?,
    val p9: Iterable<Float>?,
    val p10: Iterable<Float>?,
    val p11: BlockMasterConfigP11?,
    val p12: Iterable<BlockMasterConfigP12>?,
    val p14: BlockMasterConfigP14?,
    val p15: BlockMasterConfigP15?,
    val p16: BlockMasterConfigP16?,
    val p17: BlockMasterConfigP17?,
    val p18: Iterable<BlockMasterConfigP18>?,
    val p20: GasLimitsPrices?,
    val p21: GasLimitsPrices?,
    val p22: BlockLimits?,
    val p23: BlockLimits?,
    val p24: MsgForwardPrices?,
    val p25: MsgForwardPrices?,
    val p28: BlockMasterConfigP28?,
    val p29: BlockMasterConfigP29?,
    val p31: Iterable<String>?,
    val p32: ValidatorSet?,
    val p33: ValidatorSet?,
    val p34: ValidatorSet?,
    val p35: ValidatorSet?,
    val p36: ValidatorSet?,
    val p37: ValidatorSet?,
    val p39: Iterable<BlockMasterConfigP39>?
)

data class BlockMaster(
    val min_shard_gen_utime: Float?,
    val min_shard_gen_utime_string: String?,
    val max_shard_gen_utime: Float?,
    val max_shard_gen_utime_string: String?,
    val shard_hashes: Iterable<BlockMasterShardHashes>?,
    val shard_fees: Iterable<BlockMasterShardFees>?,
    val recover_create_msg: InMsg?,
    val prev_blk_signatures: Iterable<BlockMasterPrevBlkSignatures>?,
    val config_addr: String?,
    val config: BlockMasterConfig?
)

data class BlockSignaturesSignatures(
    val node_id: String?,
    val r: String?,
    val s: String?
)

data class BlockSignaturesSig_WeightArgs(
    val format: BigIntFormat? = null
)

data class BlockSignaturesBlockArgs(
    val timeout: Int? = null,
    val `when`: BlockSignaturesFilterInput? = null
)

data class BlockSignatures(
    val id: String?,
    val gen_utime: Float?,
    val gen_utime_string: String?,
    val seq_no: Float?,
    val shard: String?,
    val workchain_id: Int?,
    val proof: String?,
    val validator_list_hash_short: Float?,
    val catchain_seqno: Float?,
    val sig_weight: String?,
    val signatures: Iterable<BlockSignaturesSignatures>?,
    val block: Block?
)

data class BlockStart_LtArgs(
    val format: BigIntFormat? = null
)

data class BlockEnd_LtArgs(
    val format: BigIntFormat? = null
)

data class BlockSignaturesArgs(
    val timeout: Int? = null,
    val `when`: BlockFilterInput? = null
)

data class Block(
    val id: String?,
    val status: Int?,
    val status_name: BlockProcessingStatusEnum?,
    val global_id: Float?,
    val want_split: Boolean?,
    val seq_no: Float?,
    val after_merge: Boolean?,
    val gen_utime: Float?,
    val gen_utime_string: String?,
    val gen_catchain_seqno: Float?,
    val flags: Int?,
    val master_ref: ExtBlkRef?,
    val prev_ref: ExtBlkRef?,
    val prev_alt_ref: ExtBlkRef?,
    val prev_vert_ref: ExtBlkRef?,
    val prev_vert_alt_ref: ExtBlkRef?,
    val version: Float?,
    val gen_validator_list_hash_short: Float?,
    val before_split: Boolean?,
    val after_split: Boolean?,
    val want_merge: Boolean?,
    val vert_seq_no: Float?,
    val start_lt: String?,
    val end_lt: String?,
    val workchain_id: Int?,
    val shard: String?,
    val min_ref_mc_seqno: Float?,
    val prev_key_block_seqno: Float?,
    val gen_software_version: Float?,
    val gen_software_capabilities: String?,
    val value_flow: BlockValueFlow?,
    val in_msg_descr: Iterable<InMsg>?,
    val rand_seed: String?,
    val created_by: String?,
    val out_msg_descr: Iterable<OutMsg>?,
    val account_blocks: Iterable<BlockAccountBlocks>?,
    val tr_count: Int?,
    val state_update: BlockStateUpdate?,
    val master: BlockMaster?,
    val key_block: Boolean?,
    val boc: String?,
    val signatures: BlockSignatures?
)

data class TransactionStorageStorage_Fees_CollectedArgs(
    val format: BigIntFormat? = null
)

data class TransactionStorageStorage_Fees_DueArgs(
    val format: BigIntFormat? = null
)

data class TransactionStorage(
    val storage_fees_collected: String?,
    val storage_fees_due: String?,
    val status_change: Int?,
    val status_change_name: AccountStatusChangeEnum?
)

data class TransactionCreditDue_Fees_CollectedArgs(
    val format: BigIntFormat? = null
)

data class TransactionCreditCreditArgs(
    val format: BigIntFormat? = null
)

data class TransactionCredit(
    val due_fees_collected: String?,
    val credit: String?,
    val credit_other: Iterable<OtherCurrency>?
)

data class TransactionComputeGas_FeesArgs(
    val format: BigIntFormat? = null
)

data class TransactionComputeGas_UsedArgs(
    val format: BigIntFormat? = null
)

data class TransactionComputeGas_LimitArgs(
    val format: BigIntFormat? = null
)

data class TransactionCompute(
    val compute_type: Int?,
    val compute_type_name: ComputeTypeEnum?,
    val skipped_reason: Int?,
    val skipped_reason_name: SkipReasonEnum?,
    val success: Boolean?,
    val msg_state_used: Boolean?,
    val account_activated: Boolean?,
    val gas_fees: String?,
    val gas_used: String?,
    val gas_limit: String?,
    val gas_credit: Int?,
    val mode: Int?,
    val exit_code: Int?,
    val exit_arg: Int?,
    val vm_steps: Float?,
    val vm_init_state_hash: String?,
    val vm_final_state_hash: String?
)

data class TransactionActionTotal_Fwd_FeesArgs(
    val format: BigIntFormat? = null
)

data class TransactionActionTotal_Action_FeesArgs(
    val format: BigIntFormat? = null
)

data class TransactionAction(
    val success: Boolean?,
    val valid: Boolean?,
    val no_funds: Boolean?,
    val status_change: Int?,
    val status_change_name: AccountStatusChangeEnum?,
    val total_fwd_fees: String?,
    val total_action_fees: String?,
    val result_code: Int?,
    val result_arg: Int?,
    val tot_actions: Int?,
    val spec_actions: Int?,
    val skipped_actions: Int?,
    val msgs_created: Int?,
    val action_list_hash: String?,
    val total_msg_size_cells: Float?,
    val total_msg_size_bits: Float?
)

data class TransactionBounceReq_Fwd_FeesArgs(
    val format: BigIntFormat? = null
)

data class TransactionBounceMsg_FeesArgs(
    val format: BigIntFormat? = null
)

data class TransactionBounceFwd_FeesArgs(
    val format: BigIntFormat? = null
)

data class TransactionBounce(
    val bounce_type: Int?,
    val bounce_type_name: BounceTypeEnum?,
    val msg_size_cells: Float?,
    val msg_size_bits: Float?,
    val req_fwd_fees: String?,
    val msg_fees: String?,
    val fwd_fees: String?
)

data class TransactionSplitInfo(
    val cur_shard_pfx_len: Int?,
    val acc_split_depth: Int?,
    val this_addr: String?,
    val sibling_addr: String?
)

data class TransactionBlockArgs(
    val timeout: Int? = null,
    val `when`: TransactionFilterInput? = null
)

data class TransactionLtArgs(
    val format: BigIntFormat? = null
)

data class TransactionPrev_Trans_LtArgs(
    val format: BigIntFormat? = null
)

data class TransactionIn_MessageArgs(
    val timeout: Int? = null,
    val `when`: TransactionFilterInput? = null
)

data class TransactionOut_MessagesArgs(
    val timeout: Int? = null,
    val `when`: TransactionFilterInput? = null
)

data class TransactionTotal_FeesArgs(
    val format: BigIntFormat? = null
)

data class TransactionBalance_DeltaArgs(
    val format: BigIntFormat? = null
)

data class Transaction(
    val id: String?,
    val tr_type: Int?,
    val tr_type_name: TransactionTypeEnum?,
    val status: Int?,
    val status_name: TransactionProcessingStatusEnum?,
    val block_id: String?,
    val block: Block?,
    val account_addr: String?,
    val workchain_id: Int?,
    val lt: String?,
    val prev_trans_hash: String?,
    val prev_trans_lt: String?,
    val now: Float?,
    val outmsg_cnt: Int?,
    val orig_status: Int?,
    val orig_status_name: AccountStatusEnum?,
    val end_status: Int?,
    val end_status_name: AccountStatusEnum?,
    val in_msg: String?,
    val in_message: Message?,
    val out_msgs: Iterable<String>?,
    val out_messages: Iterable<Message>?,
    val total_fees: String?,
    val total_fees_other: Iterable<OtherCurrency>?,
    val old_hash: String?,
    val new_hash: String?,
    val credit_first: Boolean?,
    val storage: TransactionStorage?,
    val credit: TransactionCredit?,
    val compute: TransactionCompute?,
    val action: TransactionAction?,
    val bounce: TransactionBounce?,
    val aborted: Boolean?,
    val destroyed: Boolean?,
    val tt: String?,
    val split_info: TransactionSplitInfo?,
    val prepare_transaction: String?,
    val installed: Boolean?,
    val proof: String?,
    val boc: String?,
    val balance_delta: String?,
    val balance_delta_other: Iterable<OtherCurrency>?
)

data class MessageBlockArgs(
    val timeout: Int? = null,
    val `when`: MessageFilterInput? = null
)

data class MessageCreated_LtArgs(
    val format: BigIntFormat? = null
)

data class MessageIhr_FeeArgs(
    val format: BigIntFormat? = null
)

data class MessageFwd_FeeArgs(
    val format: BigIntFormat? = null
)

data class MessageImport_FeeArgs(
    val format: BigIntFormat? = null
)

data class MessageValueArgs(
    val format: BigIntFormat? = null
)

data class MessageSrc_TransactionArgs(
    val timeout: Int? = null,
    val `when`: MessageFilterInput? = null
)

data class MessageDst_TransactionArgs(
    val timeout: Int? = null,
    val `when`: MessageFilterInput? = null
)

data class Message(
    val id: String?,
    val msg_type: Int?,
    val msg_type_name: MessageTypeEnum?,
    val status: Int?,
    val status_name: MessageProcessingStatusEnum?,
    val block_id: String?,
    val block: Block?,
    val body: String?,
    val body_hash: String?,
    val split_depth: Int?,
    val tick: Boolean?,
    val tock: Boolean?,
    val code: String?,
    val code_hash: String?,
    val data: String?,
    val data_hash: String?,
    val library: String?,
    val library_hash: String?,
    val src: String?,
    val dst: String?,
    val src_workchain_id: Int?,
    val dst_workchain_id: Int?,
    val created_lt: String?,
    val created_at: Float?,
    val created_at_string: String?,
    val ihr_disabled: Boolean?,
    val ihr_fee: String?,
    val fwd_fee: String?,
    val import_fee: String?,
    val bounce: Boolean?,
    val bounced: Boolean?,
    val value: String?,
    val value_other: Iterable<OtherCurrency>?,
    val proof: String?,
    val boc: String?,
    val src_transaction: Transaction?,
    val dst_transaction: Transaction?
)

data class AccountDue_PaymentArgs(
    val format: BigIntFormat? = null
)

data class AccountLast_Trans_LtArgs(
    val format: BigIntFormat? = null
)

data class AccountBalanceArgs(
    val format: BigIntFormat? = null
)

data class Account(
    val id: String?,
    val workchain_id: Int?,
    val acc_type: Int?,
    val acc_type_name: AccountStatusEnum?,
    val last_paid: Float?,
    val due_payment: String?,
    val last_trans_lt: String?,
    val balance: String?,
    val balance_other: Iterable<OtherCurrency>?,
    val split_depth: Int?,
    val tick: Boolean?,
    val tock: Boolean?,
    val code: String?,
    val code_hash: String?,
    val data: String?,
    val data_hash: String?,
    val library: String?,
    val library_hash: String?,
    val proof: String?,
    val boc: String?,
    val state_hash: String?
)

data class OtherCurrencyFilterInput(
    val currency: FloatFilterInput? = null,
    val value: StringFilterInput? = null,
    val OR: OtherCurrencyFilterInput? = null
)

data class ExtBlkRefFilterInput(
    val end_lt: StringFilterInput? = null,
    val seq_no: FloatFilterInput? = null,
    val root_hash: StringFilterInput? = null,
    val file_hash: StringFilterInput? = null,
    val OR: ExtBlkRefFilterInput? = null
)

data class MsgEnvelopeFilterInput(
    val msg_id: StringFilterInput? = null,
    val next_addr: StringFilterInput? = null,
    val cur_addr: StringFilterInput? = null,
    val fwd_fee_remaining: StringFilterInput? = null,
    val OR: MsgEnvelopeFilterInput? = null
)

data class InMsgTypeEnumFilterInput(
    val eq: InMsgTypeEnum? = null,
    val ne: InMsgTypeEnum? = null,
    val gt: InMsgTypeEnum? = null,
    val lt: InMsgTypeEnum? = null,
    val ge: InMsgTypeEnum? = null,
    val le: InMsgTypeEnum? = null,
    val `in`: Iterable<InMsgTypeEnum>? = null,
    val notIn: Iterable<InMsgTypeEnum>? = null
)

data class InMsgFilterInput(
    val msg_type: IntFilterInput? = null,
    val msg_type_name: InMsgTypeEnumFilterInput? = null,
    val msg_id: StringFilterInput? = null,
    val ihr_fee: StringFilterInput? = null,
    val proof_created: StringFilterInput? = null,
    val in_msg: MsgEnvelopeFilterInput? = null,
    val fwd_fee: StringFilterInput? = null,
    val out_msg: MsgEnvelopeFilterInput? = null,
    val transit_fee: StringFilterInput? = null,
    val transaction_id: StringFilterInput? = null,
    val proof_delivered: StringFilterInput? = null,
    val OR: InMsgFilterInput? = null
)

data class OutMsgTypeEnumFilterInput(
    val eq: OutMsgTypeEnum? = null,
    val ne: OutMsgTypeEnum? = null,
    val gt: OutMsgTypeEnum? = null,
    val lt: OutMsgTypeEnum? = null,
    val ge: OutMsgTypeEnum? = null,
    val le: OutMsgTypeEnum? = null,
    val `in`: Iterable<OutMsgTypeEnum>? = null,
    val notIn: Iterable<OutMsgTypeEnum>? = null
)

data class OutMsgFilterInput(
    val msg_type: IntFilterInput? = null,
    val msg_type_name: OutMsgTypeEnumFilterInput? = null,
    val msg_id: StringFilterInput? = null,
    val transaction_id: StringFilterInput? = null,
    val out_msg: MsgEnvelopeFilterInput? = null,
    val reimport: InMsgFilterInput? = null,
    val imported: InMsgFilterInput? = null,
    val import_block_lt: StringFilterInput? = null,
    val msg_env_hash: StringFilterInput? = null,
    val next_workchain: IntFilterInput? = null,
    val next_addr_pfx: StringFilterInput? = null,
    val OR: OutMsgFilterInput? = null
)

data class OtherCurrencyArrayFilterInput(
    val any: OtherCurrencyFilterInput? = null,
    val all: OtherCurrencyFilterInput? = null
)

data class BlockValueFlowFilterInput(
    val to_next_blk: StringFilterInput? = null,
    val to_next_blk_other: OtherCurrencyArrayFilterInput? = null,
    val exported: StringFilterInput? = null,
    val exported_other: OtherCurrencyArrayFilterInput? = null,
    val fees_collected: StringFilterInput? = null,
    val fees_collected_other: OtherCurrencyArrayFilterInput? = null,
    val created: StringFilterInput? = null,
    val created_other: OtherCurrencyArrayFilterInput? = null,
    val imported: StringFilterInput? = null,
    val imported_other: OtherCurrencyArrayFilterInput? = null,
    val from_prev_blk: StringFilterInput? = null,
    val from_prev_blk_other: OtherCurrencyArrayFilterInput? = null,
    val minted: StringFilterInput? = null,
    val minted_other: OtherCurrencyArrayFilterInput? = null,
    val fees_imported: StringFilterInput? = null,
    val fees_imported_other: OtherCurrencyArrayFilterInput? = null,
    val OR: BlockValueFlowFilterInput? = null
)

data class BlockAccountBlocksTransactionsFilterInput(
    val lt: StringFilterInput? = null,
    val transaction_id: StringFilterInput? = null,
    val total_fees: StringFilterInput? = null,
    val total_fees_other: OtherCurrencyArrayFilterInput? = null,
    val OR: BlockAccountBlocksTransactionsFilterInput? = null
)

data class BlockAccountBlocksTransactionsArrayFilterInput(
    val any: BlockAccountBlocksTransactionsFilterInput? = null,
    val all: BlockAccountBlocksTransactionsFilterInput? = null
)

data class BlockAccountBlocksFilterInput(
    val account_addr: StringFilterInput? = null,
    val transactions: BlockAccountBlocksTransactionsArrayFilterInput? = null,
    val old_hash: StringFilterInput? = null,
    val new_hash: StringFilterInput? = null,
    val tr_count: IntFilterInput? = null,
    val OR: BlockAccountBlocksFilterInput? = null
)

data class BlockStateUpdateFilterInput(
    val new: StringFilterInput? = null,
    val new_hash: StringFilterInput? = null,
    val new_depth: IntFilterInput? = null,
    val old: StringFilterInput? = null,
    val old_hash: StringFilterInput? = null,
    val old_depth: IntFilterInput? = null,
    val OR: BlockStateUpdateFilterInput? = null
)

data class SplitTypeEnumFilterInput(
    val eq: SplitTypeEnum? = null,
    val ne: SplitTypeEnum? = null,
    val gt: SplitTypeEnum? = null,
    val lt: SplitTypeEnum? = null,
    val ge: SplitTypeEnum? = null,
    val le: SplitTypeEnum? = null,
    val `in`: Iterable<SplitTypeEnum>? = null,
    val notIn: Iterable<SplitTypeEnum>? = null
)

data class BlockMasterShardHashesDescrFilterInput(
    val seq_no: FloatFilterInput? = null,
    val reg_mc_seqno: FloatFilterInput? = null,
    val start_lt: StringFilterInput? = null,
    val end_lt: StringFilterInput? = null,
    val root_hash: StringFilterInput? = null,
    val file_hash: StringFilterInput? = null,
    val before_split: BooleanFilterInput? = null,
    val before_merge: BooleanFilterInput? = null,
    val want_split: BooleanFilterInput? = null,
    val want_merge: BooleanFilterInput? = null,
    val nx_cc_updated: BooleanFilterInput? = null,
    val flags: IntFilterInput? = null,
    val next_catchain_seqno: FloatFilterInput? = null,
    val next_validator_shard: StringFilterInput? = null,
    val min_ref_mc_seqno: FloatFilterInput? = null,
    val gen_utime: FloatFilterInput? = null,
    val split_type: IntFilterInput? = null,
    val split_type_name: SplitTypeEnumFilterInput? = null,
    val split: FloatFilterInput? = null,
    val fees_collected: StringFilterInput? = null,
    val fees_collected_other: OtherCurrencyArrayFilterInput? = null,
    val funds_created: StringFilterInput? = null,
    val funds_created_other: OtherCurrencyArrayFilterInput? = null,
    val OR: BlockMasterShardHashesDescrFilterInput? = null
)

data class BlockMasterShardHashesFilterInput(
    val workchain_id: IntFilterInput? = null,
    val shard: StringFilterInput? = null,
    val descr: BlockMasterShardHashesDescrFilterInput? = null,
    val OR: BlockMasterShardHashesFilterInput? = null
)

data class BlockMasterShardFeesFilterInput(
    val workchain_id: IntFilterInput? = null,
    val shard: StringFilterInput? = null,
    val fees: StringFilterInput? = null,
    val fees_other: OtherCurrencyArrayFilterInput? = null,
    val create: StringFilterInput? = null,
    val create_other: OtherCurrencyArrayFilterInput? = null,
    val OR: BlockMasterShardFeesFilterInput? = null
)

data class BlockMasterPrevBlkSignaturesFilterInput(
    val node_id: StringFilterInput? = null,
    val r: StringFilterInput? = null,
    val s: StringFilterInput? = null,
    val OR: BlockMasterPrevBlkSignaturesFilterInput? = null
)

data class BlockMasterConfigP6FilterInput(
    val mint_new_price: StringFilterInput? = null,
    val mint_add_price: StringFilterInput? = null,
    val OR: BlockMasterConfigP6FilterInput? = null
)

data class BlockMasterConfigP7FilterInput(
    val currency: FloatFilterInput? = null,
    val value: StringFilterInput? = null,
    val OR: BlockMasterConfigP7FilterInput? = null
)

data class BlockMasterConfigP8FilterInput(
    val version: FloatFilterInput? = null,
    val capabilities: StringFilterInput? = null,
    val OR: BlockMasterConfigP8FilterInput? = null
)

data class ConfigProposalSetupFilterInput(
    val min_tot_rounds: IntFilterInput? = null,
    val max_tot_rounds: IntFilterInput? = null,
    val min_wins: IntFilterInput? = null,
    val max_losses: IntFilterInput? = null,
    val min_store_sec: FloatFilterInput? = null,
    val max_store_sec: FloatFilterInput? = null,
    val bit_price: FloatFilterInput? = null,
    val cell_price: FloatFilterInput? = null,
    val OR: ConfigProposalSetupFilterInput? = null
)

data class BlockMasterConfigP11FilterInput(
    val normal_params: ConfigProposalSetupFilterInput? = null,
    val critical_params: ConfigProposalSetupFilterInput? = null,
    val OR: BlockMasterConfigP11FilterInput? = null
)

data class BlockMasterConfigP12FilterInput(
    val workchain_id: IntFilterInput? = null,
    val enabled_since: FloatFilterInput? = null,
    val actual_min_split: IntFilterInput? = null,
    val min_split: IntFilterInput? = null,
    val max_split: IntFilterInput? = null,
    val active: BooleanFilterInput? = null,
    val accept_msgs: BooleanFilterInput? = null,
    val flags: IntFilterInput? = null,
    val zerostate_root_hash: StringFilterInput? = null,
    val zerostate_file_hash: StringFilterInput? = null,
    val version: FloatFilterInput? = null,
    val basic: BooleanFilterInput? = null,
    val vm_version: IntFilterInput? = null,
    val vm_mode: StringFilterInput? = null,
    val min_addr_len: IntFilterInput? = null,
    val max_addr_len: IntFilterInput? = null,
    val addr_len_step: IntFilterInput? = null,
    val workchain_type_id: FloatFilterInput? = null,
    val OR: BlockMasterConfigP12FilterInput? = null
)

data class BlockMasterConfigP14FilterInput(
    val masterchain_block_fee: StringFilterInput? = null,
    val basechain_block_fee: StringFilterInput? = null,
    val OR: BlockMasterConfigP14FilterInput? = null
)

data class BlockMasterConfigP15FilterInput(
    val validators_elected_for: FloatFilterInput? = null,
    val elections_start_before: FloatFilterInput? = null,
    val elections_end_before: FloatFilterInput? = null,
    val stake_held_for: FloatFilterInput? = null,
    val OR: BlockMasterConfigP15FilterInput? = null
)

data class BlockMasterConfigP16FilterInput(
    val max_validators: IntFilterInput? = null,
    val max_main_validators: IntFilterInput? = null,
    val min_validators: IntFilterInput? = null,
    val OR: BlockMasterConfigP16FilterInput? = null
)

data class BlockMasterConfigP17FilterInput(
    val min_stake: StringFilterInput? = null,
    val max_stake: StringFilterInput? = null,
    val min_total_stake: StringFilterInput? = null,
    val max_stake_factor: FloatFilterInput? = null,
    val OR: BlockMasterConfigP17FilterInput? = null
)

data class BlockMasterConfigP18FilterInput(
    val utime_since: FloatFilterInput? = null,
    val bit_price_ps: StringFilterInput? = null,
    val cell_price_ps: StringFilterInput? = null,
    val mc_bit_price_ps: StringFilterInput? = null,
    val mc_cell_price_ps: StringFilterInput? = null,
    val OR: BlockMasterConfigP18FilterInput? = null
)

data class GasLimitsPricesFilterInput(
    val gas_price: StringFilterInput? = null,
    val gas_limit: StringFilterInput? = null,
    val special_gas_limit: StringFilterInput? = null,
    val gas_credit: StringFilterInput? = null,
    val block_gas_limit: StringFilterInput? = null,
    val freeze_due_limit: StringFilterInput? = null,
    val delete_due_limit: StringFilterInput? = null,
    val flat_gas_limit: StringFilterInput? = null,
    val flat_gas_price: StringFilterInput? = null,
    val OR: GasLimitsPricesFilterInput? = null
)

data class BlockLimitsBytesFilterInput(
    val underload: FloatFilterInput? = null,
    val soft_limit: FloatFilterInput? = null,
    val hard_limit: FloatFilterInput? = null,
    val OR: BlockLimitsBytesFilterInput? = null
)

data class BlockLimitsGasFilterInput(
    val underload: FloatFilterInput? = null,
    val soft_limit: FloatFilterInput? = null,
    val hard_limit: FloatFilterInput? = null,
    val OR: BlockLimitsGasFilterInput? = null
)

data class BlockLimitsLtDeltaFilterInput(
    val underload: FloatFilterInput? = null,
    val soft_limit: FloatFilterInput? = null,
    val hard_limit: FloatFilterInput? = null,
    val OR: BlockLimitsLtDeltaFilterInput? = null
)

data class BlockLimitsFilterInput(
    val bytes: BlockLimitsBytesFilterInput? = null,
    val gas: BlockLimitsGasFilterInput? = null,
    val lt_delta: BlockLimitsLtDeltaFilterInput? = null,
    val OR: BlockLimitsFilterInput? = null
)

data class MsgForwardPricesFilterInput(
    val lump_price: StringFilterInput? = null,
    val bit_price: StringFilterInput? = null,
    val cell_price: StringFilterInput? = null,
    val ihr_price_factor: FloatFilterInput? = null,
    val first_frac: IntFilterInput? = null,
    val next_frac: IntFilterInput? = null,
    val OR: MsgForwardPricesFilterInput? = null
)

data class BlockMasterConfigP28FilterInput(
    val shuffle_mc_validators: BooleanFilterInput? = null,
    val mc_catchain_lifetime: FloatFilterInput? = null,
    val shard_catchain_lifetime: FloatFilterInput? = null,
    val shard_validators_lifetime: FloatFilterInput? = null,
    val shard_validators_num: FloatFilterInput? = null,
    val OR: BlockMasterConfigP28FilterInput? = null
)

data class BlockMasterConfigP29FilterInput(
    val new_catchain_ids: BooleanFilterInput? = null,
    val round_candidates: FloatFilterInput? = null,
    val next_candidate_delay_ms: FloatFilterInput? = null,
    val consensus_timeout_ms: FloatFilterInput? = null,
    val fast_attempts: FloatFilterInput? = null,
    val attempt_duration: FloatFilterInput? = null,
    val catchain_max_deps: FloatFilterInput? = null,
    val max_block_bytes: FloatFilterInput? = null,
    val max_collated_bytes: FloatFilterInput? = null,
    val OR: BlockMasterConfigP29FilterInput? = null
)

data class ValidatorSetListFilterInput(
    val public_key: StringFilterInput? = null,
    val weight: StringFilterInput? = null,
    val adnl_addr: StringFilterInput? = null,
    val OR: ValidatorSetListFilterInput? = null
)

data class ValidatorSetListArrayFilterInput(
    val any: ValidatorSetListFilterInput? = null,
    val all: ValidatorSetListFilterInput? = null
)

data class ValidatorSetFilterInput(
    val utime_since: FloatFilterInput? = null,
    val utime_until: FloatFilterInput? = null,
    val total: IntFilterInput? = null,
    val total_weight: StringFilterInput? = null,
    val list: ValidatorSetListArrayFilterInput? = null,
    val OR: ValidatorSetFilterInput? = null
)

data class BlockMasterConfigP39FilterInput(
    val adnl_addr: StringFilterInput? = null,
    val temp_public_key: StringFilterInput? = null,
    val seqno: FloatFilterInput? = null,
    val valid_until: FloatFilterInput? = null,
    val signature_r: StringFilterInput? = null,
    val signature_s: StringFilterInput? = null,
    val OR: BlockMasterConfigP39FilterInput? = null
)

data class BlockMasterConfigP7ArrayFilterInput(
    val any: BlockMasterConfigP7FilterInput? = null,
    val all: BlockMasterConfigP7FilterInput? = null
)

data class FloatArrayFilterInput(
    val any: FloatFilterInput? = null,
    val all: FloatFilterInput? = null
)

data class BlockMasterConfigP12ArrayFilterInput(
    val any: BlockMasterConfigP12FilterInput? = null,
    val all: BlockMasterConfigP12FilterInput? = null
)

data class BlockMasterConfigP18ArrayFilterInput(
    val any: BlockMasterConfigP18FilterInput? = null,
    val all: BlockMasterConfigP18FilterInput? = null
)

data class StringArrayFilterInput(
    val any: StringFilterInput? = null,
    val all: StringFilterInput? = null
)

data class BlockMasterConfigP39ArrayFilterInput(
    val any: BlockMasterConfigP39FilterInput? = null,
    val all: BlockMasterConfigP39FilterInput? = null
)

data class BlockMasterConfigFilterInput(
    val p0: StringFilterInput? = null,
    val p1: StringFilterInput? = null,
    val p2: StringFilterInput? = null,
    val p3: StringFilterInput? = null,
    val p4: StringFilterInput? = null,
    val p6: BlockMasterConfigP6FilterInput? = null,
    val p7: BlockMasterConfigP7ArrayFilterInput? = null,
    val p8: BlockMasterConfigP8FilterInput? = null,
    val p9: FloatArrayFilterInput? = null,
    val p10: FloatArrayFilterInput? = null,
    val p11: BlockMasterConfigP11FilterInput? = null,
    val p12: BlockMasterConfigP12ArrayFilterInput? = null,
    val p14: BlockMasterConfigP14FilterInput? = null,
    val p15: BlockMasterConfigP15FilterInput? = null,
    val p16: BlockMasterConfigP16FilterInput? = null,
    val p17: BlockMasterConfigP17FilterInput? = null,
    val p18: BlockMasterConfigP18ArrayFilterInput? = null,
    val p20: GasLimitsPricesFilterInput? = null,
    val p21: GasLimitsPricesFilterInput? = null,
    val p22: BlockLimitsFilterInput? = null,
    val p23: BlockLimitsFilterInput? = null,
    val p24: MsgForwardPricesFilterInput? = null,
    val p25: MsgForwardPricesFilterInput? = null,
    val p28: BlockMasterConfigP28FilterInput? = null,
    val p29: BlockMasterConfigP29FilterInput? = null,
    val p31: StringArrayFilterInput? = null,
    val p32: ValidatorSetFilterInput? = null,
    val p33: ValidatorSetFilterInput? = null,
    val p34: ValidatorSetFilterInput? = null,
    val p35: ValidatorSetFilterInput? = null,
    val p36: ValidatorSetFilterInput? = null,
    val p37: ValidatorSetFilterInput? = null,
    val p39: BlockMasterConfigP39ArrayFilterInput? = null,
    val OR: BlockMasterConfigFilterInput? = null
)

data class BlockMasterShardHashesArrayFilterInput(
    val any: BlockMasterShardHashesFilterInput? = null,
    val all: BlockMasterShardHashesFilterInput? = null
)

data class BlockMasterShardFeesArrayFilterInput(
    val any: BlockMasterShardFeesFilterInput? = null,
    val all: BlockMasterShardFeesFilterInput? = null
)

data class BlockMasterPrevBlkSignaturesArrayFilterInput(
    val any: BlockMasterPrevBlkSignaturesFilterInput? = null,
    val all: BlockMasterPrevBlkSignaturesFilterInput? = null
)

data class BlockMasterFilterInput(
    val min_shard_gen_utime: FloatFilterInput? = null,
    val max_shard_gen_utime: FloatFilterInput? = null,
    val shard_hashes: BlockMasterShardHashesArrayFilterInput? = null,
    val shard_fees: BlockMasterShardFeesArrayFilterInput? = null,
    val recover_create_msg: InMsgFilterInput? = null,
    val prev_blk_signatures: BlockMasterPrevBlkSignaturesArrayFilterInput? = null,
    val config_addr: StringFilterInput? = null,
    val config: BlockMasterConfigFilterInput? = null,
    val OR: BlockMasterFilterInput? = null
)

data class BlockSignaturesSignaturesFilterInput(
    val node_id: StringFilterInput? = null,
    val r: StringFilterInput? = null,
    val s: StringFilterInput? = null,
    val OR: BlockSignaturesSignaturesFilterInput? = null
)

data class BlockSignaturesSignaturesArrayFilterInput(
    val any: BlockSignaturesSignaturesFilterInput? = null,
    val all: BlockSignaturesSignaturesFilterInput? = null
)

data class BlockSignaturesFilterInput(
    val id: StringFilterInput? = null,
    val gen_utime: FloatFilterInput? = null,
    val seq_no: FloatFilterInput? = null,
    val shard: StringFilterInput? = null,
    val workchain_id: IntFilterInput? = null,
    val proof: StringFilterInput? = null,
    val validator_list_hash_short: FloatFilterInput? = null,
    val catchain_seqno: FloatFilterInput? = null,
    val sig_weight: StringFilterInput? = null,
    val signatures: BlockSignaturesSignaturesArrayFilterInput? = null,
    val block: BlockFilterInput? = null,
    val OR: BlockSignaturesFilterInput? = null
)

data class InMsgArrayFilterInput(
    val any: InMsgFilterInput? = null,
    val all: InMsgFilterInput? = null
)

data class OutMsgArrayFilterInput(
    val any: OutMsgFilterInput? = null,
    val all: OutMsgFilterInput? = null
)

data class BlockAccountBlocksArrayFilterInput(
    val any: BlockAccountBlocksFilterInput? = null,
    val all: BlockAccountBlocksFilterInput? = null
)

data class BlockProcessingStatusEnumFilterInput(
    val eq: BlockProcessingStatusEnum? = null,
    val ne: BlockProcessingStatusEnum? = null,
    val gt: BlockProcessingStatusEnum? = null,
    val lt: BlockProcessingStatusEnum? = null,
    val ge: BlockProcessingStatusEnum? = null,
    val le: BlockProcessingStatusEnum? = null,
    val `in`: Iterable<BlockProcessingStatusEnum>? = null,
    val notIn: Iterable<BlockProcessingStatusEnum>? = null
)

data class BlockFilterInput(
    val id: StringFilterInput? = null,
    val status: IntFilterInput? = null,
    val status_name: BlockProcessingStatusEnumFilterInput? = null,
    val global_id: FloatFilterInput? = null,
    val want_split: BooleanFilterInput? = null,
    val seq_no: FloatFilterInput? = null,
    val after_merge: BooleanFilterInput? = null,
    val gen_utime: FloatFilterInput? = null,
    val gen_catchain_seqno: FloatFilterInput? = null,
    val flags: IntFilterInput? = null,
    val master_ref: ExtBlkRefFilterInput? = null,
    val prev_ref: ExtBlkRefFilterInput? = null,
    val prev_alt_ref: ExtBlkRefFilterInput? = null,
    val prev_vert_ref: ExtBlkRefFilterInput? = null,
    val prev_vert_alt_ref: ExtBlkRefFilterInput? = null,
    val version: FloatFilterInput? = null,
    val gen_validator_list_hash_short: FloatFilterInput? = null,
    val before_split: BooleanFilterInput? = null,
    val after_split: BooleanFilterInput? = null,
    val want_merge: BooleanFilterInput? = null,
    val vert_seq_no: FloatFilterInput? = null,
    val start_lt: StringFilterInput? = null,
    val end_lt: StringFilterInput? = null,
    val workchain_id: IntFilterInput? = null,
    val shard: StringFilterInput? = null,
    val min_ref_mc_seqno: FloatFilterInput? = null,
    val prev_key_block_seqno: FloatFilterInput? = null,
    val gen_software_version: FloatFilterInput? = null,
    val gen_software_capabilities: StringFilterInput? = null,
    val value_flow: BlockValueFlowFilterInput? = null,
    val in_msg_descr: InMsgArrayFilterInput? = null,
    val rand_seed: StringFilterInput? = null,
    val created_by: StringFilterInput? = null,
    val out_msg_descr: OutMsgArrayFilterInput? = null,
    val account_blocks: BlockAccountBlocksArrayFilterInput? = null,
    val tr_count: IntFilterInput? = null,
    val state_update: BlockStateUpdateFilterInput? = null,
    val master: BlockMasterFilterInput? = null,
    val key_block: BooleanFilterInput? = null,
    val boc: StringFilterInput? = null,
    val signatures: BlockSignaturesFilterInput? = null,
    val OR: BlockFilterInput? = null
)

data class AccountStatusChangeEnumFilterInput(
    val eq: AccountStatusChangeEnum? = null,
    val ne: AccountStatusChangeEnum? = null,
    val gt: AccountStatusChangeEnum? = null,
    val lt: AccountStatusChangeEnum? = null,
    val ge: AccountStatusChangeEnum? = null,
    val le: AccountStatusChangeEnum? = null,
    val `in`: Iterable<AccountStatusChangeEnum>? = null,
    val notIn: Iterable<AccountStatusChangeEnum>? = null
)

data class TransactionStorageFilterInput(
    val storage_fees_collected: StringFilterInput? = null,
    val storage_fees_due: StringFilterInput? = null,
    val status_change: IntFilterInput? = null,
    val status_change_name: AccountStatusChangeEnumFilterInput? = null,
    val OR: TransactionStorageFilterInput? = null
)

data class TransactionCreditFilterInput(
    val due_fees_collected: StringFilterInput? = null,
    val credit: StringFilterInput? = null,
    val credit_other: OtherCurrencyArrayFilterInput? = null,
    val OR: TransactionCreditFilterInput? = null
)

data class ComputeTypeEnumFilterInput(
    val eq: ComputeTypeEnum? = null,
    val ne: ComputeTypeEnum? = null,
    val gt: ComputeTypeEnum? = null,
    val lt: ComputeTypeEnum? = null,
    val ge: ComputeTypeEnum? = null,
    val le: ComputeTypeEnum? = null,
    val `in`: Iterable<ComputeTypeEnum>? = null,
    val notIn: Iterable<ComputeTypeEnum>? = null
)

data class SkipReasonEnumFilterInput(
    val eq: SkipReasonEnum? = null,
    val ne: SkipReasonEnum? = null,
    val gt: SkipReasonEnum? = null,
    val lt: SkipReasonEnum? = null,
    val ge: SkipReasonEnum? = null,
    val le: SkipReasonEnum? = null,
    val `in`: Iterable<SkipReasonEnum>? = null,
    val notIn: Iterable<SkipReasonEnum>? = null
)

data class TransactionComputeFilterInput(
    val compute_type: IntFilterInput? = null,
    val compute_type_name: ComputeTypeEnumFilterInput? = null,
    val skipped_reason: IntFilterInput? = null,
    val skipped_reason_name: SkipReasonEnumFilterInput? = null,
    val success: BooleanFilterInput? = null,
    val msg_state_used: BooleanFilterInput? = null,
    val account_activated: BooleanFilterInput? = null,
    val gas_fees: StringFilterInput? = null,
    val gas_used: StringFilterInput? = null,
    val gas_limit: StringFilterInput? = null,
    val gas_credit: IntFilterInput? = null,
    val mode: IntFilterInput? = null,
    val exit_code: IntFilterInput? = null,
    val exit_arg: IntFilterInput? = null,
    val vm_steps: FloatFilterInput? = null,
    val vm_init_state_hash: StringFilterInput? = null,
    val vm_final_state_hash: StringFilterInput? = null,
    val OR: TransactionComputeFilterInput? = null
)

data class TransactionActionFilterInput(
    val success: BooleanFilterInput? = null,
    val valid: BooleanFilterInput? = null,
    val no_funds: BooleanFilterInput? = null,
    val status_change: IntFilterInput? = null,
    val status_change_name: AccountStatusChangeEnumFilterInput? = null,
    val total_fwd_fees: StringFilterInput? = null,
    val total_action_fees: StringFilterInput? = null,
    val result_code: IntFilterInput? = null,
    val result_arg: IntFilterInput? = null,
    val tot_actions: IntFilterInput? = null,
    val spec_actions: IntFilterInput? = null,
    val skipped_actions: IntFilterInput? = null,
    val msgs_created: IntFilterInput? = null,
    val action_list_hash: StringFilterInput? = null,
    val total_msg_size_cells: FloatFilterInput? = null,
    val total_msg_size_bits: FloatFilterInput? = null,
    val OR: TransactionActionFilterInput? = null
)

data class BounceTypeEnumFilterInput(
    val eq: BounceTypeEnum? = null,
    val ne: BounceTypeEnum? = null,
    val gt: BounceTypeEnum? = null,
    val lt: BounceTypeEnum? = null,
    val ge: BounceTypeEnum? = null,
    val le: BounceTypeEnum? = null,
    val `in`: Iterable<BounceTypeEnum>? = null,
    val notIn: Iterable<BounceTypeEnum>? = null
)

data class TransactionBounceFilterInput(
    val bounce_type: IntFilterInput? = null,
    val bounce_type_name: BounceTypeEnumFilterInput? = null,
    val msg_size_cells: FloatFilterInput? = null,
    val msg_size_bits: FloatFilterInput? = null,
    val req_fwd_fees: StringFilterInput? = null,
    val msg_fees: StringFilterInput? = null,
    val fwd_fees: StringFilterInput? = null,
    val OR: TransactionBounceFilterInput? = null
)

data class TransactionSplitInfoFilterInput(
    val cur_shard_pfx_len: IntFilterInput? = null,
    val acc_split_depth: IntFilterInput? = null,
    val this_addr: StringFilterInput? = null,
    val sibling_addr: StringFilterInput? = null,
    val OR: TransactionSplitInfoFilterInput? = null
)

data class MessageArrayFilterInput(
    val any: MessageFilterInput? = null,
    val all: MessageFilterInput? = null
)

data class TransactionTypeEnumFilterInput(
    val eq: TransactionTypeEnum? = null,
    val ne: TransactionTypeEnum? = null,
    val gt: TransactionTypeEnum? = null,
    val lt: TransactionTypeEnum? = null,
    val ge: TransactionTypeEnum? = null,
    val le: TransactionTypeEnum? = null,
    val `in`: Iterable<TransactionTypeEnum>? = null,
    val notIn: Iterable<TransactionTypeEnum>? = null
)

data class TransactionProcessingStatusEnumFilterInput(
    val eq: TransactionProcessingStatusEnum? = null,
    val ne: TransactionProcessingStatusEnum? = null,
    val gt: TransactionProcessingStatusEnum? = null,
    val lt: TransactionProcessingStatusEnum? = null,
    val ge: TransactionProcessingStatusEnum? = null,
    val le: TransactionProcessingStatusEnum? = null,
    val `in`: Iterable<TransactionProcessingStatusEnum>? = null,
    val notIn: Iterable<TransactionProcessingStatusEnum>? = null
)

data class AccountStatusEnumFilterInput(
    val eq: AccountStatusEnum? = null,
    val ne: AccountStatusEnum? = null,
    val gt: AccountStatusEnum? = null,
    val lt: AccountStatusEnum? = null,
    val ge: AccountStatusEnum? = null,
    val le: AccountStatusEnum? = null,
    val `in`: Iterable<AccountStatusEnum>? = null,
    val notIn: Iterable<AccountStatusEnum>? = null
)

data class TransactionFilterInput(
    val id: StringFilterInput? = null,
    val tr_type: IntFilterInput? = null,
    val tr_type_name: TransactionTypeEnumFilterInput? = null,
    val status: IntFilterInput? = null,
    val status_name: TransactionProcessingStatusEnumFilterInput? = null,
    val block_id: StringFilterInput? = null,
    val block: BlockFilterInput? = null,
    val account_addr: StringFilterInput? = null,
    val workchain_id: IntFilterInput? = null,
    val lt: StringFilterInput? = null,
    val prev_trans_hash: StringFilterInput? = null,
    val prev_trans_lt: StringFilterInput? = null,
    val now: FloatFilterInput? = null,
    val outmsg_cnt: IntFilterInput? = null,
    val orig_status: IntFilterInput? = null,
    val orig_status_name: AccountStatusEnumFilterInput? = null,
    val end_status: IntFilterInput? = null,
    val end_status_name: AccountStatusEnumFilterInput? = null,
    val in_msg: StringFilterInput? = null,
    val in_message: MessageFilterInput? = null,
    val out_msgs: StringArrayFilterInput? = null,
    val out_messages: MessageArrayFilterInput? = null,
    val total_fees: StringFilterInput? = null,
    val total_fees_other: OtherCurrencyArrayFilterInput? = null,
    val old_hash: StringFilterInput? = null,
    val new_hash: StringFilterInput? = null,
    val credit_first: BooleanFilterInput? = null,
    val storage: TransactionStorageFilterInput? = null,
    val credit: TransactionCreditFilterInput? = null,
    val compute: TransactionComputeFilterInput? = null,
    val action: TransactionActionFilterInput? = null,
    val bounce: TransactionBounceFilterInput? = null,
    val aborted: BooleanFilterInput? = null,
    val destroyed: BooleanFilterInput? = null,
    val tt: StringFilterInput? = null,
    val split_info: TransactionSplitInfoFilterInput? = null,
    val prepare_transaction: StringFilterInput? = null,
    val installed: BooleanFilterInput? = null,
    val proof: StringFilterInput? = null,
    val boc: StringFilterInput? = null,
    val balance_delta: StringFilterInput? = null,
    val balance_delta_other: OtherCurrencyArrayFilterInput? = null,
    val OR: TransactionFilterInput? = null
)

data class MessageTypeEnumFilterInput(
    val eq: MessageTypeEnum? = null,
    val ne: MessageTypeEnum? = null,
    val gt: MessageTypeEnum? = null,
    val lt: MessageTypeEnum? = null,
    val ge: MessageTypeEnum? = null,
    val le: MessageTypeEnum? = null,
    val `in`: Iterable<MessageTypeEnum>? = null,
    val notIn: Iterable<MessageTypeEnum>? = null
)

data class MessageProcessingStatusEnumFilterInput(
    val eq: MessageProcessingStatusEnum? = null,
    val ne: MessageProcessingStatusEnum? = null,
    val gt: MessageProcessingStatusEnum? = null,
    val lt: MessageProcessingStatusEnum? = null,
    val ge: MessageProcessingStatusEnum? = null,
    val le: MessageProcessingStatusEnum? = null,
    val `in`: Iterable<MessageProcessingStatusEnum>? = null,
    val notIn: Iterable<MessageProcessingStatusEnum>? = null
)

data class MessageFilterInput(
    val id: StringFilterInput? = null,
    val msg_type: IntFilterInput? = null,
    val msg_type_name: MessageTypeEnumFilterInput? = null,
    val status: IntFilterInput? = null,
    val status_name: MessageProcessingStatusEnumFilterInput? = null,
    val block_id: StringFilterInput? = null,
    val block: BlockFilterInput? = null,
    val body: StringFilterInput? = null,
    val body_hash: StringFilterInput? = null,
    val split_depth: IntFilterInput? = null,
    val tick: BooleanFilterInput? = null,
    val tock: BooleanFilterInput? = null,
    val code: StringFilterInput? = null,
    val code_hash: StringFilterInput? = null,
    val data: StringFilterInput? = null,
    val data_hash: StringFilterInput? = null,
    val library: StringFilterInput? = null,
    val library_hash: StringFilterInput? = null,
    val src: StringFilterInput? = null,
    val dst: StringFilterInput? = null,
    val src_workchain_id: IntFilterInput? = null,
    val dst_workchain_id: IntFilterInput? = null,
    val created_lt: StringFilterInput? = null,
    val created_at: FloatFilterInput? = null,
    val ihr_disabled: BooleanFilterInput? = null,
    val ihr_fee: StringFilterInput? = null,
    val fwd_fee: StringFilterInput? = null,
    val import_fee: StringFilterInput? = null,
    val bounce: BooleanFilterInput? = null,
    val bounced: BooleanFilterInput? = null,
    val value: StringFilterInput? = null,
    val value_other: OtherCurrencyArrayFilterInput? = null,
    val proof: StringFilterInput? = null,
    val boc: StringFilterInput? = null,
    val src_transaction: TransactionFilterInput? = null,
    val dst_transaction: TransactionFilterInput? = null,
    val OR: MessageFilterInput? = null
)

data class AccountFilterInput(
    val id: StringFilterInput? = null,
    val workchain_id: IntFilterInput? = null,
    val acc_type: IntFilterInput? = null,
    val acc_type_name: AccountStatusEnumFilterInput? = null,
    val last_paid: FloatFilterInput? = null,
    val due_payment: StringFilterInput? = null,
    val last_trans_lt: StringFilterInput? = null,
    val balance: StringFilterInput? = null,
    val balance_other: OtherCurrencyArrayFilterInput? = null,
    val split_depth: IntFilterInput? = null,
    val tick: BooleanFilterInput? = null,
    val tock: BooleanFilterInput? = null,
    val code: StringFilterInput? = null,
    val code_hash: StringFilterInput? = null,
    val data: StringFilterInput? = null,
    val data_hash: StringFilterInput? = null,
    val library: StringFilterInput? = null,
    val library_hash: StringFilterInput? = null,
    val proof: StringFilterInput? = null,
    val boc: StringFilterInput? = null,
    val state_hash: StringFilterInput? = null,
    val OR: AccountFilterInput? = null
)

/** Specify sort order direction */
enum class QueryOrderByDirection(val label: String) {
    Asc("ASC"),
    Desc("DESC");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): QueryOrderByDirection? {
            return values().find { it.label == label }
        }
    }
}

data class QueryOrderByInput(
    val path: String? = null,
    val direction: QueryOrderByDirection? = null
)

data class QueryBlocks_SignaturesArgs(
    val filter: BlockSignaturesFilterInput? = null,
    val orderBy: Iterable<QueryOrderByInput>? = null,
    val limit: Int? = null,
    val timeout: Float? = null,
    val accessKey: String? = null,
    val operationId: String? = null
)

data class QueryBlocksArgs(
    val filter: BlockFilterInput? = null,
    val orderBy: Iterable<QueryOrderByInput>? = null,
    val limit: Int? = null,
    val timeout: Float? = null,
    val accessKey: String? = null,
    val operationId: String? = null
)

data class QueryTransactionsArgs(
    val filter: TransactionFilterInput? = null,
    val orderBy: Iterable<QueryOrderByInput>? = null,
    val limit: Int? = null,
    val timeout: Float? = null,
    val accessKey: String? = null,
    val operationId: String? = null
)

data class QueryMessagesArgs(
    val filter: MessageFilterInput? = null,
    val orderBy: Iterable<QueryOrderByInput>? = null,
    val limit: Int? = null,
    val timeout: Float? = null,
    val accessKey: String? = null,
    val operationId: String? = null
)

data class QueryAccountsArgs(
    val filter: AccountFilterInput? = null,
    val orderBy: Iterable<QueryOrderByInput>? = null,
    val limit: Int? = null,
    val timeout: Float? = null,
    val accessKey: String? = null,
    val operationId: String? = null
)

data class QueryGetAccountsCountArgs(
    val accessKey: String? = null
)

data class QueryGetTransactionsCountArgs(
    val accessKey: String? = null
)

data class QueryGetAccountsTotalBalanceArgs(
    val accessKey: String? = null
)

data class QueryAggregateBlockSignaturesArgs(
    val filter: BlockSignaturesFilterInput? = null,
    val fields: Iterable<FieldAggregationInput>? = null,
    val accessKey: String? = null
)

data class QueryAggregateBlocksArgs(
    val filter: BlockFilterInput? = null,
    val fields: Iterable<FieldAggregationInput>? = null,
    val accessKey: String? = null
)

data class QueryAggregateTransactionsArgs(
    val filter: TransactionFilterInput? = null,
    val fields: Iterable<FieldAggregationInput>? = null,
    val accessKey: String? = null
)

data class QueryAggregateMessagesArgs(
    val filter: MessageFilterInput? = null,
    val fields: Iterable<FieldAggregationInput>? = null,
    val accessKey: String? = null
)

data class QueryAggregateAccountsArgs(
    val filter: AccountFilterInput? = null,
    val fields: Iterable<FieldAggregationInput>? = null,
    val accessKey: String? = null
)

data class Query(
    val blocks_signatures: Iterable<BlockSignatures>?,
    val blocks: Iterable<Block>?,
    val transactions: Iterable<Transaction>?,
    val messages: Iterable<Message>?,
    val accounts: Iterable<Account>?,
    val info: Info?,
    val getAccountsCount: Float?,
    val getTransactionsCount: Float?,
    val getAccountsTotalBalance: String?,
    val getManagementAccessKey: String?,
    val aggregateBlockSignatures: Iterable<String>?,
    val aggregateBlocks: Iterable<String>?,
    val aggregateTransactions: Iterable<String>?,
    val aggregateMessages: Iterable<String>?,
    val aggregateAccounts: Iterable<String>?
)

data class SubscriptionBlocks_SignaturesArgs(
    val filter: BlockSignaturesFilterInput? = null,
    val accessKey: String? = null
)

data class SubscriptionBlocksArgs(
    val filter: BlockFilterInput? = null,
    val accessKey: String? = null
)

data class SubscriptionTransactionsArgs(
    val filter: TransactionFilterInput? = null,
    val accessKey: String? = null
)

data class SubscriptionMessagesArgs(
    val filter: MessageFilterInput? = null,
    val accessKey: String? = null
)

data class SubscriptionAccountsArgs(
    val filter: AccountFilterInput? = null,
    val accessKey: String? = null
)

data class Subscription(
    val blocks_signatures: BlockSignatures?,
    val blocks: Block?,
    val transactions: Transaction?,
    val messages: Message?,
    val accounts: Account?
)

data class RequestInput(
    val id: String? = null,
    val body: String? = null,
    val expireAt: Float? = null
)

data class Info(
    val version: String?,
    val time: Float?
)

/** Aggregation function used to collect aggregated value */
enum class AggregationFn(val label: String) {
    Count("COUNT"),
    Min("MIN"),
    Max("MAX"),
    Sum("SUM"),
    Average("AVERAGE");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): AggregationFn? {
            return values().find { it.label == label }
        }
    }
}

data class FieldAggregationInput(
    val field: String? = null,
    val fn: AggregationFn? = null
)

data class AccessKeyInput(
    val key: String? = null,
    val restrictToAccounts: Iterable<String>? = null
)

data class MutationPostRequestsArgs(
    val requests: Iterable<RequestInput>? = null,
    val accessKey: String? = null
)

data class MutationRegisterAccessKeysArgs(
    val account: String? = null,
    val keys: Iterable<AccessKeyInput>? = null,
    val signedManagementAccessKey: String? = null
)

data class MutationRevokeAccessKeysArgs(
    val account: String? = null,
    val keys: Iterable<String>? = null,
    val signedManagementAccessKey: String? = null
)

data class MutationFinishOperationsArgs(
    val operationIds: Iterable<String>? = null
)

data class Mutation(
    val postRequests: Iterable<String>?,
    val registerAccessKeys: Int?,
    val revokeAccessKeys: Int?,
    val finishOperations: Int?
)

enum class CacheControlScope(val label: String) {
    Public("PUBLIC"),
    Private("PRIVATE");

    companion object {
        @JvmStatic
        fun valueOfLabel(label: String): CacheControlScope? {
            return values().find { it.label == label }
        }
    }
}