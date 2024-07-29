package com.agustin.videojuegos.repository;

import com.agustin.videojuegos.model.Videojuego;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {

    @Query(value = "SELECT * FROM videojuegos WHERE videojuegos.activo = true", nativeQuery = true)
    List<Videojuego> findAllByActivo();

    @Query(value = "SELECT * FROM videojuegos WHERE videojuegos.id= :id AND videojuegos.activo = true", nativeQuery = true)
    Optional<Videojuego> findByIdActivo(@Param("id") long id);

}
