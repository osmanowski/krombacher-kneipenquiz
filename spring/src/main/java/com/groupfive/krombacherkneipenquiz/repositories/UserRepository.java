package com.groupfive.krombacherkneipenquiz.repositories;

import com.groupfive.krombacherkneipenquiz.models.User;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends UserBaseRepository<User> {

}
