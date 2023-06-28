package ru.process.thymeleaf.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.process.thymeleaf.dto.ProcessDto;
import ru.process.thymeleaf.entity.Process;
import ru.process.thymeleaf.repository.AgentRepository;
import ru.process.thymeleaf.repository.PlaceRepository;
import ru.process.thymeleaf.repository.ProcessRepository;
import ru.process.thymeleaf.service.ProcessService;

@Controller
public class ProcessController {

  @Autowired
  private ProcessRepository processRepository;

  @Autowired
  private AgentRepository agentRepository;

  @Autowired
  private PlaceRepository placeRepository;

  @Autowired
  private ProcessService processService;

  @GetMapping("/processes")
  public String getAll(Model model, @Param("keyword") String keyword) {
    try {
      List<Process> processes = new ArrayList<>();

      if (keyword == null) {
        processRepository.findAll().forEach(processes::add);
      } else {
        processRepository.findByTitleContainingIgnoreCase(keyword).forEach(processes::add);
        model.addAttribute("keyword", keyword);
      }

      model.addAttribute("processes", processes);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }

    return "processes";
  }

  @GetMapping("/processes/new")
  public String addProcess(Model model) {
    Process process = new Process();

    model.addAttribute("process", process);
    model.addAttribute("dateAdd", LocalDate.now());
    model.addAttribute("agents", agentRepository.findAll());
    model.addAttribute("places", placeRepository.findAll());
    model.addAttribute("pageTitle", "Создание нового заказа");

    return "process_form";
  }

  @PostMapping("/processes/save")
  public String saveProcess(ProcessDto processDto, RedirectAttributes redirectAttributes) {
    try {
      processService.saveProcess(processDto);
      redirectAttributes.addFlashAttribute("message", "Заказ успешно создан!");
    } catch (Exception e) {
      redirectAttributes.addAttribute("message", e.getMessage());
    }

    return "redirect:/processes";
  }

  @GetMapping("/processes/{id}")
  public String editProcess(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      Process process = processRepository.findById(id).get();

      model.addAttribute("process", process);
      model.addAttribute("pageTitle", "Редактирование заказа (ID: " + id + ")");

      return "process_form";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());

      return "redirect:/processes";
    }
  }

  @GetMapping("/processes/delete/{id}")
  public String deleteProcess(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      processRepository.deleteById(id);

      redirectAttributes.addFlashAttribute("message", "Заказ с id=" + id + " успешно удален!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());
    }

    return "redirect:/processes";
  }
}
