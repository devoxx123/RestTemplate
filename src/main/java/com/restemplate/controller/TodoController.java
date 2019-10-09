package com.restemplate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.restemplate.domain.TodoModel;
import com.restemplate.domain.TodoModelRequestBody;

@RestController
public class TodoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/todos") // Return JSON Array
	public List<TodoModel> getTodos() {
		String todoUrl = "https://jsonplaceholder.typicode.com/todos";
		ResponseEntity<List<TodoModel>> response = restTemplate.exchange(todoUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TodoModel>>() {
				});
		List<TodoModel> todoList = response.getBody();
		return todoList;
	}

	@GetMapping("/todos/{id}") // Return a TodoModel Single JSON Object
	public TodoModel getTodo(@PathVariable int id) {
		String todoUrl = "https://jsonplaceholder.typicode.com/todos/" + id;
		ResponseEntity<TodoModel> reponse = restTemplate.exchange(todoUrl, HttpMethod.GET, null, TodoModel.class);
		TodoModel todoList = reponse.getBody();
		return todoList;
	}

	@GetMapping("/todos/json") // Return a TodoModel Plain JSON String
	public String getTodosJson() {
		String todoUrl = "https://jsonplaceholder.typicode.com/todos";
		ResponseEntity<String> reponse = restTemplate.exchange(todoUrl, HttpMethod.GET, null, String.class);
		return reponse.getBody();
	}

	@PostMapping("/todos") // Create a new TodoModel Request
	public TodoModel createTodos(@RequestBody TodoModelRequestBody todoModelRequestBody) {
		String theUrl = "https://jsonplaceholder.typicode.com/todos";
		HttpEntity<TodoModelRequestBody> httpEntity = new HttpEntity<>(todoModelRequestBody);
		ResponseEntity<TodoModel> reponse = restTemplate.exchange(theUrl, HttpMethod.POST, httpEntity, TodoModel.class);
		return reponse.getBody();
	}

	@PutMapping("/todos/{id}") // Update a TodoModel Request
	public TodoModel updateTodo(@RequestBody TodoModelRequestBody todoModelRequestBody, @PathVariable int id) {
		String theUrl = "https://jsonplaceholder.typicode.com/todos/" + id;
		HttpEntity<TodoModelRequestBody> httpEntity = new HttpEntity<>(todoModelRequestBody);
		ResponseEntity<TodoModel> reponse = restTemplate.exchange(theUrl, HttpMethod.PUT, httpEntity, TodoModel.class);
		return reponse.getBody();
	}

	@DeleteMapping("/todos/{id}") // Delete a TodoModel Single JSON Object
	public TodoModel deleteTodo(@PathVariable int id) {
		String todoUrl = "https://jsonplaceholder.typicode.com/todos/" + id;
		ResponseEntity<TodoModel> reponse = restTemplate.exchange(todoUrl, HttpMethod.DELETE, null, TodoModel.class);
		TodoModel todoList = reponse.getBody();
		return todoList;
	}

}
