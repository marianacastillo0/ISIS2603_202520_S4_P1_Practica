package co.edu.uniandes.dse.parcial1.services;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import static org.junit.jupiter.api.Assertions.*;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;


@DataJpaTest
@Transactional
@Import(MercanciaService.class)
public class MercanciaServiceTest {
    
    @Autowired
    private MercanciaService mercanciaService;

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private UbicacionBodegaService UbicacionBodegaService;

    private final PodamFactory factory=new PodamFactoryImpl();
    private final List<MercanciaEntity> mercanciaList=new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData()
    {
        entityManager.getEntityManager().createQuery("delete from MercanciaEntity").executeUpdate();
    }

     private void insertData() {
    for (int i = 0; i < 3; i++) {
        MercanciaEntity mercancia = new MercanciaEntity();
        mercancia.setId(null); 
        mercancia.setNombre("Mercancia " + i);
        mercancia.setCodigoBarras("CB" + i);
        mercancia.setFechaRecepcion(LocalDateTime.of(2026, 4, 14, 10, 0));
        mercancia.setCantidadDisponible(100 + i);

        entityManager.persist(mercancia);
        mercanciaList.add(mercancia);
    }
    entityManager.flush();
}


    @Test
    void testCrearMercanciaExito() throws IllegalOperationException {

    MercanciaEntity nueva = new MercanciaEntity();
    nueva.setNombre("merc");
    nueva.setCodigoBarras("0123");                         
    nueva.setFechaRecepcion(LocalDateTime.of(2026, 4, 14, 10, 0)); 
    nueva.setCantidadDisponible(1000);                  

    LocalDateTime before = LocalDateTime.now();            

    MercanciaEntity crear = mercanciaService.createMercancia(nueva);
    assertNotNull(crear);
    assertNotNull(crear.getId()); 

    MercanciaEntity prueba = entityManager.find(MercanciaEntity.class, crear.getId());
    assertEquals("0123", prueba.getCodigoBarras());
    assertTrue(prueba.getFechaRecepcion().isAfter(before)); 
    assertEquals(1000, prueba.getCantidadDisponible());
}


    @Test
    void testCrearMercanciaSinExito() throws IllegalOperationException
    {
    MercanciaEntity nueva = new MercanciaEntity();
    nueva.setNombre(null);
    nueva.setCodigoBarras("0123");                         
    nueva.setFechaRecepcion(LocalDateTime.of(2025, 4, 14, 10, 0)); 
    nueva.setCantidadDisponible(1000);                  

    LocalDateTime before = LocalDateTime.now();            

    MercanciaEntity crear = mercanciaService.createMercancia(nueva);
    assertNotNull(crear);
    assertNotNull(crear.getId()); 

    MercanciaEntity prueba = entityManager.find(MercanciaEntity.class, crear.getId());
    assertEquals("0123", prueba.getCodigoBarras());
    assertTrue(prueba.getFechaRecepcion().isAfter(before)); 
    assertEquals(1000, prueba.getCantidadDisponible());
    }
}
