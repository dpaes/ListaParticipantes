package com.MundoSenaiTDSN.ListaParticipantes.Repository;

import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Pessoa extends JpaRepository<M_Pessoa, Long> {
    @Query(value = "SELECT * FROM pessoa WHERE id = :id", nativeQuery = true)
    M_Pessoa findById(@Param("id") String id);

    @Query(value = "SELECT * FROM pessoa WHERE cpf = :cpf AND senha = :senha", nativeQuery = true)
    M_Pessoa findByCPFeSenha(@Param("cpf") Long cpf, @Param("senha") String senha);
}