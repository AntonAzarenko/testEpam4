package com.azarenko.Services;

import com.azarenko.model.Publication;

import java.util.List;

public interface PublicationService {

    List<Publication> getCatalog();

    void add(Publication publication);

    void remove(int id);

    void update(Publication publication);

    Publication getPublication(int id);

}
