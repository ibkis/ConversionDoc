package com.conversion.dao;

import com.conversion.modeles.ConversionFile;
import com.conversion.payload.UploadFileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversionFileDao extends JpaRepository<ConversionFile, Integer> {

}
