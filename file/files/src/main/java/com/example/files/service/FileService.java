package com.example.files.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.files.dal.dto.file.FileQueryDTO;
import com.example.files.dal.dto.file.FileSaveDTO;
import com.example.files.dal.entity.FileEntity;
import com.example.files.dal.vo.file.FileDetailVO;
import com.example.files.dal.vo.file.FileListVO;
import com.example.files.dal.vo.file.FilePageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface FileService {
    /**
     * 获取列表分页
     *
     * @param file 分页入参
     * @return 列表分页
     */
    Result<PageList<FilePageVO>> filePage(FileQueryDTO file);

    /**
     * 获取列表
     *
     * @param file 入参
     * @return 列表
     */
    Result<List<FileListVO>> fileList(FileQueryDTO file);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<FileDetailVO> fileDetail(Long id);

    /**
     * 新增/修改
     *
     * @param file 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<FileEntity> fileSave(FileSaveDTO file);

    /**
     * 批量新增/修改
     *
     * @param file 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<FileEntity>> fileSaveList(@Valid List<FileSaveDTO> file);

    /**
     * 导出
     *
     * @param response,file 入参
     */
    void fileExport(HttpServletResponse response, FileQueryDTO file) throws IOException;
}