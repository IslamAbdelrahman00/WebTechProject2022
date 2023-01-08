package de.htwberlin.webtech.webproject.Service;

import de.htwberlin.webtech.webproject.Entity.ListEntity;
import de.htwberlin.webtech.webproject.Repo.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {

    private final TodoListRepository todoRepository;
    private final ItemService itemService;
    private RestTemplate restTemplate;

    @Autowired
    public TodoListService(TodoListRepository repository, ItemService itemService) {
        this.todoRepository = repository;
        this.itemService = itemService;
    }

    //save the  list to DB
    public  ListEntity saveList(ListEntity todolist) {

        return todoRepository.save(todolist);

    }

    //fetch atodo list by its id
    public  Optional<ListEntity> findById(Long todoListId) {
        return todoRepository.findById(todoListId);
    }

    //fetch all todolists
    public List<ListEntity> findAll() {

        var it = todoRepository.findAll();

        var sList = new ArrayList<ListEntity>();
        for (ListEntity s : it) {
           sList.add(s);
        }
        return sList;
    }

    //get the total number of todolists available
    public Long count() {
        return todoRepository.count();
    }

    //delete atodo list by its id
    public  void deleteById(Long listid) {
        todoRepository.deleteById(listid);
    }
}