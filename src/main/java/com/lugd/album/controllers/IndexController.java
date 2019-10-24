package com.lugd.album.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lugd.album.response.BaseResponse;
import com.lugd.album.utilities.ResultCode;

@RestController
public class IndexController implements ErrorController {

	private static final String PATH = "/error";

	@Autowired
	private Environment env;

	@RequestMapping(value = PATH)
	@ResponseBody
	public Object error(HttpServletResponse response, HttpServletRequest request) {
		BaseResponse output = new BaseResponse();
		output.setError(ResultCode.ERROR);
		output.setMsg(env.getProperty("error.unexpected"));
		return output;

	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

}