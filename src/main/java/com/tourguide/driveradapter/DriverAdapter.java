package com.tourguide.driveradapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tourguide.entities.DriverEntity;

public class DriverAdapter {

	public List<DriverEntity> readExcel(String fileName) {

		List<DriverEntity> driversList = new ArrayList<>();

		try {
			FileInputStream excelFile;
			excelFile = new FileInputStream(new File(fileName));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			DataFormatter dataFormatter = new DataFormatter();

			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				if (currentRow.getRowNum() != 0) {
					Cell cellA = currentRow.getCell(0);
					Cell cellB = currentRow.getCell(1);
					Cell cellC = currentRow.getCell(2);

					String voornaam = "";
					String achternaam = "";
					String geboortedatum = "";

					if (cellA != null) {
						voornaam = dataFormatter.formatCellValue(cellA);
					}
					if (cellB != null) {
						achternaam = dataFormatter.formatCellValue(cellB);
					}
					if (cellA != null) {
						geboortedatum = dataFormatter.formatCellValue(cellC);
					}

					DriverEntity driver = convertToDriver(voornaam, achternaam, geboortedatum);
					if (driver != null) {
						driversList.add(driver);
					}

				}
			}
			workbook.close();
			return driversList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DriverEntity> readExcel2(String fileName) {

		List<DriverEntity> driversList = new ArrayList<>();

		try {
			FileInputStream excelFile;
			excelFile = new FileInputStream(new File(fileName));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			DataFormatter dataFormatter = new DataFormatter();

			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				if (currentRow.getRowNum() != 0) {
					Cell cellA = currentRow.getCell(0);
					Cell cellB = currentRow.getCell(1);
					Cell cellC = currentRow.getCell(2);

					String voornaam = "";
					String achternaam = "";
					String geboortedatum = "";

					if (cellA != null) {
						voornaam = dataFormatter.formatCellValue(cellA);
					}
					if (cellB != null) {
						achternaam = dataFormatter.formatCellValue(cellB);
					}
					if (cellA != null) {
						geboortedatum = dataFormatter.formatCellValue(cellC);
					}

					DriverEntity driver = convertToDriver(voornaam, achternaam, geboortedatum);
					if (driver != null) {
						driversList.add(driver);
					}

				}
			}
			workbook.close();
			return driversList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private DriverEntity convertToDriver(String voornaam, String achternaam, String geboortedatum) {
		DriverEntity driver = new DriverEntity();
		driver.setName(voornaam + " " + achternaam);
		driver.setDob(Date.valueOf(LocalDate.parse(geboortedatum)));
		System.out.println(driver.getName() + " / " + driver.getDob());
		return driver;
	}
}
