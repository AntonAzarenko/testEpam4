package com.azarenko.to;

import com.azarenko.model.ShoppingCart;
import com.azarenko.util.ShoppingCartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class ShoppingCartTo {
    @Autowired
    private ShoppingCartUtil shoppingCartUtil;

    @Nullable
    private int periodicalId;

    public ShoppingCart create(ShoppingCartTo shoppingCartTo){
        ShoppingCart shoppingCart = shoppingCartUtil.create(shoppingCartTo.getPeriodicalId(),1);
        return shoppingCart;
    }
    @Nullable
    public int getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(@Nullable int periodicalId) {
        this.periodicalId = periodicalId;
    }
}
