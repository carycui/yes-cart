package org.yes.cart.shoppingcart.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yes.cart.constants.Constants;
import org.yes.cart.constants.ServiceSpringKeys;
import org.yes.cart.domain.dto.ShoppingCart;
import org.yes.cart.service.domain.PriceService;
import org.yes.cart.service.domain.ProductService;
import org.yes.cart.service.domain.ShopService;
import org.yes.cart.service.domain.impl.BaseCoreDBTestCase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public class AddSkuToCartEventCommandImplTest extends BaseCoreDBTestCase {
    
    ShopService shopService = null;
    PriceService priceService = null;
    ProductService productService = null;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        productService = (ProductService) ctx.getBean(ServiceSpringKeys.PRODUCT_SERVICE);
        priceService = (PriceService) ctx.getBean(ServiceSpringKeys.PRICE_SERVICE);
        shopService = (ShopService) ctx.getBean(ServiceSpringKeys.SHOP_SERVICE);
    }

    @After
    public void tearDown() {
        shopService = null;
        priceService = null;
        productService = null;
        super.tearDown();
    }    
    
    @Test
    public void testExecute() {
        ShoppingCart shoppingCart = new ShoppingCartImpl();
        shoppingCart.setCurrencyCode("EUR");
        shoppingCart.setShopId(10);

        assertEquals(BigDecimal.ZERO.setScale(Constants.DEFAULT_SCALE), shoppingCart.getCartSubTotal(shoppingCart.getCartItemList()));

        Map<String, String> params = new HashMap<String, String>();
        params.put(AddSkuToCartEventCommandImpl.CMD_KEY, "CC_TEST1");

        
        AddSkuToCartEventCommandImpl command;

        command = new AddSkuToCartEventCommandImpl(ctx, params);
        command.execute(shoppingCart);
        assertTrue("Expected 19.99", (new BigDecimal("19.99")).equals(shoppingCart.getCartSubTotal(shoppingCart.getCartItemList())));

        command = new AddSkuToCartEventCommandImpl(ctx, params);
        command.execute(shoppingCart);
        assertTrue("Expected 39.98", (new BigDecimal("39.98")).equals(shoppingCart.getCartSubTotal(shoppingCart.getCartItemList())));

        command = new AddSkuToCartEventCommandImpl(ctx, params);
        command.execute(shoppingCart);
        assertTrue("Expected 57.00", (new BigDecimal("57.00")).equals(shoppingCart.getCartSubTotal(shoppingCart.getCartItemList())));


    }
    
}