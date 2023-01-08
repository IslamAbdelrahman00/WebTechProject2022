package de.htwberlin.webtech.webproject;

import de.htwberlin.webtech.webproject.Entity.ItemEntity;
import de.htwberlin.webtech.webproject.Entity.ListEntity;
import de.htwberlin.webtech.webproject.Repo.ItemRepository;
import de.htwberlin.webtech.webproject.Repo.TodoListRepository;
import de.htwberlin.webtech.webproject.Service.ItemService;
import de.htwberlin.webtech.webproject.Service.TodoListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;


@SpringBootTest
public class Tests {

    @Autowired
    private ItemService itemTestService;

    @MockBean
    private ItemRepository itemRepo;

    @Autowired
    private TodoListService todoListTestService;

    @MockBean
    private TodoListRepository repository;


    @Test
    void shouldReturnAllLists() throws Exception {
        //given
        List<String> items = new ArrayList<String>();
        var dto1 = new ListEntity("Test List1");
        var dto2 = new ListEntity("Test List2");
        //when
        doReturn(List.of(dto1, dto2)).when(repository).findAll();
        List<ListEntity> allLists = todoListTestService.findAll();
        //then
        Assertions.assertSame(allLists.get(1).getList_name(), "Test List2", "The wrong list was returned.");
        Assertions.assertSame(allLists.size(), 2, "The number of Lists is wrong.");


        //var dtoAsString = new ObjectMapper().writeValueAsString(dto);
        //this.mockMvc
        //      .perform(MockMvcRequestBuilders
        //            .post(Endpoints.Rest.Todo_LIST + "/create")
        //          .contentType(MediaType.APPLICATION_JSON)
        //        .content(dtoAsString)
        // ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void setListName() {
        //given
        var dto1 = new ListEntity("Test List1");
        doReturn(List.of(dto1)).when(repository).findAll();
        //when
        List<ListEntity> allLists = todoListTestService.findAll();
        allLists.get(0).setList_name("list2");
        //then
        Assertions.assertSame(allLists.get(0).getList_name(), "list2", "The wrong list was returned.");
    }

    @Test
    void addingitemstoList() {
        //given
        var dto1 = new ListEntity("Test List1");
        doReturn(List.of(dto1)).when(repository).findAll();
        //when
        List<ListEntity> allLists = todoListTestService.findAll();
        allLists.get(0).addListItem(" test Task");
        //then
        Assertions.assertSame(allLists.get(0).getListItems().size(), 1, "The wrong number was returned.");
        Assertions.assertSame(allLists.get(0).getListItems().get(0).getItem_name(), " test Task", "The wrong item was returned.");

    }

    @Test
    void settingListItems() {
        //given
        var dto1 = new ListEntity("Test List1");
        doReturn(List.of(dto1)).when(repository).findAll();
        List<ListEntity> allLists = todoListTestService.findAll();
        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("Firts Task"));
        items.add(new ItemEntity("second Task"));

        //when
        allLists.get(0).setListItems(items);

        //then
        Assertions.assertSame(allLists.get(0).getListItems().size(), 2, "The wrong number was returned.");
        Assertions.assertSame(allLists.get(0).getListItems().get(0).getItem_name(), "Firts Task", "The wrong item was returned.");
        Assertions.assertSame(allLists.get(0).getListItems().get(1).getItem_name(), "second Task", "The wrong item was returned.");

    }



    @Test
    @DisplayName("should find item 1 ")
    void testFindByListId() {
        //given
        var item1 = new ItemEntity("Go to the movies ");
        item1.setList_id(1L);
        doReturn(List.of(item1)).when(itemRepo).findAll();
        //when
        List<ItemEntity> test = itemTestService.findBytodoListId(1L);

        //then
        Assertions.assertSame(test.size(), 1, "The number of itmes isn't not correct.");
        Assertions.assertSame(test.get(0).getItem_name(), "Go to the movies ", "incorrect item");
    }
}

