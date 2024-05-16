package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Goods;
import com.example.smsbackspringboot.demos.entiy.Purchase;
import com.example.smsbackspringboot.demos.service.purchaseService;
import com.example.smsbackspringboot.demos.vo.commom.GoodsItemVo;
import com.example.smsbackspringboot.demos.vo.commom.PurchaseInfoVo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class ExcelExportController {
    @Autowired
    com.example.smsbackspringboot.demos.service.purchaseService purchaseService;

    @GetMapping("/purchase/exportToExcel")
    public void exportToExcel(String filePath) throws IOException {
        System.out.println("filePath"+filePath);
        List<PurchaseInfoVo> purchaseInfoVoList = purchaseService.getAllPurchaseList();

//        // 创建 Excel 工作簿和工作表
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("PurchaseList");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Supplier Name");
        headerRow.createCell(2).setCellValue("Create Time");
        headerRow.createCell(3).setCellValue("Detail");

        // 创建商品列表的表头
        headerRow.createCell(4).setCellValue("Good ID");
        headerRow.createCell(5).setCellValue("Good Name");
        headerRow.createCell(6).setCellValue("Purchase quantity");
        headerRow.createCell(7).setCellValue("Purchase Price");

        for (PurchaseInfoVo purchaseInfoVo:purchaseInfoVoList){
            int rowNum = 1;
            // 写入purchase数据
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(purchaseInfoVo.getId());
            row.createCell(1).setCellValue(purchaseInfoVo.getSupplierName());
            row.createCell(2).setCellValue(purchaseInfoVo.getCreateTime());
            row.createCell(3).setCellValue(purchaseInfoVo.getDetail());

            // 写入商品列表数据
            for (GoodsItemVo good : purchaseInfoVo.getGoodsList()) {
                sheet.createRow(rowNum++);
                row.createCell(4).setCellValue(good.getGoodId());
                row.createCell(5).setCellValue(good.getGoodName());
                row.createCell(6).setCellValue(good.getCount());
                row.createCell(7).setCellValue(good.getGoodCost());
            }
        }
        File file = new File(filePath);

        if(file.exists()) {
            System.out.println("文件存在");
// 打开文件进行读取或写入
        } else {
            System.out.println("文件不存在");
        }
//
//        // 自动调整列宽
        for (int i = 0; i < 7; i++) {
            sheet.autoSizeColumn(i);
        }

//        // 写入文件
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            workbook.close(); // 关闭工作簿释放资源
        }
//        return Result.success("导出")
    }
}
