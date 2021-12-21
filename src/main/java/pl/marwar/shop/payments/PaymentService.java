package pl.marwar.shop.payments;

public interface PaymentService {
    Payment process(PaymentRequest paymentRequest);
}
