package com.example.todo1.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo1.model.Todo
import com.example.todo1.model.TodosApi
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    var todos = mutableStateListOf<Todo>()
    private set
    init {
        getTodosList()
    }

    private fun getTodosList(){
        viewModelScope.launch {
            //var todosApi: TodosApi? = null
            try {
                val todosApi = TodosApi.getInstance()
                todos.clear()
                todos.addAll(todosApi.getTodos())
            } catch (e:Exception) {
                Log.d("Error", e.message.toString())
            }
        }
    }
}