package com.groupfive.krombacherkneipenquiz.repositories;

import com.groupfive.krombacherkneipenquiz.models.QuizRunde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRundeRepository extends JpaRepository<QuizRunde, Long> {
QuizRunde findByIp(String ip);
List<QuizRunde> findByIpAndEnabled(String ip, boolean enabled);
}
