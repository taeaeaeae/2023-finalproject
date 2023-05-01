package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FreeBoardDTO;
import org.zerock.myapp.domain.FreeBoardVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@NoArgsConstructor
@Log4j2

@Service
public class FreeBoardServiceImpl implements FreeBoardService, InitializingBean {

	@Setter(onMethod_ = {@Autowired})
	private BoardMapper mapper;
	
	
	@Override
	public void afterPropertiesSet() throws ServiceException {
		log.trace("afterPropertiesSet() invoked.");
		
		try {
			Objects.requireNonNull(this.mapper);
			log.info("\t+ this.mapper : {}", this.mapper);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // afterPropertiesSet
	
	@Override
	public List<FreeBoardVO> getList(Criteria cri) throws ServiceException {
		log.trace("getList({}) invoked.", cri);
		
		try {
			return this.mapper.selectAll(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getList

	@Override
	public FreeBoardVO get(Integer fid) throws ServiceException {
		log.trace("get({}) invoked.", fid);
		
		try {	
			this.mapper.viewCountUp(fid);
			return this.mapper.select(fid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // get
	
	@Override
	public boolean remove(Integer fid) throws ServiceException {
		log.trace("remove({}) invoked.", fid);
		
		try {
			return (this.mapper.delete(fid) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // remove

	@Override
	public boolean register(FreeBoardDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		try {
			return (this.mapper.insert(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public boolean modify(FreeBoardDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		
		try {
			return (this.mapper.update(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer getTotalAmount(Criteria cri) throws ServiceException {
		log.trace("getTotalAmount({}) invoked.", cri);
		
		try {
			return this.mapper.selectTotalCount(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getTotalAmount

	@Override
	public Integer getPrevPost(Integer fid) throws ServiceException {
		log.trace("getPrevPost() invoked.");
		
		try {
			return this.mapper.prev(fid);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getPrevPost
	
	@Override
	public Integer getNextPost(Integer fid) throws ServiceException {
		log.trace("getNextPost() invoked.");
		
		try {
			return this.mapper.next(fid);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getNextPost

	@Override
	public List<FreeBoardVO> getListPageSearch(Criteria cri) throws ServiceException {
		log.trace("getListPageSearch({}) invoked", cri);
		
		try {
			return this.mapper.listPageSearch(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getListPageSearch

	@Override
	public Integer viewCountUp(Integer fid) throws ServiceException {
		log.trace("viewCountUp({}) invoked", fid);
		
		try {
			return this.mapper.viewCountUp(fid);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}  // viewCountUp
		
//	@Override
//	public FreeBoardVO getNextPost(Integer fid) throws ServiceException {
//		log.trace("getNextPost() invoked.");
//		
//		try {
//			Integer nextFid = this.mapper.next(fid);
//			return this.mapper.select(nextFid);
//		} catch (Exception e) {
//			throw new ServiceException(e);
//		} // try-catch
//	} // getNextPost
	

} // end class