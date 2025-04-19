package com.example.api_sem4.controller;

import com.example.api_sem4.dto.MenuDTO;
import com.example.api_sem4.entity.Menu;
import com.example.api_sem4.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping()
    public ResponseEntity<Menu> addMenu(@Valid @RequestBody MenuDTO menuDTO) {
        try {
            Menu savedMenu = menuService.saveMenu(menuDTO);
            return ResponseEntity.ok(savedMenu);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Trả về 400 nếu `day_of_week` trùng lặp
        }
    }

    @GetMapping()
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long id, @Valid @RequestBody MenuDTO menuDTO) {
        Optional<Menu> existingMenu = menuService.getMenuById(id);
        if (existingMenu.isPresent()) {
            menuDTO.setId(id);
            Menu updatedMenu = menuService.saveMenu(menuDTO);
            return ResponseEntity.ok(updatedMenu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        Optional<Menu> existingMenu = menuService.getMenuById(id);
        if (existingMenu.isPresent()) {
            menuService.deleteMenu(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/bulk-save")
    public ResponseEntity<List<Menu>> addMultipleMenus(@RequestBody List<MenuDTO> menuDTOs) {
        List<Menu> savedMenus = menuService.saveMultipleMenus(menuDTOs);
        return ResponseEntity.ok(savedMenus);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAllMenus() {
        menuService.deleteAllMenus();
        return ResponseEntity.noContent().build();
    }
}
