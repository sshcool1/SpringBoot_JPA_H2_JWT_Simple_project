package com.hellcow.testBook.service;

import com.hellcow.testBook.api.ApiMethod;
import com.hellcow.testBook.api.exception.ApiException;
import com.hellcow.testBook.api.model.ApiData;
import com.hellcow.testBook.entity.UserEntity;
import com.hellcow.testBook.entity.dto.UserDTO;
import com.hellcow.testBook.repo.UserRepository;
import com.hellcow.testBook.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 사용자 확인
	 * @param data
	 * @return
	 */
	@ApiMethod(name="view")
	public Object getOne(ApiData data) throws ApiException {
		UserDTO user = (UserDTO) ConvertUtil.convertMapToObject(data.getData(), new UserDTO());
		UserEntity userEntity = userRepository.findById(user.getUserId()).get();
		return ConvertUtil.convertEntityToDTO(userEntity, UserDTO.class);
	}

	/**
	 * 저장
	 * @param data
	 * @return
	 */
	@ApiMethod(name="save")
	public Object save(ApiData data) throws ApiException {
		Map<String, Object> param = data.getData();
		UserEntity user = UserEntity.builder()
				.userId(param.get("userId").toString())
				.password(param.get("password").toString())
				.name(param.get("name").toString()).build();

		UserEntity save = userRepository.save(user);

		return save;
	}

}
