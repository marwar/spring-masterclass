package pl.marwar.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FakePaymentServiceTest {
    private static final String PAYMENT_ID = "1";
    private static final FastMoney MONEY = LocalMoney.of(1_000);
    private static final PaymentRequest PAYMENT_REQUEST = PaymentRequest.builder().money(MONEY).build();

    @Mock
    private PaymentIdGenerator paymentIdGenerator;
    private Payment payment;

    @BeforeEach
    void setUp() {
        when(paymentIdGenerator.getNext()).thenReturn(PAYMENT_ID);
        FakePaymentService fakePaymentService = new FakePaymentService(paymentIdGenerator);
        payment = fakePaymentService.process(PAYMENT_REQUEST);
    }

    @DisplayName("Should assign generated id to created payment")
    @Test
    void testAssignGeneratedIdToCreatedPayment() {
        assertEquals(PAYMENT_ID, payment.getId());
    }

    @DisplayName("Should assign money from payment request to create payment")
    @Test
    void testAssignMoneyFromPaymentRequestToCreatedPayment() {
        assertEquals(MONEY, payment.getMoney());
    }

    @DisplayName("Should assign timestamp to created payment")
    @Test
    void testAssignTimestampToCreatedPayment() {
        assertNotNull(payment.getTimestamp());
    }

    @DisplayName("Should assign STARTED status to created payment")
    @Test
    void testAssignStartedStatusToCratedPayment() {
        assertEquals(PaymentStatus.STARTED, payment.getStatus());
    }
}
