package org.example.springboot_firstproject.data.access.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Source : https://www.geeksforgeeks.org/spring-boot-crudrepository-with-example/
//Source 2 : https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html

public interface GameUserRepository extends JpaRepository<GameUser, String> {

    Optional<GameUser> findByUsername(String username);

}
