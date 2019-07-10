package com.clinicmaster.clinic.utils;

import com.clinicmaster.clinic.domain.ClinicStaff;
import com.clinicmaster.clinic.domain.DepartmentChild;
import com.clinicmaster.clinic.domain.DepartmentParent;
import com.clinicmaster.clinic.domain.Medicine;
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



    public static List<Medicine> doMedicineExcel(MultipartFile file) throws IOException {

        List<Medicine> medicineZS =new ArrayList<>();

        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());

        int numberOfSheets=workbook.getNumberOfSheets();

        for (int sheetNum = 0; sheetNum < numberOfSheets; sheetNum++) {
            XSSFSheet sheet=workbook.getSheetAt(sheetNum);

            int rows =sheet.getPhysicalNumberOfRows();

            Medicine medicine;

            for (int rowsNum = 1; rowsNum < rows; rowsNum++) {
                medicine =new Medicine();

                XSSFRow xssfRow=sheet.getRow(rowsNum);
                medicine.setId(Integer.valueOf(xssfRow.getCell(0).toString()));
                medicine.setName(xssfRow.getCell(1).toString());
                medicine.setTotalNum(Integer.valueOf(xssfRow.getCell(3).toString()));
                medicine.setRestNum(Integer.valueOf(xssfRow.getCell(3).toString()));
                medicine.setMoney(new BigDecimal(xssfRow.getCell(3).toString()));

                medicineZS.add(medicine);

            }
        }

        return medicineZS;

    }


    public static List doDepartmentParentExcel(MultipartFile file) throws IOException {

        List<DepartmentParent> departmentParentZS =new ArrayList<>();

        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());

        int numberOfSheets=workbook.getNumberOfSheets();

        for (int sheetNum = 0; sheetNum < numberOfSheets; sheetNum++) {

            XSSFSheet sheet=workbook.getSheetAt(sheetNum);

            int rows =sheet.getPhysicalNumberOfRows();

            DepartmentParent departmentParent;

            for (int rowsNum = 1; rowsNum < rows; rowsNum++) {
                departmentParent =new DepartmentParent();

                XSSFRow xssfRow=sheet.getRow(rowsNum);
                departmentParent.setId(Integer.valueOf(xssfRow.getCell(0).toString()));
                departmentParent.setParentName(xssfRow.getCell(1).toString());
                departmentParent.setClinicId(Integer.valueOf(xssfRow.getCell(2).toString()));
//                departmentParent.setChildId(Integer.valueOf(xssfRow.getCell(3).toString()));

                departmentParentZS.add(departmentParent);

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
