package pl.marwar.shop.payments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UUIDPaymentIdGeneratorTest {

    private static final String ID_FORMAT = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";

    private final UUIDPaymentIdGenerator paymentIdGenerator = new UUIDPaymentIdGenerator();

    @DisplayName("Should generate valid id")
    @Test
    void testGenerateValidId() {
        String id = paymentIdGenerator.getNext();
        Assertions.assertTrue(id.matches(ID_FORMAT));
    }
}
