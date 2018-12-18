package com.conversion.dao;

import com.conversion.payload.UploadFileResponse;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.query.Param;
 import org.springframework.stereotype.Repository;

 import java.util.List;

@Repository
public interface UploadFileResponseDao extends JpaRepository<UploadFileResponse, Integer> {

 @Query("SELECT c FROM UploadFileResponse c WHERE  c.iduser=:iduser")
 public List<UploadFileResponse> finByIdUser(@Param("iduser") int iduser);

 @Query("SELECT c FROM UploadFileResponse c WHERE  c.Terminer<>0 and ((:curentdate-c.Terminer)/1000)>=300")
 public List<UploadFileResponse> filetodelete(@Param("curentdate") long curentdate);

}
