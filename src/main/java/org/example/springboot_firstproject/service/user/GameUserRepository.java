package org.example.springboot_firstproject.service.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

//Source : https://www.geeksforgeeks.org/spring-boot-crudrepository-with-example/
//Source 2 : https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html

public interface GameUserRepository extends JpaRepository<GameUser, String> {

    Optional<GameUser> findByUsername(String username);

//    @Modifying
//    @Query("UPDATE GameUser g SET g.username = :username, g.password = :password WHERE g.id = :id")
//    void updateGameUserById(@Param("id") int id,
//                                @Param("gameUser") String username,
//                                @Param("password") String password);
}
