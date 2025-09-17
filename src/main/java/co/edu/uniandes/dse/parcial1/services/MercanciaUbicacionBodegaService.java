package co.edu.uniandes.dse.parcial1.services;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Slf4j
@Service
public class MercanciaUbicacionBodegaService {

    @Autowired
    private MercanciaRepository mercanciaRepository;

    @Autowired
    private UbicacionBodegaRepository ubicacionBodegaRepository;
    
    public MercanciaEntity asociarUbicacionBodega(Long mercanciaID, Long UbicacionBodegaID)throws IllegalOperationException, EntityNotFoundException
    {
        MercanciaEntity mercancia=mercanciaRepository.findById(mercanciaID).orElseThrow(()->new EntityNotFoundException("Mercancia no encontrada"));
        UbicacionBodegaEntity ubicacionBodega=ubicacionBodegaRepository.findById(mercanciaID).orElseThrow(()->new EntityNotFoundException("Ubicacion no encontrada"));

        mercancia.setUbicacionBodegaEntity(ubicacionBodega);
        return mercanciaRepository.save(mercancia);
    }
}
