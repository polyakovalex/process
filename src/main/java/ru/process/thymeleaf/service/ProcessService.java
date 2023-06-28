package ru.process.thymeleaf.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.process.thymeleaf.dto.ProcessDto;
import ru.process.thymeleaf.entity.Process;
import ru.process.thymeleaf.repository.ProcessRepository;

@Service
public class ProcessService {

  @Autowired
  private ProcessRepository processRepository;

  @Autowired
  private ModelMapper modelMapper;


  public void saveProcess(ProcessDto processDto) {
    Process process = convertProcessDtoToProcess(processDto);
    processRepository.save(process);
  }


  private Process convertProcessDtoToProcess(ProcessDto processDto) {
    Process process = modelMapper.map(processDto, Process.class);
   // postDto.setUserDto(convertToUserDto(post.getUser()));
    return process;
  }

}
