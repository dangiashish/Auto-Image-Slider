package com.codebyashish.autoimageslider

class ExceptionsClass : Exception {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)

    var errorCode = 0
        private set

    constructor(message: String?, errorCode: Int) : super(message) {
        this.errorCode = errorCode
    }
}
