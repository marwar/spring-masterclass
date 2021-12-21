package pl.marwar.shop.payments;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Builder
@Value
public class PaymentRequest {
    Long id;
    FastMoney money;
}
