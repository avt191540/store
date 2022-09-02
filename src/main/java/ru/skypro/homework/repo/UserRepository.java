package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u order by u.firstName, u.lastName")
    Collection<User> getAll();

    Optional<User> getUserByUsername (String userName);

    boolean existsUserByUsername (String username);

    @Modifying
    @Query("update User u set u.password = :newPassword where u.username = :username")
    void updatePassword(@Param(value = "username") String username,
                        @Param(value = "newPassword") String newPassword);

    @Modifying
    @Query("update User u set u.email = :email, u.password = :password, u.firstName = :firstName, u.lastName = :lastName, u.phone = :phone where u.id = :idUser")
    void updateUserRegistration(@Param(value = "email") String email, @Param(value = "password") String password,
                                @Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName,
                                @Param(value = "phone") String phone, @Param(value = "idUser") Long idUser);
}
