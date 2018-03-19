package net.pupunha.test.job.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Songs")
@Data
public class Songs {

    @Id
    @Column(name = "track_id")
    private String trackId;

    @Column(name = "title")
    private String title;

    @Column(name = "song_id")
    private String songId;

    @Column(name = "release")
    private String release;

    @Column(name = "artist_id")
    private String artistId;

    @Column(name = "artist_mbid")
    private String artistMbid;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "duration")
    private Float duration;

    @Column(name = "artist_familiarity")
    private Float artistFamiliarity;

    @Column(name = "artist_hotttnesss")
    private Float artistHotttnesss;

    @Column(name = "year")
    private Integer year;

    @Column(name = "track_7digitalid")
    private Integer track7digitalid;

    @Column(name = "shs_perf")
    private Integer shsPerf;

    @Column(name = "shs_work")
    private Integer shsWork;

}
