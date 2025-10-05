package com.bootcamp.demo.demo_sb_calculator.controller;

import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_calculator.model.Database;
import com.bootcamp.demo.demo_sb_calculator.model.ShoppingMall.Cinema.Film;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class FilmController {
  @PostMapping("/film")
  public Film createFilm(@RequestBody Film film) {
    if (film != null) {
      if(!Database.filmDatabase.add(film)) {
        return null;
      }
    }
    return null;
  }
  
  // Get a Film by Film name
  @GetMapping("/film/{name}")
  public Film getFilm(@PathVariable String name) {
    for (Film film : Database.filmDatabase) {
      if (name != null && name.equals(film.getName())) {
        return film;
      }
    }
    return null;
  }

  // Get all -> return List<Film>
  @GetMapping("/films")
  public List<Film> getAFilms() {
    return Database.filmDatabase;
  }

  // DeleteMapping by name
  @DeleteMapping("/film/{name}")
  public Film deleteFilm(@PathVariable String name) {
    if (name == null) 
      return null;
    int idx = 0;
    for (Film film : Database.filmDatabase) {
      if (name.equals(film.getName())) {
        break;
      }
      ++idx;
    }
    return Database.filmDatabase.remove(idx);
  }  
  // Update: PUT and PATCH
  // ! PUT -> trust API user
  
}
