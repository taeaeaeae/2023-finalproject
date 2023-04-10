package org.zerock.myapp.service;

import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.AnswerDTO;
import org.zerock.myapp.domain.AnswerVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.AnswerMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@ToString
@Log4j2
@NoArgsConstructor

@Service("answerService")
public class AnswerServiceImpl
	implements AnswerService, InitializingBean {	// POJO

	@Setter(onMethod_= { @Autowired })
	private AnswerMapper mapper;
	



	@Override
	public void afterPropertiesSet() throws ServiceException {	
		log.trace("afterPropertiesSet() invoked.");
		
		try {
			Objects.requireNonNull(this.mapper);
			log.info("\t+ this.mapper: {}", this.mapper);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // afterPropertiesSet
	


	@Override
	public AnswerVO get(Integer qid) throws ServiceException {
		log.trace("getList({}) invoked.", qid);
		
		try {
			return this.mapper.select(qid);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // get


	@Override
	public boolean remove(Integer qid) throws ServiceException {
		log.trace("remove({}) invoked.", qid);
		
		try {
			return this.mapper.delete(qid) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // remove


	@Override
	public boolean register(AnswerDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		try {
			return this.mapper.insert(dto) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // register


	@Override
	public boolean modify(AnswerDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		
		try {
			return this.mapper.update(dto) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // modify	
	
} // end class
