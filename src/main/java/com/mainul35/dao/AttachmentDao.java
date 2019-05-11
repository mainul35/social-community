package com.mainul35.dao;

import com.mainul35.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentDao {

    public Attachment save(Attachment attachment, MultipartFile multipartFile, Long referenceId);

    public Attachment readAttachment(Long id);
}
