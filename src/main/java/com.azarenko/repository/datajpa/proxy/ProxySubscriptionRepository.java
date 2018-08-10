package com.azarenko.repository.datajpa.proxy;

import com.azarenko.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProxySubscriptionRepository extends JpaRepository<Subscription, Integer> {

}
