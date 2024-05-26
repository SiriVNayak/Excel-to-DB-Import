package com.excel_db.excel_to_db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.excel_db.excel_to_db.repository.ExcelDataRepository;
import com.excel_db.excel_to_db.service.ExcelDataService;

@Controller
public class ExcelDataController {
	@Autowired
    private ExcelDataService excelDataService;

    @Autowired
    private ExcelDataRepository excelDataRepository ;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file, Model model) {
        excelDataService.saveExcelData(file);
        model.addAttribute("dataRecords", excelDataRepository.findAll());
        return "viewRecords";
    }

    @GetMapping("/viewRecords")
    public String viewRecords(Model model) {
        model.addAttribute("dataRecords", excelDataRepository.findAll());
        return "viewRecords";
    }
}
