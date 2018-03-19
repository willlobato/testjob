package net.pupunha.test.job.dao;

import net.pupunha.test.job.domain.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongsRepository extends JpaRepository<Songs, String> {

    @Override
    List<Songs> findAll();

}
