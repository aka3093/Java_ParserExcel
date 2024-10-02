package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Individual> individuals = new ArrayList<>();
    private static List<Company> companies= new ArrayList<>();

    public static void main(String[] args) throws Exception {
        XSSFSheet sheet = createSheet();
        listEmployees(sheet);

        System.out.println("The total amount of employees: " + individuals.size());
        for (Individual individual : individuals) {
            if (individual.getAge() < 20) {
                System.out.println("    Employees are under 20:\n    - " + individual.getName());
            }
        }
        System.out.println("The total amount of companies: " + companies.size());
    }

    private static void listEmployees(XSSFSheet sheet) {
        for (Row row : sheet) {
            // Skip the 1st and 2nd lines
            if (row.getRowNum() == 0 || row.getRowNum() == 1) {
                continue;
            }
            // Skip empty cells between table
            if (row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                continue;
            }
            // Gathering general data from the Sheet
            int id =(int) row.getCell(0).getNumericCellValue();
            String email = row.getCell(1).getStringCellValue();
            String phone = readCell(row.getCell(2)).replace(" ", "");
            String address = row.getCell(3).getStringCellValue();

            String iban = row.getCell(13).getStringCellValue();
            String bic = row.getCell(14).getStringCellValue();
            String accountHolder = row.getCell(15).getStringCellValue();
            BankAccount bankAccount = new BankAccount(iban, bic, accountHolder);
            // Choose appropriate type of user based on Has Children or not (as a flag)
            if (row.getCell(7).getCellType() != CellType.BLANK) {
                String firstName = row.getCell(5).getStringCellValue();
                String lastName = row.getCell(6).getStringCellValue();
                Boolean hasChildren = row.getCell(7).getBooleanCellValue();
                int age =(int) row.getCell(8).getNumericCellValue();

                Individual individual = new Individual(id, email, phone, address, firstName, lastName, hasChildren, age, bankAccount);

                individuals.add(individual);
            } else {
                String companyName = readCell(row.getCell(10));
                CompanyType typeCompany = CompanyType.valueOf(readCell(row.getCell(11)).toUpperCase());

                Company company = new Company(id, email, phone, address, companyName, typeCompany, bankAccount);

                companies.add(company);
            }
        }
    }

    private static String readCell(Cell cell) {
        if (cell.getCellType().name().equals("STRING")) {
            return cell.getStringCellValue();
        } else {
            Double temp = cell.getNumericCellValue();
            return String.valueOf(temp.longValue());
        }
    }

    private static XSSFSheet createSheet() throws Exception {
        File file1 = new File(Main.class.getClassLoader().getResource("test_table.xlsx").getFile());
        FileInputStream file = new FileInputStream(file1);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        return workbook.getSheetAt(0);
    }
}