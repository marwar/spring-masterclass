package pl.marwar.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Instant;

@Log
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Service("paymentService")
//@RequiredArgsConstructor
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;

    @Autowired
    public FakePaymentService(@IdGenerator("incremental") PaymentIdGenerator paymentIdGenerator,
                              PaymentRepository paymentRepository) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.paymentRepository = paymentRepository;
    }

    @LogPayments
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        return paymentRepository.save(payment);
    }

    @PostConstruct
    public void init() {
        log.info("PaymentService initialized");
    }

    @PreDestroy
    public void destroy() {
        log.info("PaymentService is going down");
    }
}
