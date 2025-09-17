package co.edu.uniandes.dse.parcial1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonSerializable.Base;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;

@Data
@Entity
public class MercanciaEntity extends BaseEntity{
   private String nombre;
   private String codigoBarras;
   private LocalDateTime fechaRecepcion;
   private Integer cantidadDisponible;
   @ManyToOne
   private UbicacionBodegaEntity ubicacionBodegaEntity;
}
