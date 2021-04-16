package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.dto.UserDto;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.ImageRepo;
import com.spring.ecommerce.repository.UserRepo;

import lombok.RequiredArgsConstructor;


/*************************************************************************
 * {@link UserServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-2-1
 *************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;
	private final ImageRepo imageRepo;

	// TODO remove
	@Override
	public List<UserDto> getAllUser() {
		return userRepo.findAllByType("user").stream().map(this::getUserDtoFromEntity).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<?> deleteById(String id) {
		try {
			User ob =userRepo.findById(id).orElse(null);
			if(ob.getImage()!=null) {
				imageRepo.deleteById(ob.getImage().getId());
			}
			userRepo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>("UnSuccecfully.", HttpStatus.BAD_REQUEST);
		}
	}
	
	/*************************************************************************
     * Get User {@link  User} by Email
     * 
     * @return {@link  User}
     *************************************************************************/
	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	public UserDto getUserDtoFromEntity(User ob) {
		if(ob.getImage()!=null) {
			ob.setImageId(ob.getImage().getId());
			ob.setImageName(ob.getImage().getName());
		}
		UserDto obj = new UserDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
