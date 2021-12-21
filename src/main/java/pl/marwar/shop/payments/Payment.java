package pl.marwar.shop.payments;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;

@Builder
@Value
public class Payment {
    String id;
    FastMoney money;
    Instant timestamp;
    PaymentStatus status;
}

