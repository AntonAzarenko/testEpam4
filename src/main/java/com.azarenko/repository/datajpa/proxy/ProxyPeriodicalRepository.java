package com.azarenko.repository.datajpa.proxy;

import com.azarenko.model.Periodical;
import com.sun.istack.internal.Nullable;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static javafx.scene.input.KeyCode.T;


public interface ProxyPeriodicalRepository extends JpaRepository<Periodical, Integer> {
    @Override
    List<Periodical> findAll();

    Periodical getById(Integer id);

    @Override
    @SuppressWarnings("Uncheked")
    Periodical save(Periodical periodical);

    @Override
    void deleteById(Integer id);

    Periodical getByTitle(String title);

    Periodical getByIndex(int index);

    Periodical getByPrice(BigDecimal price);


}
