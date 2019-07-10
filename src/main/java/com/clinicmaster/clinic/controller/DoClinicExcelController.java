package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.ClinicStaff;
import com.clinicmaster.clinic.domain.DepartmentChild;
import com.clinicmaster.clinic.domain.DepartmentParent;
import com.clinicmaster.clinic.domain.Medicine;
import com.clinicmaster.clinic.repository.ClinicStaffRepository;
import com.clinicmaster.clinic.repository.DepartmentChildRepository;
import com.clinicmaster.clinic.repository.DepartmentParentRepository;
import com.clinicmaster.clinic.repository.MedicineRepository;
import com.clinicmaster.clinic.utils.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class DoClinicExcelController {

    @Autowired
    private ClinicStaffRepository clinicStaffRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private DepartmentParentRepository departmentParentRepository;

    @Autowired
    private DepartmentChildRepository departmentChildRepositoryZ;

    @ApiOperation("上传员工Excel")
    @PostMapping("/clinic/importExcel/clinicStaffExcel")
    public UnifyReponse doClinicStaffExcel(@RequestParam("excel_path") MultipartFile file) throws IOException {

        if (file == null) {
            return new UnifyReponse(0, "上传文件不能为空");
        }


        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return new UnifyReponse(0, "上传文件格式错误，请上传后缀为.xls或.xlsx的文件");
        }

        List<ClinicStaff> clinicStaffs = ExcelUtil.doClinicStaffExcel(file);

        clinicStaffRepository.saveAll(clinicStaffs);

        return new UnifyReponse(1, "success");
    }


    @ApiOperation("上传药品Excel")
    @PostMapping("/clinic/importExcel/medicineExcel")
    public UnifyReponse doMedicineExcel(@RequestParam("excel_path") MultipartFile file) throws IOException {

        if (file == null) {
            return new UnifyReponse(0, "上传文件不能为空");
        }


        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return new UnifyReponse(0, "上传文件格式错误，请上传后缀为.xls或.xlsx的文件");
        }

        List<Medicine> medicineZS = ExcelUtil.doMedicineExcel(file);

        medicineRepository.saveAll(medicineZS);

        return new UnifyReponse(1, "success");
    }

    @ApiOperation("上传部门Excel")
    @PostMapping("/clinic/importExcel/departmentExcel")
    public UnifyReponse doDepartmentExcel(@RequestParam("excel_parent_path") MultipartFile parentfile,
                                          @RequestParam("excel_child_path") MultipartFile childfile) throws IOException {

        if (parentfile == null && childfile == null) {
            return new UnifyReponse(0, "上传文件不能为空");
        }


        String pFileName = parentfile.getOriginalFilename();
        if (pFileName != null && !pFileName.matches("^.+\\.(?i)(xls)$") && !pFileName.matches("^.+\\.(?i)(xlsx)$")) {
            return new UnifyReponse(0, "上传文件格式错误，请上传后缀为.xls或.xlsx的文件");
        }


        String cFileName = childfile.getOriginalFilename();
        if (cFileName != null && !cFileName.matches("^.+\\.(?i)(xls)$") && !cFileName.matches("^.+\\.(?i)(xlsx)$")) {
            return new UnifyReponse(0, "上传文件格式错误，请上传后缀为.xls或.xlsx的文件");
        }

        List<DepartmentParent> departmentParentZS = ExcelUtil.doDepartmentParentExcel(parentfile);
        departmentParentRepository.saveAll(departmentParentZS);

        List<DepartmentChild> departmentChildZS = ExcelUtil.doDepartmentChildExcel(childfile);
        departmentChildRepositoryZ.saveAll(departmentChildZS);

        return new UnifyReponse(1, "success");
    }
}
