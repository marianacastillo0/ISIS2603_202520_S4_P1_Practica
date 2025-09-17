package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import java.util.*;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;

@DataJpaTest
@Transactional
@Import(UbicacionBodegaService.class)
public class UbicacionBodegaServiceTest {

    @Autowired
    private UbicacionBodegaService ubicacionBodegaService;

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private MercanciaService mercanciaService;

    private final PodamFactory factory=new PodamFactoryImpl();
    private final List<UbicacionBodegaEntity> ubicacionBodegaList=new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData()
    {
        entityManager.getEntityManager().createQuery("delete from UbicacionBodegaEntity").executeUpdate();
    }

     private void insertData()
    {
        for (int i=0;i<3;i++)
        {
            UbicacionBodegaEntity ubicacion=factory.manufacturePojo(UbicacionBodegaEntity.class);
            ubicacion.setId(null);
            ubicacion.setNumeroEstante(i);
            ubicacion.setNumeroCanasta(i);
            ubicacion.setPesoMaximo(1000);
            entityManager.persist(ubicacion);
            ubicacionBodegaList.add(ubicacion);
        }
    }

    @Test
    void crearUbicacionEsitosa()
    {
        UbicacionBodegaEntity pedido = factory.manufacturePojo(UbicacionBodegaEntity.class);
        entityManager.persist(pedido);

        UbicacionBodegaEntity nuevo = new UbicacionBodegaEntity();
        nuevo.setNumeroEstante(6);
        nuevo.setNumeroCanasta(7);
        nuevo.setPesoMaximo(1000);

        assertNotNull(nuevo);
     assertNotNull(nuevo.getId());
        
        UbicacionBodegaEntity crear=entityManager.find(UbicacionBodegaEntity.class, nuevo.getId());

        UbicacionBodegaEntity entity = entityManager.find(UbicacionBodegaEntity.class, crear.getId());
        assertEquals(6, crear.getNumeroEstante());
        assertEquals(7, crear.getNumeroCanasta());
    
}
}
