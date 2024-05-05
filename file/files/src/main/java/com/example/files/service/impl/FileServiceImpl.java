package com.example.files.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.files.convert.FileConvert;
import com.example.files.dal.dto.file.FileQueryDTO;
import com.example.files.dal.dto.file.FileSaveDTO;
import com.example.files.dal.entity.FileEntity;
import com.example.files.dal.vo.file.FileExportVO;
import com.example.files.mapper.FileMapper;
import com.example.files.dal.vo.file.FileDetailVO;
import com.example.files.dal.vo.file.FileListVO;
import com.example.files.dal.vo.file.FilePageVO;
import com.example.files.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements FileService {
    @Resource
    FileMapper fileMapper;

    /**
     * 获取列表分页
     *
     * @param file 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<FilePageVO>> filePage(FileQueryDTO file) {
        return Result.success(FileConvert.INSTANCE.page(fileMapper.queryPage(file)));
    }

    /**
     * 获取列表
     *
     * @param file 入参
     * @return 列表
     */
    @Override
    public Result<List<FileListVO>> fileList(FileQueryDTO file) {
        return Result.success(FileConvert.INSTANCE.list(fileMapper.queryList(file)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<FileDetailVO> fileDetail(Long id) {
        return Result.success(FileConvert.INSTANCE.detail(fileMapper.selectById(id)));
    }

    /**
     * 新增/修改
     *
     * @param file 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<FileEntity> fileSave(FileSaveDTO file) {
        FileEntity fileData = FileConvert.INSTANCE.save(file);
        List<FileEntity> fileList = fileMapper.queryList(new FileQueryDTO());
        Result<FileEntity> valid = fileMapper.onlyValid(fileData, fileList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(fileData);
            return Result.success(fileData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param file 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<FileEntity>> fileSaveList(List<FileSaveDTO> file) {
        List<FileEntity> fileData = FileConvert.INSTANCE.saveList(file);
        Result<List<FileEntity>> valid = fileMapper.onlyValidList(fileData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(fileData);
            return Result.success(fileData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,file 入参
     */
    @Override
    public void fileExport(HttpServletResponse response, FileQueryDTO file) throws IOException {
        ExcelUtils.export(response, "文件.xlsx", "文件", FileExportVO.class, FileConvert.INSTANCE.export(fileMapper.queryList(file)));
    }
}