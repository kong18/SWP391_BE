package com.FPTU.repository;

import com.FPTU.model.Customer;
import com.FPTU.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "Select c.customer_id,c.paymentplf,c.user_id from customer c join user u on u.user_id = c.user_id where u.username = :name", nativeQuery = true)
    Customer getCustomerByUserName(@Param("name") String name);
}
