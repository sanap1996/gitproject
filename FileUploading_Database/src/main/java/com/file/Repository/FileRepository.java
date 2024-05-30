package com.file.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.file.model.FileDetails;
@Repository
public interface FileRepository extends CrudRepository<FileDetails, Integer>{

}
