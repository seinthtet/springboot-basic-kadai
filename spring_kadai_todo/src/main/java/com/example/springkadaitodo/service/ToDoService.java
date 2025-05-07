package com.example.springkadaitodo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.repository.ToDoRepository;


@Service
public class ToDoService {
	
@Autowired
	private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

   
    
    public List<ToDo> findAll() {
        return toDoRepository.findAll();
    }
}
	

