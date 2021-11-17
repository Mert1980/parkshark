package com.switchfully.parkshark.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Builder;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "address", schema = "parkshark")
public class Address {

  @Id
  @SequenceGenerator(name = "address_address_id_seq", sequenceName = "address_address_id_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_address_id_seq")
  private Long address_id;
  @Column(name = "streetname")
  private String streetName;
  @Column(name = "streetnumber")
  private String streetNumber;
  @Column(name = "city")
  private String cityName;
  @Column(name = "postal_code")
  private String postalCode;

}
