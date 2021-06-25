package de.cmis.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "TBL_CMISTEST", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })

public class TestMessageEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer messageId;

	@Column(name = "Test_Name")
	private String testName;

	@Column(name = "Test_Package")
	private String testPackage;

	@Column(name = "Test_Ergebnis",columnDefinition = "NVARCHAR(MAX)")
	private String testErgebnis;

	@Column(name = "Test_Datum")
	private String testDatum;

	public void setAll(String testName, String testPackage, String testErgebnis) {
		this.testName = testName;
		this.testPackage = testPackage;
		this.testErgebnis = testErgebnis;
		setTestDatum();
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public void setTestPackage(String testPackage) {
		this.testPackage = testPackage;
	}

	public void setTestErgebnis(String testErgebnis) {
		this.testErgebnis = testErgebnis;
	}

	public void setTestDatum() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");
		this.testDatum = now.format(df);
	}

}
