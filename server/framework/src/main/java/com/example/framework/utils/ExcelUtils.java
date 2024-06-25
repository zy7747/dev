package com.example.framework.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Excel 工具类
 */
public class ExcelUtils {

    /**
     * 将列表以 Excel 响应给前端
     *
     * @param is   数据流
     * @param head Excel head 头
     * @param <T>  泛型，保证 head 和 data 类型的一致性
     */
    public static <T> List<T> imports(InputStream is, Class<T> head) {
        // 输出 Excel
        return EasyExcel.read(is).head(head).sheet().doReadSync();
    }

    public static HorizontalCellStyleStrategy handlerStyleWrite() {
        // 创建一个写出的单元格样式对象
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();

        // 设置填充样式
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        // 设置表头字体
        WriteFont headWriteFont = new WriteFont();
        WriteFont contentWriteFont = new WriteFont();

        XSSFColor hdBgc = new XSSFColor(new java.awt.Color(248, 248, 249));
        XSSFColor hdFc = new XSSFColor(new java.awt.Color(96, 98, 102));

        //-----------------------------------------------------------------------------------------
        // 标题文字颜色
        headWriteFont.setColor(IndexedColors.ORANGE.getIndex());
        // 标题字体大小
        headWriteFont.setFontHeightInPoints((short) 16);
        // 内容字体大小
        contentWriteFont.setFontHeightInPoints((short) 12);
        // 字体
        contentWriteFont.setFontName("Arial");

        //设置头背景颜色
        headWriteCellStyle.setFillForegroundColor((short) 42);

        //内容边框
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setWrapped(true);
        //垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //左右居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        //-----------------------------------------------------------------------------------------


        // 把字体对象设置到单元格样式对象中
        headWriteCellStyle.setWriteFont(headWriteFont);
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }


    /**
     * 将列表以 Excel 响应给前端
     *
     * @param response  响应
     * @param fileName  文件名
     * @param sheetName Excel sheet 名
     * @param head      Excel head 头
     * @param list      数据列表
     * @param <T>       泛型，保证 head 和 data 类型的一致性
     * @throws IOException 写入失败的情况
     */
    public static <T> void export(HttpServletResponse response, String fileName, String sheetName, Class<T> head, List<T> list) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");


        // 输出 Excel
        EasyExcel.write(response.getOutputStream(), head)
                .registerWriteHandler(handlerStyleWrite())
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet(sheetName + ".xlsx")
                .doWrite(list);
    }
}
