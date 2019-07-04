package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.ClinicStaff;
import com.clinicmaster.clinic.domain.DepartmentChildZ;
import com.clinicmaster.clinic.domain.DepartmentParentZ;
import com.clinicmaster.clinic.domain.MedicineZ;
import com.clinicmaster.clinic.repository.ClinicStaffRepository;
import com.clinicmaster.clinic.repository.DepartmentChildRepositoryZ;
import com.clinicmaster.clinic.repository.DepartmentParentRepositoryZ;
import com.clinicmaster.clinic.repository.MedicineRepositoryZ;
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
//@RequestMapping("/clinic/importExcel")
public class DoClinicExcelController {

    @Autowired
    private ClinicStaffRepository clinicStaffRepository;

    @Autowired
    private MedicineRepositoryZ medicineRepositoryZ;

    @Autowired
    private DepartmentParentRepositoryZ departmentParentRepositoryZ;

    @Autowired
    private DepartmentChildRepositoryZ departmentChildRepositoryZ;

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

        List<MedicineZ> medicineZS = ExcelUtil.doMedicineExcel(file);

        medicineRepositoryZ.saveAll(medicineZS);

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

        List<DepartmentParentZ> departmentParentZS = ExcelUtil.doDepartmentParentExcel(parentfile);
        departmentParentRepositoryZ.saveAll(departmentParentZS);

        List<DepartmentChildZ> departmentChildZS = ExcelUtil.doDepartmentChildExcel(childfile);
        departmentChildRepositoryZ.saveAll(departmentChildZS);

        return new UnifyReponse(1, "success");
    }
}
