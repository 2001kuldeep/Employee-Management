package com.magic.dtos;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.magic.models.TimeInfo;

@Component
public class TimeInfoMapper {
	
	private ModelMapper modelMapper;

	@Autowired
	public TimeInfoMapper(ModelMapper modelMopper) {
		this.modelMapper = modelMopper;
		
		modelMapper.createTypeMap(TimeInfoDto.class, TimeInfo.class).addMapping(TimeInfoDto::getEmpid,
				(dest, value) -> dest.getEmployee().setEmpid((String) value));
		modelMapper.createTypeMap(TimeInfo.class, TimeInfoDto.class).addMapping(src -> src.getEmployee().getEmpid(),
				TimeInfoDto::setEmpid);
	}

	public TimeInfoDto toDto(TimeInfo timeInfo) {
		return modelMapper.map(timeInfo, TimeInfoDto.class);
	}

	public TimeInfo toEntity(TimeInfoDto TimeInfoDto) {
		return modelMapper.map(TimeInfoDto, TimeInfo.class);
	}
}
