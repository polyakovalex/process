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
import ru.process.thymeleaf.entity.Place;
import ru.process.thymeleaf.repository.PlaceRepository;

@Controller
public class PlacesController {

  @Autowired
  private PlaceRepository placeRepository;

  @GetMapping("/placess")
  public String getAll(Model model) {
    try {
      List<Place> placess = new ArrayList<>();
      placeRepository.findAll().forEach(placess::add);

      model.addAttribute("placess", placess);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }

    return "placess";
  }

  @GetMapping("/placess/new")
  public String addPlacess(Model model) {
    Place place = new Place();
    model.addAttribute("place", place);
    model.addAttribute("pageTitle", "Создание нового места обработки");

    return "place_form";
  }

  @PostMapping("/placess/save")
  public String savePlacess(Place place, RedirectAttributes redirectAttributes) {
    try {
      placeRepository.save(place);

      redirectAttributes.addFlashAttribute("message", "Место обработки успешно добавлен!");
    } catch (Exception e) {
      redirectAttributes.addAttribute("message", e.getMessage());
    }

    return "redirect:/placess";
  }

  @GetMapping("/placess/{id}")
  public String editPlace(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      Place place = placeRepository.findById(id).get();

      model.addAttribute("place", place);
      model.addAttribute("pageTitle", "Редактирование места обработки (ID: " + id + ")");

      return "place_form";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());

      return "redirect:/placess";
    }
  }

  @GetMapping("/placess/delete/{id}")
  public String deletePlace(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      placeRepository.deleteById(id);

      redirectAttributes.addFlashAttribute("message", "Место обработки с id=" + id + " успешно удалено!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());
    }

    return "redirect:/placess";
  }

}
