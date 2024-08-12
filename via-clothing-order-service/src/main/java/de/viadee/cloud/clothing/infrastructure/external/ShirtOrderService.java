package de.viadee.cloud.clothing.infrastructure.external;

import de.viadee.cloud.clothing.infrastructure.messasing.InvalidShirtOrderMessage;
import de.viadee.cloud.clothing.infrastructure.persistence.ShirtOrder;

public interface ShirtOrderService {

    ShirtOrderRequest readShirtOrderRequestFrom(String body) throws InvalidShirtOrderException;

    void sendInvalidShirtOrderMessage(InvalidShirtOrderMessage invalidShirtOrderMessage);

    void persistValidShirtOrder(ShirtOrder shirtOrder);
}
