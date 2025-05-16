package com.example.samuraitravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.repository.FavoriteRepository;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.FavoriteService;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {
    private final HouseRepository houseRepository;
    private final FavoriteRepository favoriteRepository;
    private final FavoriteService favoriteService;

    public FavoriteController(HouseRepository houseRepository, FavoriteRepository favoriteRepository, FavoriteService favoriteService) {
        this.houseRepository = houseRepository;
        this.favoriteRepository = favoriteRepository;
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public String index(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model)
    {
        User user = userDetailsImpl.getUser();
        Page<Favorite> favoritePage = favoriteRepository.findByUserOrderByUpdatedAtDesc(user, pageable);
        model.addAttribute("favoritePage", favoritePage);
        return "favorites/index";
    }

    @GetMapping("/{id}/create")
    public String addfavorites(@PathVariable(name = "id") Integer id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model, RedirectAttributes redirectAttributes)
    {
        House house = houseRepository.getReferenceById(id);
        User user = userDetailsImpl.getUser();
        favoriteService.create(house, user);
        redirectAttributes.addFlashAttribute("successMessage", "お気に入りに追加しました。");
        return "redirect:/houses/{id}";
    }

    @GetMapping("/{id}/delete")
    public String deletefavorites(@PathVariable(name = "id") Integer id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model, RedirectAttributes redirectAttributes)
    {
        House house = houseRepository.getReferenceById(id);
        User user = userDetailsImpl.getUser();
        favoriteService.delete(house, user);
        redirectAttributes.addFlashAttribute("successMessage", "お気に入りを解除しました。");
        return "redirect:/houses/{id}";
    }
}