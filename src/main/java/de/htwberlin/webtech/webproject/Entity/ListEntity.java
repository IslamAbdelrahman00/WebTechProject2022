package de.htwberlin.webtech.webproject.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "todo_list")
public class ListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long list_id;


    @Column(name = "list_name", nullable = false)
    private String list_name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "list_id")
    private List<ItemEntity> listItems = new ArrayList<ItemEntity>();

    public ListEntity(String list_name) {
        this.list_name = list_name;
    }

    public ListEntity() {
    }


    public Long getList_id() {
        return list_id;
    }


    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    //fetch all items in a list
    public List<ItemEntity> getListItems() {
        return listItems;
    }

    //set a list of items
    public void setListItems(List<ItemEntity> listItems) {
        this.listItems = listItems;
    }

    //adds a list Item by name
    public void addListItem(String taskname) {
        ItemEntity listItem = new ItemEntity(taskname);
        listItems.add(listItem);
    }

    //adds a list Item
    public void addListItem(ItemEntity item) {
        listItems.add(item);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("todo_list{");
        sb.append("ID=").append(list_id);
        sb.append(", list_name='").append(list_name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListEntity todolist = (ListEntity) o;
        return Objects.equals(list_id, todolist.list_id) &&
                Objects.equals(list_name, todolist.list_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list_id, list_name);
    }


}