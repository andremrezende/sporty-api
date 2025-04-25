package com.sporty.api.core.ports;

import com.sporty.api.core.BasicPersistencePort;
import com.sporty.api.core.exceptions.EntityNotFoundException;
import com.sporty.api.core.model.CustomerLoyalty;
import java.util.UUID;

public interface CustomerLoyaltyPort extends BasicPersistencePort<CustomerLoyalty> {

  CustomerLoyalty findByCustomerId(UUID customerID) throws EntityNotFoundException;
}
