package net.pupunha.test.job.dao;

import net.pupunha.test.job.domain.Songs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SongsRepositoryTest {

    @Autowired
    private SongsRepository songsRepository;

    @Test
    public void findAll() {
        List<Songs> all = songsRepository.findAll();
        all.forEach(s -> s.getArtistId());
    }
}