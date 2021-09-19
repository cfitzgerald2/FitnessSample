package com.fitz.usecases.repository

/**
 * generic use case for accessing, updating, and deleting data from a repo
 */

interface DataAccessor<T> {
    // return all from the data source
    suspend fun getAll(): List<T>

    // return all that match the search criteria from the data source
    suspend fun getAll(searchArg: Any): List<T>

    // add an item to the data source
    suspend fun add(addedValue: T)

    // delete an item from the data source
    suspend fun delete(itemToDelete: T)
}