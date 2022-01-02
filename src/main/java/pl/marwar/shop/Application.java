package pl.marwar.shop;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.marwar.shop.payments.LocalMoney;
import pl.marwar.shop.payments.LoggingPaymentService;
import pl.marwar.shop.payments.PaymentRequest;

@Log
public class Application {
    private static final String BASE_PACKAGE = "pl.marwar.shop";

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                BASE_PACKAGE)) {
            var paymentService = applicationContext.getBean(LoggingPaymentService.class);
            var paymentRequest = PaymentRequest.builder()
                    .money(LocalMoney.of(1_000))
                    .build();
            var payment = paymentService.process(paymentRequest);
            log.info(payment.toString());
        }

    }
}
