package com.barisatalay.domain.model.exception

open class BaseException(val originalException: Throwable?) : Throwable() {
    open class UnknownException(originalException: Throwable?) : BaseException(originalException)
    open class DeveloperException : BaseException(Exception("DeveloperException"))
    class JsonException(originalException: Throwable) : BaseException(originalException)
    class TimeoutException(originalException: Throwable) : BaseException(originalException)
    class SSLHandshakeException(originalException: Throwable) : BaseException(originalException)
    class ConnectException(originalException: Throwable) : BaseException(originalException)
    class SSLException(originalException: Throwable) : BaseException(originalException)
    class SocketTimeoutException(originalException: Throwable) : BaseException(originalException)
    class UnknownHostException(originalException: Throwable) : BaseException(originalException)

    class SolScanMapperException() : BaseException(Exception("Unexpected exeption"))

}