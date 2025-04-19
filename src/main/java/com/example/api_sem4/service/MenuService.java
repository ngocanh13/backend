package com.example.api_sem4.service;

import com.example.api_sem4.dto.MenuDTO;
import com.example.api_sem4.entity.Menu;
import com.example.api_sem4.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu saveMenu(MenuDTO menuDTO) {
        // Kiểm tra nếu đã tồn tại day_of_week trong DB
        Optional<Menu> existingMenu = menuRepository.findByDayOfWeek(menuDTO.getDayOfWeek());
        if (existingMenu.isPresent()) {
            throw new RuntimeException("Day of week '" + menuDTO.getDayOfWeek() + "' already exists.");
        }

        Menu menu = new Menu();
        menu.setId(menuDTO.getId());
        menu.setDayOfWeek(menuDTO.getDayOfWeek());
        menu.setStart_date(menuDTO.getStart_date());
        menu.setEnd_date(menuDTO.getEnd_date());
        menu.setBreakfast(menuDTO.getBreakfast());
        menu.setSecond_breakfast(menuDTO.getSecond_breakfast());
        menu.setLunch(menuDTO.getLunch());
        menu.setDinner(menuDTO.getDinner());
        menu.setSecond_dinner(menuDTO.getSecond_dinner());
        return menuRepository.save(menu);
    }

    public Menu updateMenu(MenuDTO menuDTO) {
        Menu menu = saveMenu(menuDTO);
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    public List<Menu> saveMultipleMenus(List<MenuDTO> menuDTOs) {
        for (MenuDTO dto : menuDTOs) {
            Optional<Menu> existingMenu = menuRepository.findByDayOfWeek(dto.getDayOfWeek());
            if (existingMenu.isPresent()) {
                throw new RuntimeException("Duplicate day_of_week found: " + dto.getDayOfWeek());
            }
        }

        List<Menu> menus = menuDTOs.stream().map(dto -> {
            Menu menu = new Menu();
            menu.setId(dto.getId());
            menu.setDayOfWeek(dto.getDayOfWeek());
            menu.setStart_date(dto.getStart_date());
            menu.setEnd_date(dto.getEnd_date());
            menu.setBreakfast(dto.getBreakfast());
            menu.setSecond_breakfast(dto.getSecond_breakfast());
            menu.setLunch(dto.getLunch());
            menu.setDinner(dto.getDinner());
            menu.setSecond_dinner(dto.getSecond_dinner());
            return menu;
        }).collect(Collectors.toList());

        return menuRepository.saveAll(menus);
    }

    public void deleteAllMenus() {
        menuRepository.deleteAll();
    }
}
