package org.zerock.myapp.service;

import org.zerock.myapp.domain.EmailDTO;
import org.zerock.myapp.exception.ServiceException;

public interface EmailService {

    public abstract void sendMail(EmailDTO dto) throws ServiceException;
}
