package com.lugd.album.repository;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import com.lugd.album.model.AlbumModel;
import com.lugd.album.model.SongModel;


@N1qlPrimaryIndexed
public interface AlbumRepository extends CouchbasePagingAndSortingRepository<AlbumModel, String>{
	        
	@Query("#{#n1ql.selectEntity} where 1=1 ")
    public List<AlbumModel> findAll();
	
	/*@Query("UPDATE INTO `Album` (KEY, VALUE)\r\n" + 
			"VALUES('songs', {\"'$1'")
	public void addSongs(List<SongModel> songs , String albId);*/
 
}
