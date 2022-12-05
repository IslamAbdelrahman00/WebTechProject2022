package de.htwberlin.webtech.webproject.Controller;


import de.htwberlin.webtech.webproject.Config.Endpoints;
import de.htwberlin.webtech.webproject.Config.ViewNames;
import de.htwberlin.webtech.webproject.Entity.ItemEntity;
import de.htwberlin.webtech.webproject.Entity.ListDTO;
import de.htwberlin.webtech.webproject.Entity.ListEntity;
import de.htwberlin.webtech.webproject.Service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ControllerForTemplates {


    @Autowired
    private TodoListService todoListService;

    //Homepage of the web app
    @GetMapping(path = Endpoints.Site.INDEX)
    public ModelAndView showHomePage(){
        return new ModelAndView(ViewNames.INDEX);
    }

    //about page for public view
    @GetMapping(path = Endpoints.Site.ABOUT)
    public ModelAndView showAbout(){
        return new ModelAndView(ViewNames.ABOUT);
    }

    //contact page for public view
    @GetMapping(path = Endpoints.Site.CONTACT)
    public ModelAndView showContact(){
        return new ModelAndView(ViewNames.CONTACT);
    }

    //get the forma for creating a list
    @GetMapping(path = Endpoints.Site.LIST)
    public ModelAndView createTodoListForm(Model model) {
        model.addAttribute("TOdoList", new ListEntity());
        return new ModelAndView(ViewNames.LIST);
    }

    //create a todolist
    @PostMapping(path = Endpoints.Site.LIST)
    public String createTodoList(  Model model,@RequestBody ListDTO listDTO) {
        ListEntity TodoList = new ListEntity(listDTO.list_name);
        var it = listDTO.list_items;
        it.forEach(item -> {
                    String itemName = item;
                    ItemEntity listItem = new ItemEntity(itemName);
                    TodoList.addListItem(listItem);
                }
        );
        todoListService.saveList(TodoList);
        model.addAttribute("TodoList", TodoList);
        return "redirect:/createlist";
    }

    //get all lists created
    @GetMapping(path = Endpoints.Site.ALL_LISTS)
    public ModelAndView listsTable(Model model) {
        List<ListEntity> slists = todoListService.findAll();
        model.addAttribute("slists", slists);
        return new ModelAndView(ViewNames.ALL_LISTS);
    }
}

