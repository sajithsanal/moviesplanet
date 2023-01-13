package com.moviesplanet.customer.common.repo;

import com.moviesplanet.customer.common.model.CustomerBookingDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerBookingRepo extends MongoRepository<CustomerBookingDetails, String> {

    Optional<CustomerBookingDetails> findByCustomerEmail(String email);
    Optional<CustomerBookingDetails> findByCustomerMobile(String mobile);

}
