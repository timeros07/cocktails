package pl.cocktails.controllers;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import pl.cocktails.common.JSONResponse;
import pl.cocktails.common.SystemException;

@ControllerAdvice
public class ExceptionController
{
	@ExceptionHandler
	public @ResponseBody JSONResponse handleSystemException(SystemException e) {
		e.printStackTrace();
		return new JSONResponse(Boolean.FALSE, e);
	}
	
	@ExceptionHandler
	public @ResponseBody JSONResponse handleException(Exception e) {
		e.printStackTrace();
		/**
		 * Transforming exception to error message which can be displayed on page
		 */
		//uploading blob
		if(e instanceof MaxUploadSizeExceededException){
			return new JSONResponse(Boolean.FALSE, "errors.image.upload.maxSize",((MaxUploadSizeExceededException)e).getMaxUploadSize()/1024);
		}
		return new JSONResponse(Boolean.FALSE, e);
	}
}
