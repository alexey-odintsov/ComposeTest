package com.example.composetest

data class Resource<out T>(val status: Status, val data: T?, val errorMessage: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        UPDATING
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> updating(data: T?): Resource<T> {
            return Resource(Status.UPDATING, data, null)
        }
    }
}