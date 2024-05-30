package com.file.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.CustomException.EnquiryNotFoundException;
import com.file.Repository.FileRepository;
import com.file.model.FileDetails;
@Service
public class FileServiceImpl implements FileServiceI{

	@Autowired
	FileRepository frr;
	@Override
	public FileDetails saveData(String fileJson, MultipartFile profImg, MultipartFile adhrDoc, MultipartFile panDoc) {
		ObjectMapper mapper=new ObjectMapper();
		FileDetails fd= null;
		try {
			fd=mapper.readValue(fileJson, FileDetails.class);
			}
			catch (JsonMappingException e) {
				e.printStackTrace();
			} 
			catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			if(fd!=null) {
				try {
					fd.setProfileImage(profImg.getBytes());
	        		  fd.setAadharCard(adhrDoc.getBytes());
	        		  fd.setPanCard(panDoc.getBytes());
				}catch (IOException ie) {
					ie.printStackTrace();
				}
				
			}
			return frr.save(fd);
		}
	@Override
	public List<FileDetails> getAllData() {
		
		return (List<FileDetails>) frr.findAll();
	}
	@Override
	public FileDetails getSingleData(int fileId) throws EnquiryNotFoundException {
					Optional<FileDetails> op=frr.findById(fileId);
					
					if(op.isPresent())
						{
						return op.get();
						}
					else {
						throw new EnquiryNotFoundException("EnquiryNotFoundException");
					}
					
					
	}
	
	@Override
	public FileDetails updateData(String fileJson, MultipartFile profImg, MultipartFile adhrDoc, MultipartFile panDoc,
			int fileId)  {
		
		ObjectMapper mapper=new ObjectMapper();
		FileDetails fd= null;
		try {
			fd=mapper.readValue(fileJson, FileDetails.class);
			}
			catch (JsonMappingException e) {
				e.printStackTrace();
			} 
			catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			if(fd!=null) {
				try {
					fd.setProfileImage(profImg.getBytes());
	        		  fd.setAadharCard(adhrDoc.getBytes());
	        		  fd.setPanCard(panDoc.getBytes());
				}catch (IOException ie) {
					ie.printStackTrace();
				}
				
			}
			return frr.save(fd);
		}

}
	



