/**
 * 
 */
package org.ms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Created on 10.04.2017 18:18:39
 * 
 * @author Hubert Popio³kiewicz
 */
@Entity
@Table(name = "t_measurement_type", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class MeasurementType extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "measurement_type_id")
	private Long id;

	@NotNull
	@Column(name = "code")
	private String code;

	@NotNull
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "unit")
	private String unit;

	@Column(name = "top_critical_bound")
	private Double topCriticalBound;

	@Column(name = "bottom_critical_bound")
	private Double bottomCriticalBound;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id")
	private Device device;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "measurementType")
	private List<Measurement> measurements;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTopCriticalBound() {
		return topCriticalBound;
	}

	public void setTopCriticalBound(Double topCriticalBound) {
		this.topCriticalBound = topCriticalBound;
	}

	public Double getBottomCriticalBound() {
		return bottomCriticalBound;
	}

	public void setBottomCriticalBound(Double bottomCriticalBound) {
		this.bottomCriticalBound = bottomCriticalBound;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

}
