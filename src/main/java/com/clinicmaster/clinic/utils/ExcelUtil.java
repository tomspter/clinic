package com.clinicmaster.clinic.utils;

import com.clinicmaster.clinic.domain.ClinicStaff;
import com.clinicmaster.clinic.domain.DepartmentChild;
import com.clinicmaster.clinic.domain.DepartmentParentZ;
import com.clinicmaster.clinic.domain.MedicineZ;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ExcelUtil {


    public static List<ClinicStaff> doClinicStaffExcel(MultipartFile file) throws IOException {

        List<ClinicStaff> clinicStaffs=new ArrayList<>();

        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());

        int numberOfSheets=workbook.getNumberOfSheets();

        for (int sheetNum = 0; sheetNum < numberOfSheets; sheetNum++) {
            XSSFSheet sheet=workbook.getSheetAt(sheetNum);

            int rows =sheet.getPhysicalNumberOfRows();

            ClinicStaff clinicStaff;

            for (int rowsNum = 1; rowsNum < rows; rowsNum++) {
                clinicStaff=new ClinicStaff();

                XSSFRow xssfRow=sheet.getRow(rowsNum);
                clinicStaff.setId(Integer.valueOf(xssfRow.getCell(0).toString()));
                clinicStaff.setName(xssfRow.getCell(1).toString());

                if (xssfRow.getCell(2).toString().equals("男")){
                    clinicStaff.setSex(1);
                }else {
                    clinicStaff.setSex(0);
                }

                clinicStaff.setBirth(xssfRow.getCell(3).toString());
                clinicStaff.setTel(xssfRow.getCell(4).toString());
                clinicStaff.setPhoto(xssfRow.getCell(5).toString());
                clinicStaff.setTitle(xssfRow.getCell(6).toString());

                clinicStaffs.add(clinicStaff);

            }
        }

        return clinicStaffs;

    }



    public static List<MedicineZ> doMedicineExcel(MultipartFile file) throws IOException {

        List<MedicineZ> medicineZS =new ArrayList<>();

        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());

        int numberOfSheets=workbook.getNumberOfSheets();

        for (int sheetNum = 0; sheetNum < numberOfSheets; sheetNum++) {
            XSSFSheet sheet=workbook.getSheetAt(sheetNum);

            int rows =sheet.getPhysicalNumberOfRows();

            MedicineZ medicineZ;

            for (int rowsNum = 1; rowsNum < rows; rowsNum++) {
                medicineZ =new MedicineZ();

                XSSFRow xssfRow=sheet.getRow(rowsNum);
                medicineZ.setId(Integer.valueOf(xssfRow.getCell(0).toString()));
                medicineZ.setName(xssfRow.getCell(1).toString());
                medicineZ.setTotalNum(Integer.valueOf(xssfRow.getCell(3).toString()));
                medicineZ.setRestNum(Integer.valueOf(xssfRow.getCell(3).toString()));
                medicineZ.setMoney(new BigDecimal(xssfRow.getCell(3).toString()));

                medicineZS.add(medicineZ);

            }
        }

        return medicineZS;

    }


    public static List doDepartmentParentExcel(MultipartFile file) throws IOException {

        List<DepartmentParentZ> departmentParentZS =new ArrayList<>();

        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());

        int numberOfSheets=workbook.getNumberOfSheets();

        for (int sheetNum = 0; sheetNum < numberOfSheets; sheetNum++) {

            XSSFSheet sheet=workbook.getSheetAt(sheetNum);

            int rows =sheet.getPhysicalNumberOfRows();

            DepartmentParentZ departmentParentZ;

            for (int rowsNum = 1; rowsNum < rows; rowsNum++) {
                departmentParentZ =new DepartmentParentZ();

                XSSFRow xssfRow=sheet.getRow(rowsNum);
                departmentParentZ.setId(Integer.valueOf(xssfRow.getCell(0).toString()));
                departmentParentZ.setParentName(xssfRow.getCell(1).toString());
                departmentParentZ.setClinicId(Integer.valueOf(xssfRow.getCell(2).toString()));
                departmentParentZ.setChildId(Integer.valueOf(xssfRow.getCell(3).toString()));

                departmentParentZS.add(departmentParentZ);

            }
        }

        return departmentParentZS;

    }

    public static List doDepartmentChildExcel(MultipartFile file) throws IOException {

        List<DepartmentChild> departmentChildrenZS =new ArrayList<>();


        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());

        int numberOfSheets=workbook.getNumberOfSheets();

        for (int sheetNum = 0; sheetNum < numberOfSheets; sheetNum++) {

            XSSFSheet sheet=workbook.getSheetAt(sheetNum);

            int rows =sheet.getPhysicalNumberOfRows();

            DepartmentChild departmentChildZ;

            for (int rowsNum = 1; rowsNum < rows; rowsNum++) {
                departmentChildZ =new DepartmentChild();

                XSSFRow xssfRow=sheet.getRow(rowsNum);
                departmentChildZ.setId(Integer.valueOf(xssfRow.getCell(0).toString()));
                departmentChildZ.setParentId(Integer.valueOf(xssfRow.getCell(1).toString()));
                departmentChildZ.setChildName(xssfRow.getCell(2).toString());
                departmentChildZ.setDoctorId(Integer.valueOf(xssfRow.getCell(3).toString()));
                departmentChildZ.setCount(Integer.valueOf(xssfRow.getCell(4).toString()));

                departmentChildrenZS.add(departmentChildZ);

            }
        }

        return departmentChildrenZS;

    }
}
