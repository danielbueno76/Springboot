package com.keepcoding.springboot.controller;

import com.keepcoding.springboot.dao.HeroService;
import com.keepcoding.springboot.model.Hero;
import com.keepcoding.springboot.exceptions.HeroNotFoundException;
import com.keepcoding.springboot.model.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class HeroController {

    @Autowired
    @Qualifier("jpa")
    private HeroService heroService;

    @GetMapping(value = "/hero")
    public List<Hero> findAllHeroes() {
        return heroService.findAll();
    }

    @GetMapping(value = "/hero/{id}")
    public Hero findHeroById(@PathVariable int id) {
        Hero result =  heroService.findHeroById(id);
        if (result == null) {
            throw new HeroNotFoundException("El heroe con id " + id + " no existe.");
        }
        return result;
    }

    @DeleteMapping(value = "/hero/{id}")
    public void deleteHeroById(@PathVariable int id) {
        Hero result = heroService.findHeroById(id);
        if (result == null) {
            throw new HeroNotFoundException("El heroe con id " + id + " no existe.");
        }
        heroService.deleteHero(id);
    }
    //POST
    // Devolver estado 201: Created
    // Devolver uri del nuevo recurso creado
    @PostMapping("/hero")
    public ResponseEntity<Object> addHero(@RequestBody @Valid Hero hero) {
        Hero addedHero = heroService.addHero(hero);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedHero.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/hero/{heroId}/power")
    public List<Power> findAllPowersByHeroId(@PathVariable int heroId) {

        return heroService.findAllPowersByHeroId(heroId);
    }

    @GetMapping(value = "/hero/{heroId}/power/{powerId}")
    public Power findAllPowersByHeroId(@PathVariable int heroId, @PathVariable int powerId) {

        return heroService.findPowerById(heroId, powerId);
    }

    @PostMapping("/hero/{heroId}/power")
    public ResponseEntity<Object> addPower(@PathVariable int heroId, @RequestBody @Valid Power power) {
        Power powerInserted = heroService.addPower(heroId, power);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{powerId}")
                .buildAndExpand(powerInserted.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/hero/{heroId}/power/{powerId}")
    public void deletePowerById(@PathVariable int heroId, @PathVariable int powerId) {
        heroService.deletePower(heroId, powerId);
    }
}
