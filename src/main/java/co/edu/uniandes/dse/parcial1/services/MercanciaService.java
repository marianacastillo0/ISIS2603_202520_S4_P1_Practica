package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class MercanciaService {
    
    @Autowired
    private MercanciaRepository mercanciaRepository;
    
    @Transactional
    public MercanciaEntity createMercancia(MercanciaEntity mercancia) throws IllegalOperationException
    {
       List<MercanciaEntity> mercancias=mercanciaRepository.findAll();
       for(MercanciaEntity m:mercancias)
       {
            if (m.getCodigoBarras()==mercancia.getCodigoBarras())
            {
                throw new IllegalOperationException("El codigo de barras tiene que ser unico");
            }
       }
       if(mercancia.getNombre()==null)
       {
            throw new IllegalOperationException("El nombre de la mercancia no puede ser nulo");
       }
       LocalDateTime f=LocalDateTime.now();
       if (mercancia.getFechaRecepcion().isBefore(f))
       {
        throw new IllegalOperationException("La fecha de recepcion no puede ser antes de la fecha actual");
       }
       return mercanciaRepository.save(mercancia);
    }
}
