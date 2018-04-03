package com.azarenko.dao;

import com.azarenko.model.Publication;

import java.util.List;

public interface PublicationDao {

    List<Publication> getCatalog();

    void add(Publication publication);

    void remove(int id);

    void update(Publication publication);

    Publication getPublication(int id);

}
