package de.htwberlin.webtech.webproject.Controller;


import de.htwberlin.webtech.webproject.Config.Endpoints;
import de.htwberlin.webtech.webproject.Entity.ItemEntity;
import de.htwberlin.webtech.webproject.Entity.ListDTO;
import de.htwberlin.webtech.webproject.Entity.ListEntity;
import de.htwberlin.webtech.webproject.Exception.ResourceNotFoundException;
import de.htwberlin.webtech.webproject.Service.ItemService;
import de.htwberlin.webtech.webproject.Service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ControllerForRest {

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private ItemService itemService;

    //fetch out all the todolists
    @GetMapping(path = Endpoints.Rest.Todo_LIST)
    public ResponseEntity<List<ListEntity>> getAllSTodoLists() {
        var allLists = todoListService.findAll();
        return ResponseEntity.ok(allLists);
    }

    //create a todolist
    @PostMapping(path = Endpoints.Rest.Todo_LIST + "/create")
    public ResponseEntity<ListEntity> createTodoListRest(@RequestBody ListDTO listDTO) {
        ListEntity TodoList = new ListEntity(listDTO.list_name);
        var it = listDTO.list_items;
        it.forEach(item -> {
                    String itemName = item;
                    ItemEntity listItem = new ItemEntity(itemName);
                    TodoList.addListItem(listItem);
                }
        );
        todoListService.saveList(TodoList);
        return ResponseEntity.ok(TodoList);
    }

    //fetch Todolist with the given id
    @GetMapping(path = Endpoints.Rest.Todo_LIST + "/{id}")
    public ResponseEntity<ListEntity> getTodoListById(@PathVariable(value = "id") Long todoListId)
            throws ResourceNotFoundException {
        ListEntity TodoList = todoListService.findById(todoListId)
                .orElseThrow(() -> new ResourceNotFoundException("todo list with the id  : " + todoListId + " deon't exist"));
        return ResponseEntity.ok().body(TodoList);
    }


    //delte a Todolist with input id
    @RequestMapping(path = Endpoints.Rest.Todo_LIST + "/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteList(@PathVariable("id") Long todoListId) throws ResourceNotFoundException {
        try {
            itemService.deleteBytodoListId(todoListId);
            todoListService.deleteById(todoListId);
            return "redirect:/alllists";
        } catch (Exception e) {
            throw new ResourceNotFoundException("todo list with the id : " + todoListId + " doesn't exist.");
        }
    }

    //delete an item from a Todolist
    @RequestMapping(path = Endpoints.Rest.ITEM + "/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<ItemEntity> deleteItem(@PathVariable("id") Long itemId) throws ResourceNotFoundException {
        try {
            itemService.deleteById(itemId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Item with the id : " + itemId + " doesn't exist.");
        }
    }

    //update an item in a Todolist
    @PutMapping(path = Endpoints.Rest.ITEM + "/updatename/{id}/{itemName}")
    public ResponseEntity<ItemEntity> updateItemName(@PathVariable(value = "id") Long itemId,
                                                     @PathVariable(value = "itemName") String itemName) throws ResourceNotFoundException {
        ItemEntity item = itemService.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item with the id : " + itemId + " doesn't exist."));
        item.setItem_name(itemName);
        final ItemEntity updatedItem = itemService.saveListItem(item);
        return ResponseEntity.ok(updatedItem);
    }

    //add new item to an existing todolist
    @RequestMapping(path = Endpoints.Rest.Todo_LIST + "/updatelist/{id}/{itemName}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<ListEntity> addItem(@PathVariable(value = "id") Long todoListId,
                                              @PathVariable(value = "itemName") String itemName) throws ResourceNotFoundException {
        ListEntity todoList = todoListService.findById(todoListId)
                .orElseThrow(() -> new ResourceNotFoundException("Todolist with the id : " + todoListId + " doesn't exist."));

        ItemEntity listItem = new ItemEntity(itemName);
        todoList.addListItem(listItem);
        final ListEntity updatedList = todoListService.saveList(todoList);
        return ResponseEntity.ok(updatedList);
    }
}