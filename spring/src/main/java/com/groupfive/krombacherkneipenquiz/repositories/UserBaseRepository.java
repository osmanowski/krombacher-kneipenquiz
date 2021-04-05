package com.groupfive.krombacherkneipenquiz.repositories;

import com.groupfive.krombacherkneipenquiz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Long> {
    User findUserByBenutzername(String benutzername);
}
