package com.keepcoding.springboot.dao;

import com.keepcoding.springboot.model.Hero;
import com.keepcoding.springboot.model.Power;
import com.keepcoding.springboot.repository.HeroRepository;
import com.keepcoding.springboot.repository.PowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("jpa")
public class HeroJpaService implements HeroService{

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerRepository powerRepository;

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }
    @Override
    public Hero findHeroById(int id) {
        return heroRepository.findById(id).orElse(null);
    }
    @Override
    public Hero addHero(Hero hero) {
        return heroRepository.save(hero);
    }
    @Override
    public void deleteHero(int id) {
        heroRepository.deleteById(id);
    }

    @Override
    public List<Power> findAllPowersByHeroId(int heroId) {
        Hero hero = heroRepository.findById(heroId).orElse(null);
        if(hero != null) {
            return hero.getPowers();
        }
        return null;
    }

    @Override
    public Power findPowerById(int heroId, int powerId) {
        Hero hero = heroRepository.findById(heroId).orElse(null);
        if(hero != null && hero.getPowers() != null && !hero.getPowers().isEmpty()) {
            return hero.getPowers()
                    .stream()
                    .filter(power -> power.getId() == powerId)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public Power addPower(int heroId, Power power) {
        Hero hero = heroRepository.findById(heroId).orElse(null);
        if(hero != null) {
            power.setHero(hero);
            return powerRepository.save(power);
        }
        return null;
    }

    @Override
    public void deletePower(int heroId, int powerId) {
        Power power = findPowerById(heroId, powerId);
        if(power != null) {
            powerRepository.delete(power);
        }
    }
}
