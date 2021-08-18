package com.mainul35.impl;

import com.mainul35.dao.AttachmentDao;
import com.mainul35.entity.Attachment;
import com.mainul35.util.FileUtil;
import com.mainul35.util.ImageUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

@Component
@Transactional
public class AttachmentDaoImpl implements AttachmentDao {
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * @return Attachment object
     * @param attachment Attachment object details to save in DB
     * @param multipartFile File that has been uploaded and to write to disk
     * @param referenceId Reference ID (Reference of Book or User) which is the owner of this attachment
     * */
    public Attachment save(Attachment attachment, MultipartFile multipartFile, Long referenceId) {
        try {
            // Make directories to save file
            FileUtil.makeDirectory("resources" + "\\" + referenceId);
            // Generate uploaded file extension
            String extension = ".jpg";
            StringTokenizer tokenizer = new StringTokenizer(multipartFile.getOriginalFilename(), ".");
            while (tokenizer.hasMoreTokens()) {
                extension = tokenizer.nextToken();
            }
            String url = "resources" + "\\" + referenceId+ "\\";
            attachment.setDisplayName(Long.toString(attachment.getAttachmentId()));
            attachment.setPath(url);
            attachment.setType("."+extension);
            File file = File.createTempFile("\\"+attachment.getDisplayName(), attachment.getType());
            multipartFile.transferTo(file);
            ImageUtil.generateThumbs(attachment, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Session session = sessionFactory.getCurrentSession();
        session.save(attachment);
        return attachment;
    }

    public Attachment readAttachment(Long id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Attachment a WHERE a.id = :id";
        Query query = session.createQuery(hql);
        Attachment attachment = null;
        if(query.list().size() > 0){
            attachment = (Attachment) query.list().get(0);
        }
        return attachment;
    }

}
