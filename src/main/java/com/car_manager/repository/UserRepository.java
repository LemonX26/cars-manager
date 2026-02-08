package com.car_manager.repository;

import com.car_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//bedzie umozliwialo komunikacje z baza danych bez koniecznosci pisania zapytan SQL
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByUsername(String username); //Optional - klasa, która może zawierać wartość lub nie
}
