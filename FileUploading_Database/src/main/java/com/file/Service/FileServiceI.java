package com.file.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.file.CustomException.EnquiryNotFoundException;
import com.file.model.FileDetails;
public interface FileServiceI {

	public FileDetails saveData(String fileJson, MultipartFile profImg, MultipartFile adhrDoc, MultipartFile panDoc);

	public List<FileDetails> getAllData();

	public FileDetails getSingleData(int fileId) throws EnquiryNotFoundException;

	public FileDetails updateData(String fileJson, MultipartFile profImg, MultipartFile adhrDoc, MultipartFile panDoc, int fileId) throws EnquiryNotFoundException;

}
