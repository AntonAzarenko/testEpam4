package com.azarenko;

import com.azarenko.dao.CatalogDao;
import com.azarenko.dao.CatalogDaoImpl;
import com.azarenko.model.Catalog;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CatalogDao catalogDao = new CatalogDaoImpl();
        List<Catalog> catalogList= catalogDao.getCatalog();
        for(Catalog pair: catalogList){
            System.out.println(pair.toString());
        }

    }
}
