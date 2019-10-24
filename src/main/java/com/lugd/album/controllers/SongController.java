package com.lugd.album.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lugd.album.model.AlbumModel;
import com.lugd.album.model.Song;
import com.lugd.album.repository.SongRepository;
import com.lugd.album.request.AlbumRequest;
import com.lugd.album.response.BaseResponse;
import com.lugd.album.services.SongService;
import com.lugd.album.utilities.ResultCode;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/actionSong")
public class SongController {
	
	@Autowired
	SongService songService;
	

	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/create/song", method = RequestMethod.POST)
	@ApiOperation(value = "Thêm bài hát")
	public BaseResponse update(@RequestBody(required = true) Song song) {
		BaseResponse output = new BaseResponse();
		try {
			/*AlbumModel album = new AlbumModel();
		//	album.setId(request.getId());
			album.setTitle(request.getTitle());
			album.setImageUrl(request.getImageUrl());
			album.setSongs(request.getListSong());*/
            songService.create(song);         
		} catch (Exception e) {
			output.setMsg(env.getProperty("log.write.notsucess"));
			output.setError(ResultCode.ERROR);
		}
		return output;
	}
}
