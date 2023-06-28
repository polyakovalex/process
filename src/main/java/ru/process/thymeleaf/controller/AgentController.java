package ru.process.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.process.thymeleaf.entity.Agent;
import ru.process.thymeleaf.repository.AgentRepository;

@Controller
public class AgentController {

  @Autowired
  private AgentRepository agentRepository;

  @GetMapping("/agents")
  public String getAll(Model model) {
    try {
      List<Agent> agents = new ArrayList<>();

      agentRepository.findAll().forEach(agents::add);

      model.addAttribute("agents", agents);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }

    return "agents";
  }

  @GetMapping("/agents/new")
  public String addAgent(Model model) {
    Agent agent = new Agent();
    model.addAttribute("agent", agent);
    model.addAttribute("pageTitle", "Создание агента");

    return "agent_form";
  }

  @PostMapping("/agents/save")
  public String saveAgent(Agent agent, RedirectAttributes redirectAttributes) {
    try {
      agentRepository.save(agent);

      redirectAttributes.addFlashAttribute("message", "Агент успешно добавлен!");
    } catch (Exception e) {
      redirectAttributes.addAttribute("message", e.getMessage());
    }

    return "redirect:/agents";
  }

  @GetMapping("/agents/{id}")
  public String editAgents(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      Agent agent = agentRepository.findById(id).get();

      model.addAttribute("agent", agent);
      model.addAttribute("pageTitle", "Изменение агента (ID: " + id + ")");

      return "agent_form";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());

      return "redirect:/agents";
    }
  }

  @GetMapping("/agents/delete/{id}")
  public String deleteAgents(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      agentRepository.deleteById(id);

      redirectAttributes.addFlashAttribute("message", "Агент с  id=" + id + " успешно удален!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());
    }

    return "redirect:/agents";
  }

}
