package com.file.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.CustomException.EnquiryNotFoundException;
import com.file.Service.FileServiceI;
import com.file.model.FileDetails;

@RestController
public class FileController {

	
	/**
	 * accepts:- (text,profile file,addhar file,pancard file)
	 *     containt        key
	 *     text            info
	 *     profileImage    prof
	 *     adharCard       adhar
	 *     panCard         pan
	 * @return 
	 */
	
	@Autowired
	FileServiceI fss;
	
	@PostMapping("enquiry")
	public ResponseEntity<FileDetails> response(@RequestPart("info") String fileJson,
													@RequestPart("prof") MultipartFile profImg,
													@RequestPart("adhar")MultipartFile adhrDoc,
													@RequestPart("pan") MultipartFile panDoc )
	{
		FileDetails fd=fss.saveData(fileJson,profImg,adhrDoc,panDoc);
		return new ResponseEntity<FileDetails>(fd,HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<FileDetails>>listFile()
	{
					List<FileDetails> list=fss.getAllData();
		return new ResponseEntity<List<FileDetails>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getSingle/{fileId}")
	public ResponseEntity<FileDetails> getData(@PathVariable int fileId) throws EnquiryNotFoundException
	{
		FileDetails fdd=fss.getSingleData(fileId);
	return new ResponseEntity<FileDetails>(fdd,HttpStatus.OK);	
	}
	@PutMapping("/update/{fileId}")
	public ResponseEntity<FileDetails> updateData(@RequestPart("info") String fileJson,
														@RequestPart("prof") MultipartFile profImg,
														@RequestPart("adhar")MultipartFile adhrDoc,
														@RequestPart("pan") MultipartFile panDoc ,@PathVariable int fileId) throws EnquiryNotFoundException
	{
		FileDetails detail=fss.updateData(fileJson,profImg,adhrDoc,panDoc,fileId);
		return new ResponseEntity<FileDetails>(detail,HttpStatus.OK);
	}
	
}
