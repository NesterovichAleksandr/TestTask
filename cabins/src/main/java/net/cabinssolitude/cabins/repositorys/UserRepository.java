package net.cabinssolitude.cabins.repositorys;

import net.cabinssolitude.cabins.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select b from User b where b.name = :name")
    User findByName(@Param("name") String name);
}
