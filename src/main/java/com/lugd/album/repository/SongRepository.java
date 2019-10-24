package com.lugd.album.repository;

import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import com.lugd.album.model.AlbumModel;
import com.lugd.album.model.Song;
import com.lugd.album.model.SongModel;

public interface SongRepository extends CouchbasePagingAndSortingRepository<Song, String> {

}
