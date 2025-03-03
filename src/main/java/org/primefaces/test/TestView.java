package org.primefaces.test;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Named
@ViewScoped
public class TestView implements Serializable {
    private String string;
    private Integer integer;
    private BigDecimal decimal;
    private LocalDateTime localDateTime;
    private List<TestObject> list;

    private TestObject selectedItem1;
    private MenuModel menuModel1 = new DefaultMenuModel();

    private TestObject selectedItem2;
    private MenuModel menuModel2 = new DefaultMenuModel();

    @PostConstruct
    public void init() {
        string = "Welcome to PrimeFaces!!!";
        list = new ArrayList<>(Arrays.asList(
                new TestObject("Thriller", "Michael Jackson", 1982),
                new TestObject("Back in Black", "AC/DC", 1980),
                new TestObject("The Bodyguard", "Whitney Houston", 1992),
                new TestObject("The Dark Side of the Moon", "Pink Floyd", 1973)
        ));

        DefaultMenuItem itemDefault = new DefaultMenuItem();
        itemDefault.setValue("default menu item");
        menuModel2.getElements().add(itemDefault);
    }

    public void onContextMenuShow1(SelectEvent<?> event) {
        selectedItem1 = (TestObject) event.getObject();
        menuModel1 = createMenuModel(selectedItem1);
        PrimeFaces.current().ajax().update("frmTest:cm1");
    }

    public void onContextMenuShow2(SelectEvent<?> event) {
        selectedItem2 = (TestObject) event.getObject();
        menuModel2 = createMenuModel(selectedItem2);
        PrimeFaces.current().ajax().update("frmTest:cm2");
    }

    private MenuModel createMenuModel(TestObject selectedItem) {
        MenuModel menuModel = new DefaultMenuModel();
        if (selectedItem != null) {
            if (selectedItem.getName().equals("Thriller")) {
                DefaultMenuItem item = new DefaultMenuItem();
                item.setValue("menu item for '" + selectedItem.getName() + "'");
                menuModel.getElements().add(item);
            } else if (selectedItem.getName().equals("Back in Black")) {
                DefaultMenuItem item = new DefaultMenuItem();
                item.setValue("menu item for '" + selectedItem.getName() + "'");
                menuModel.getElements().add(item);

                DefaultMenuItem itemDefault = new DefaultMenuItem();
                itemDefault.setValue("second menu item");
                menuModel.getElements().add(itemDefault);
            }
        }
        return menuModel;
    }
}
