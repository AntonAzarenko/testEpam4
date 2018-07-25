package com.azarenko.repository.datajpa.proxy;

import com.azarenko.model.Periodical;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;



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
