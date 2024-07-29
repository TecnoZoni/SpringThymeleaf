package com.agustin.videojuegos.services;

import com.agustin.videojuegos.model.Estudio;
import com.agustin.videojuegos.repository.EstudioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstudioService implements ServicioBase<Estudio> {

    @Autowired
    private EstudioRepository estuRepo;

    @Override
    @Transactional
    public List<Estudio> findAll() throws Exception {
        try {
            List<Estudio> videojuegos = this.estuRepo.findAll();
            return videojuegos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio findById(long id) throws Exception {
        try {
            Optional<Estudio> videojuego = this.estuRepo.findById(id);
            return videojuego.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio saveOne(Estudio entity) throws Exception {
        try {
            this.estuRepo.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio updateOne(Estudio entity, long id) throws Exception {
        try {
            Optional<Estudio> opt = this.estuRepo.findById(id);
            Estudio estudio = opt.get();
            estudio = this.estuRepo.save(entity);
            return estudio;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            Optional<Estudio> opt = this.estuRepo.findById(id);
            if (!opt.isEmpty()) {
                Estudio estudio = opt.get();
                estudio.setActivo(!estudio.isActivo());
                this.estuRepo.save(estudio);
            } else {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
