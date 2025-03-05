package com.example.todo1.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Data class representing the JSON structure
data class Todo(
    var userId: Int,
    var id: Int,
    var title: String,
    var completed : Boolean
)

// Base URL for the API service
const val BASE_URL = "https://jsonplaceholder.typicode.com"

// Retrofit interface to define API endpoints
interface TodosApi {
    @GET("todos")
    suspend fun getTodos() : List<Todo>

    companion object {
        var todosService: TodosApi? = null

        fun getInstance(): TodosApi {
            if (todosService ===null) {
                todosService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(TodosApi::class.java)
            }
            return todosService!!
        }
    }
}