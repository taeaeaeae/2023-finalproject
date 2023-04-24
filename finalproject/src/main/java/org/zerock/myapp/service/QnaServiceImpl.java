package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.QnaDTO;
import org.zerock.myapp.domain.QnaVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.QnaMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@ToString
@Log4j2
@NoArgsConstructor

@Service("qnaService")
public class QnaServiceImpl
	implements QnaService, InitializingBean {	// POJO

	@Setter(onMethod_= { @Autowired })
	private QnaMapper mapper;
	



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
	public List<QnaVO> getList(Criteria cri) throws ServiceException {
		log.trace("getList() invoked.");
	
		try {
			return this.mapper.selectAll(cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getList


	@Override
	public QnaVO get(Integer qid) throws ServiceException {
		log.trace("get({}) invoked.", qid);
		
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
	public boolean register(QnaDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		try {
			return this.mapper.insert(dto) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // register


	@Override
	public boolean modify(QnaDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		
		try {
			return this.mapper.update(dto) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // modify	
	
	@Override
	public Integer getTotalAmount() throws ServiceException {
		log.trace("getTotalAmount() invoked.");
		
		try {
			return this.mapper.selectTotalCount();
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getTotalAmount
	
} // end class
