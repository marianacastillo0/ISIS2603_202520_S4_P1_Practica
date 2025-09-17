package co.edu.uniandes.dse.parcial1.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import java.util.*;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Data
@Entity
public class UbicacionBodegaEntity extends BaseEntity{
   private Integer numeroEstante;
   private Integer numeroCanasta;
   private Integer pesoMaximo;

   @OneToMany(mappedBy="ubicacionBodega", cascade = CascadeType.PERSIST, orphanRemoval = true)
   private List<MercanciaEntity> mercancia=new ArrayList<>();
    
}
