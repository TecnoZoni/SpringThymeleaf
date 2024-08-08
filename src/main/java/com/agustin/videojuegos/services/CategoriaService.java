package com.agustin.videojuegos.services;

import com.agustin.videojuegos.model.Categoria;
import com.agustin.videojuegos.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService implements ServicioBase<Categoria> {

    @Autowired
    private CategoriaRepository cateRepo;

    @Override
    @Transactional
    public List<Categoria> findAll() throws Exception {
        try {
            List<Categoria> categoria = this.cateRepo.findAll();
            return categoria;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Categoria findById(long id) throws Exception {
        try {
            Optional<Categoria> catoria = this.cateRepo.findById(id);
            return catoria.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Categoria saveOne(Categoria entity) throws Exception {
        try {
            this.cateRepo.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Categoria updateOne(Categoria entity, long id) throws Exception {
        try {
            Optional<Categoria> opt = this.cateRepo.findById(id);
            Categoria categoria = opt.get();
            categoria = this.cateRepo.save(entity);
            return categoria;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            Optional<Categoria> opt = this.cateRepo.findById(id);
            if (!opt.isEmpty()) {
                Categoria categoria = opt.get();
                categoria.setActivo(!categoria.isActivo());
                this.cateRepo.save(categoria);
            } else {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
