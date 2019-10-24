package com.lugd.album.controllers;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lugd.album.model.AlbumModel;
import com.lugd.album.model.Song;
import com.lugd.album.model.SongModel;
import com.lugd.album.request.AlbumRequest;
import com.lugd.album.request.SongRequest;
import com.lugd.album.response.BaseResponse;
import com.lugd.album.services.AlbumService;
import com.lugd.album.services.SongService;
import com.lugd.album.utilities.AppUtil;
import com.lugd.album.utilities.ResultCode;
import com.lugd.album.validators.LogValidator;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/albumAPI")
public class AlbumController {

	@Autowired
	AlbumService albumService;

	@Autowired
	private Environment env;
	
	@GetMapping(path = "/getAll")
	@ApiOperation(value = "Lấy tất cả Album")
	public List<AlbumModel> getAll() {
		return albumService.getAll();
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ApiOperation(value = "Thêm album mới")
	public BaseResponse create(@RequestBody(required = true) AlbumRequest request) {
		BaseResponse output = new BaseResponse();
		try {
			
			AlbumModel album = new AlbumModel();
		//	album.setId(request.getId());
			album.setTitle(request.getTitle());
			album.setImageUrl(request.getImageUrl());
			Date now = new Date();
			album.setRelDate(now.toString());
			album.setSongs(request.getListSong());
			albumService.create(album);
	
		} catch (Exception e) {
			output.setMsg(env.getProperty("log.write.notsucess"));
			output.setError(ResultCode.ERROR);
		}
		return output;

	}
	
	@RequestMapping(value ="/update/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Cập nhật album")
	public BaseResponse update(@PathVariable("id") String id ,@RequestBody(required = true) AlbumRequest request) {
		BaseResponse output = new BaseResponse();
		try {
			AlbumModel album = new AlbumModel();
			album.setId(id);
			album.setTitle(request.getTitle());
			album.setImageUrl(request.getImageUrl());
			album.setSongs(request.getListSong());
            albumService.update(album);         
		} catch (Exception e) {
			output.setMsg(env.getProperty("log.write.notsucess"));
			output.setError(ResultCode.ERROR);
		}
		return output;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Xóa album")
	public BaseResponse deleteAlbum(@PathVariable("id") String idAlbum ) {
		BaseResponse output = new BaseResponse();
		try {
			albumService.delete(idAlbum);
		} catch (Exception e) {
			output.setMsg(env.getProperty("log.write.notsucess"));
			output.setError(ResultCode.ERROR);
		}
		return output;
	}
	
   // Thêm bài hát cho album 
	@RequestMapping(value = "/addSong/album/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "Thêm bài hát cho album")
	public BaseResponse addSongForAlbum(@PathVariable("id") String idAlbum ,@RequestBody List<SongRequest> songs) {
		BaseResponse output = new BaseResponse();
		try {		
		
			Optional<AlbumModel> album= albumService.findById(idAlbum);
			AlbumModel model = album.get();
			List<SongModel> listSong = model.getSongs();

			for (SongRequest songRequest : songs) {
				SongModel song = new SongModel();
				int id = new Random().nextInt(999);
				song.setsId(Integer.toString(id));
				song.setsTitle(songRequest.getsTitle());
				song.setPlaySong(songRequest.getPlaySong());
				song.setArtist(songRequest.getArtist());
				listSong.add(song);
			}
			
			model.setSongs(listSong);
			albumService.create(model);
		} catch (Exception e) {
			output.setMsg(env.getProperty("log.write.notsucess"));
			output.setError(ResultCode.ERROR);
		}
		return output;
	}
	
	//Xóa bài hát cho album
	@RequestMapping(value = "/deleteSong/album/{idAlb}", method = RequestMethod.POST)
	@ApiOperation(value = "Xóa bài hát cho album")
	public BaseResponse deleteSongForAlbum(@PathVariable("idAlb") String idAlbum ,@RequestBody SongModel songs) {
		BaseResponse output = new BaseResponse();
		try {		
		    
			Optional<AlbumModel> alb = albumService.findById(idAlbum);	
			AlbumModel albModel = alb.get();	
			List<SongModel> listSong = albModel.getSongs();
			for (SongModel songModel : listSong) {
				if(songs.getsId().equalsIgnoreCase(songModel.getsId())) {
					listSong.remove(songModel);
					break;
				}
			}
			albModel.setSongs(listSong);
			albumService.create(albModel);
		} catch (Exception e) {
			output.setMsg(env.getProperty("log.write.notsucess"));
			output.setError(ResultCode.ERROR);
		}
		return output;
	}
	
	
	//Sửa bài hát cho album
	@RequestMapping(value = "/updateSong/album/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "Sửa bài hát cho album")
	public BaseResponse updateSongForAlbum(@PathVariable("id") String idAlbum ,@RequestBody List<SongModel> songs) {
		BaseResponse output = new BaseResponse();
		try {		
			Optional<AlbumModel> alb = albumService.findById(idAlbum);
			AlbumModel albModel = alb.get();
			List<SongModel> listSong = albModel.getSongs();		
			for (int i = 0; i < songs.size(); i++) {
				for (int j = 0; j < listSong.size(); j++) {
					if(songs.get(i).getsId().equalsIgnoreCase(listSong.get(j).getsId()))
					{
						listSong.get(j).setsTitle(songs.get(i).getsTitle());
						listSong.get(j).setPlaySong(songs.get(i).getPlaySong());
						listSong.get(j).setArtist(songs.get(i).getArtist());
					}
				}
			}
			albModel.setSongs(listSong);
			albumService.create(albModel);	
		} catch (Exception e) {
			output.setMsg(env.getProperty("log.write.notsucess"));
			output.setError(ResultCode.ERROR);
		}
		return output;
	}
	
	
	

}