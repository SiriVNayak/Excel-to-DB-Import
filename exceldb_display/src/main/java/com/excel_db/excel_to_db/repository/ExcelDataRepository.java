package com.excel_db.excel_to_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel_db.excel_to_db.entity.ExcelData;

public interface ExcelDataRepository extends JpaRepository<ExcelData, Long>{

}
