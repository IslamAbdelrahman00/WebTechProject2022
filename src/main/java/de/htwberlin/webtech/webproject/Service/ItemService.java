package de.htwberlin.webtech.webproject.Service;


import de.htwberlin.webtech.webproject.Entity.ItemEntity;
import de.htwberlin.webtech.webproject.Repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ItemService {

    @Autowired
    private final ItemRepository itemRepository;
    private RestTemplate restTemplate;

    public ItemService(ItemRepository repository) {
        this.itemRepository = repository;
    }

    //save the item to DB
    public ItemEntity saveListItem(ItemEntity listItem) {

        return itemRepository.save(listItem);

    }

    //delete an item by its id
    public void deleteById(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    //delete list items by todoList id
    public void deleteBytodoListId(Long todoListId) {

        var it = itemRepository.findAll();
        it.forEach(e -> {
            if (e.getList_id().equals(todoListId)) {
                itemRepository.deleteById(e.getItem_id());
            }
        });
    }

    //find list items by  todolist id
    public List<ItemEntity> findBytodoListId(Long todoListId) {

        var it = itemRepository.findAll();

        var sList = new ArrayList<ItemEntity>();
        it.forEach(e -> {
            if (e.getList_id().equals(todoListId)) {
                sList.add(e);
            }
        });
        return sList;
    }

    //find an item by its id
    public Optional<ItemEntity> findById(Long itemId) {
        return itemRepository.findById(itemId);
    }


}