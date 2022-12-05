package de.htwberlin.webtech.webproject.Entity;

import java.util.ArrayList;
import java.util.List;

public class ListDTO {

    public String list_name;
    public List<String> list_items = new ArrayList<String>();

    public ListDTO() {
    }

    public ListDTO(String author, String list_name, List<String> list_items) {
        this.list_name = list_name;
        this.list_items = list_items;
    }
}
