package com.smartfitness.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {

	int insertToken(String refreshToken, String DBmem_id);

}
