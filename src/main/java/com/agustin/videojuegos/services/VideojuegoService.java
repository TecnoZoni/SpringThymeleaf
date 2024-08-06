package com.agustin.videojuegos.services;

import com.agustin.videojuegos.model.Videojuego;
import com.agustin.videojuegos.repository.VideojuegoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VideojuegoService implements ServicioBase<Videojuego> {
    
    @Autowired
    private VideojuegoRepository videoRepo;
    
    @Override
    @Transactional
    public List<Videojuego> findAll() throws Exception {
        try {
            List<Videojuego> videojuegos = this.videoRepo.findAll();
            return videojuegos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public Videojuego findById(long id) throws Exception {
        try {
            Optional<Videojuego> videojuego = this.videoRepo.findById(id);
            return videojuego.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public Videojuego saveOne(Videojuego entity) throws Exception {
        try {
            this.videoRepo.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public Videojuego updateOne(Videojuego entity, long id) throws Exception {
        try {
            Optional<Videojuego> opt = this.videoRepo.findById(id);
            Videojuego videojuego = opt.get();
            videojuego = this.videoRepo.save(entity);
            return videojuego;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            Optional<Videojuego> opt = this.videoRepo.findById(id);
            if (!opt.isEmpty()) {
                Videojuego videojuego = opt.get();
                videojuego.setActivo(!videojuego.isActivo());
                this.videoRepo.save(videojuego);
            } else {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public List<Videojuego> finAllByActivo() throws Exception {
        try {
            List<Videojuego> entities = this.videoRepo.findAllByActivo();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public Videojuego findByIdAndActivo(long id) throws Exception {
        try {
            Optional<Videojuego> opt = this.videoRepo.findByIdActivo(id);
            return opt.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public List<Videojuego> findByTitle(String titulo) throws Exception {
        try {
            List<Videojuego> entities = this.videoRepo.findByTitle(titulo);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}
