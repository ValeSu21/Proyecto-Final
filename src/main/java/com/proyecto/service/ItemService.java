package com.proyecto.service;

import com.proyecto.domain.Item;
import java.util.ArrayList;
import java.util.List;

public interface ItemService {

    List<Item> listaItems = new ArrayList<>();

    List<Item> gets();

    Item get(Item item);

    void delete(Item item);

    void save(Item item);

    void actualiza(Item item);

    void facturar();
}
