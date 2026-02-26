package com.example.shop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Tests {

    /* ========================
       DiscountService Tests
       ======================== */

    @Test
    void discount_nullOrBlank_returnsSubtotal() {
        DiscountService service = new DiscountService();
        assertEquals(100.0, service.applyDiscount(100, null));
        assertEquals(100.0, service.applyDiscount(100, ""));
    }

    @Test
    void discount_invalid_throwsException() {
        DiscountService service = new DiscountService();
        assertThrows(IllegalArgumentException.class,
                () -> service.applyDiscount(100, "INVALID"));
    }

    @Test
    void discount_unknown_returnsSubtotal() {
        DiscountService service = new DiscountService();
        assertEquals(100.0, service.applyDiscount(100, "OTHER"));
    }

    /* ========================
       OrderItem Tests
       ======================== */

    @Test
    void orderItem_totalPrice_calculatedCorrectly() {
        OrderItem item = new OrderItem("Book", 2, 50);
        assertEquals(100.0, item.getTotalPrice());
    }

    @Test
    void orderItem_invalidQuantity_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrderItem("Book", 0, 10));
    }

    /* ========================
       PricingService Tests
       ======================== */

    @Test
    void pricing_subtotalAndTax_calculatedCorrectly() {
        Order order = new Order();
        order.addItem(new OrderItem("A", 2, 10));
        order.addItem(new OrderItem("B", 1, 20));

        PricingService service = new PricingService();

        double subtotal = service.calculateSubtotal(order);
        assertEquals(40.0, subtotal);

        double tax = service.calculateTax(subtotal);
        assertEquals(8.0, tax);
    }

    @Test
    void pricing_negativeSubtotal_throws() {
        PricingService service = new PricingService();
        assertThrows(IllegalArgumentException.class,
                () -> service.calculateTax(-10));
    }

    /* ========================
       PaymentValidator Tests
       ======================== */

    @Test
    void payment_validMethods_returnTrue() {
        PaymentValidator validator = new PaymentValidator();
        assertTrue(validator.isPaymentMethodValid("card"));
        assertTrue(validator.isPaymentMethodValid("paypal"));
    }

    @Test
    void payment_invalidMethods_handledCorrectly() {
        PaymentValidator validator = new PaymentValidator();

        assertFalse(validator.isPaymentMethodValid(null));
        assertFalse(validator.isPaymentMethodValid("crypto"));

        assertThrows(UnsupportedOperationException.class,
                () -> validator.isPaymentMethodValid("bank"));
    }

    /* ========================
       OrderService Tests
       ======================== */

    @Test
    void orderService_invalidPayment_cancelsOrder() {
        Order order = new Order();
        order.addItem(new OrderItem("Item", 1, 100));

        OrderService service = new OrderService();
        double result = service.processOrder(order, null, "crypto");

        assertEquals(0.0, result);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }
}