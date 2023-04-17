package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.FreeBoardCommentDTO;
import org.zerock.myapp.domain.FreeBoardCommentVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.CommentMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@NoArgsConstructor
@Log4j2

@Service
public class FreeBoardCommentServiceImpl implements FreeBoardCommentService, InitializingBean {

	@Setter(onMethod_ = {@Autowired})
	private CommentMapper mapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.trace("afterPropertiesSet() invoked.");
		
		try {
			Objects.requireNonNull(this.mapper);
			log.info("\t+ this.mapper : {}", this.mapper);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // afterPropertiesSet
	
	@Override
	public List<FreeBoardCommentVO> getList(Integer fid) throws ServiceException {
		
		try {
			return this.mapper.selectAll(fid);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getList

	@Override
	public boolean register(FreeBoardCommentDTO dto) throws ServiceException {
		log.trace("register({}) invoked", dto);
		
		try {
			return this.mapper.insert(dto) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // register

	@Override
	public boolean remove(Integer fbcid) throws ServiceException {
		log.trace("remove({}) invoked", fbcid);
		
		try {
			return this.mapper.delete(fbcid) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // remove

	@Override
	public boolean modify(FreeBoardCommentDTO dto) throws ServiceException {
		
		try {
			return this.mapper.update(dto) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // modify

	@Override
	public Integer getTotal() throws ServiceException {
		
		try {
			return this.mapper.selectTotal();
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getTotal

	@Override
	public FreeBoardCommentVO get(Integer fbcid) throws ServiceException {
		
		try {
			return this.mapper.select(fbcid);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // get


} // end class