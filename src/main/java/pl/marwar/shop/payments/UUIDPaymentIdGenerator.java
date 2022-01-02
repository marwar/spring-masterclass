package pl.marwar.shop.payments;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDPaymentIdGenerator implements PaymentIdGenerator {
    @Override public String getNext() {
        return UUID.randomUUID().toString();
    }
}
