package org.zerock.myapp.service;

import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.UsersMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2
@NoArgsConstructor

@Service
public class UsersServiceImpl implements UsersService, InitializingBean {
	
	@Setter(onMethod_= {@Autowired})
	private UsersMapper mapper;
	
	@Override
	public void afterPropertiesSet() throws ServiceException{	// 1회성 전처리
		log.trace("afterPropertiesSet() invoked.");
		
		try {
			Objects.requireNonNull(this.mapper);
			log.info("\t+ this.mapper: {}", this.mapper);
		}catch (Exception e) {
			throw new ServiceException(e);
		}	//try-catch
	}// afterPropertiesSet
	
	@Override
	public UsersVO select(String uids) throws ServiceException {
		try {
			return this.mapper.select(uids);
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}	//select

	@Override
	public boolean join(UsersDTO dto) throws ServiceException {
		
		try {
			return this.mapper.join(dto) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}	//join

	@Override
	public boolean remove(String uids) throws ServiceException {
		try {
			return this.mapper.remove(uids) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}	//remove

	@Override
	public boolean update(UsersDTO dto) throws ServiceException {
		try {
			return this.mapper.update(dto) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}	//update

	@Override
	public int idCheck(String uids) {
		int cnt = mapper.idCheck(uids);
	
		return cnt;
	}	//idCheck	

	
}	// end class
