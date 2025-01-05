package org.example.springboot_firstproject.service.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

//Source : https://www.geeksforgeeks.org/spring-boot-crudrepository-with-example/

public interface GameUserRepository extends JpaRepository<GameUser, String> {

    @Query("SELECT g FROM GameUser g WHERE g.username LIKE %:username%")
    Optional<GameUser> searchByUsername(@Param("username") String username);

    @Modifying
//    @Query("DELETE FROM GameUser g WHERE g.userId = :userId")
    void deleteGameUserByUserId(@Param("userId") UUID userId);

    //This shows the JPA that its UPDATE or INSERT not SELECT type of query
    @Modifying
    @Query("UPDATE GameUser g SET g.username = :username, g.password = :password WHERE g.userId = :userId")
    int updateGameUserByUserId(@Param("username") String username,
                               @Param("password") String password,
                               @Param("userId") String userId);

}
