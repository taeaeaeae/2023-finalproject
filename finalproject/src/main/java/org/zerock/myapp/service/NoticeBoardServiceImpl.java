package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FreeBoardDTO;
import org.zerock.myapp.domain.FreeBoardVO;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.domain.NoticeVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.NoticeMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@NoArgsConstructor
@Log4j2

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService, InitializingBean {

	@Setter(onMethod_ = {@Autowired})
	private NoticeMapper noticemapper;
	
	
	@Override
	public void afterPropertiesSet() throws ServiceException {
		log.trace("afterPropertiesSet() invoked.");
		
		try {
			Objects.requireNonNull(this.noticemapper);
			log.info("\t+ this.noticemapper : {}", this.noticemapper);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // afterPropertiesSet
	
	@Override
	public List<NoticeVO> getList(Criteria cri) throws ServiceException {
		log.trace("getList({}) invoked.", cri);
		
		try {
			return this.noticemapper.selectAll(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getList

	@Override
	public NoticeVO get(Integer nid) throws ServiceException {
		log.trace("get({}) invoked.", nid);
		
		try {	
			this.noticemapper.viewCountUp(nid);
			return this.noticemapper.select(nid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // get
	
	@Override
	public boolean remove(Integer nid) throws ServiceException {
		log.trace("remove({}) invoked.", nid);
		
		try {
			return (this.noticemapper.delete(nid) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // remove

	@Override
	public boolean register(NoticeDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		try {
			return (this.noticemapper.insert(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}	//register

	@Override
	public boolean modify(NoticeDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		
		try {
			return (this.noticemapper.update(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}	//modify

	@Override
	public Integer getTotalAmount(Criteria cri) throws ServiceException {
		log.trace("getTotalAmount({}) invoked.", cri);
		
		try {
			return this.noticemapper.selectTotalCount(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getTotalAmount

	@Override
	public Integer getPrevPost(Integer nid) throws ServiceException {
		log.trace("getPrevPost() invoked.");
		
		try {
			return this.noticemapper.prev(nid);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getPrevPost
	
	@Override
	public Integer getNextPost(Integer nid) throws ServiceException {
		log.trace("getNextPost() invoked.");
		
		try {
			return this.noticemapper.next(nid);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getNextPost

	@Override
	public List<NoticeVO> getListPageSearch(Criteria cri) throws ServiceException {
		log.trace("getListPageSearch({}) invoked", cri);
		
		try {
			return this.noticemapper.listPageSearch(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getListPageSearch

	@Override
	public Integer viewCountUp(Integer nid) throws ServiceException {
		log.trace("viewCountUp({}) invoked", nid);
		
		try {
			return this.noticemapper.viewCountUp(nid);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}  // viewCountUp
		
	
	@Override
	public Integer top(Integer nid) throws ServiceException {
		log.trace("top({}) invoked", nid);
		
		try {
			return this.noticemapper.top(nid);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}  // top
	

} // end class