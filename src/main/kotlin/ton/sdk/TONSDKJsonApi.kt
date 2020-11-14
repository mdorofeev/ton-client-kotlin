package ton.sdk

object TONSDKJsonApi {
    external fun createContext(config: String): String
    external fun destroyContext(context: Int)
    external fun jsonRequestAsync(context: Int, requestId: Int, method: String, paramsJson: String, onResult: Any)
}
