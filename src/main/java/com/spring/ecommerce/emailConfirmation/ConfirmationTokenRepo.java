package com.spring.ecommerce.emailConfirmation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.ecommerce.model.User;

@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepo extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);

	ConfirmationToken findByUser(User user);
}
