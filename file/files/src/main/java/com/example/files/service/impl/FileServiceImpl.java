package com.example.files.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.files.dal.dto.file.FileUploadDTO;
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
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements FileService {
    @Resource
    FileMapper fileMapper;

    @Value("${file.path}") //存储路径
    private String path;

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
     * 获取列表
     *
     * @param file 入参
     * @return 列表
     */
    @Override
    public Result<List<FileListVO>> fileDetailList(FileQueryDTO file) {
        return Result.success(FileConvert.INSTANCE.list(fileMapper.queryFileList(file)));
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
            String filePath;//文件夹路径
            if (fileData.getParentId() != null) {
                QueryWrapper<FileEntity> wrapper = new QueryWrapper<>();

                wrapper.eq("id", fileData.getParentId());

                FileEntity parentFolder = fileMapper.selectOne(wrapper);

                filePath = parentFolder.getFilePath() + "/" + fileData.getFileName();
            } else {
                filePath = "/" + fileData.getFileName();
            }

            File directory = new File(path + filePath);

            fileData.setFilePath(filePath);

            //如果找不到目标文件夹自动创建文件夹
            if (!directory.exists()) {
                directory.mkdirs();
                this.saveOrUpdate(fileData);
                return Result.success(fileData);
            } else {
                return Result.error("文件已存在");
            }
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

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 文件上传信息
     */
    @Override
    public Result<FileEntity> uploadFileService(MultipartFile file, FileUploadDTO params) {
        FileEntity fileInfo = new FileEntity();

        // 检查文件是否为空
        if (file.isEmpty()) {
            return Result.error("文件内容为空");
        }

        String fileName = file.getOriginalFilename();
        String filePath = "";

        if (fileName == null) {
            return Result.error("读取不到文件名");
        }

        if (params.getParentId() != null) {
            FileEntity folder = fileMapper.selectById(params.getParentId());
            filePath = folder.getFilePath();
        }

        //上传成功保存文件信息
        fileInfo.setFileName(fileName);
        fileInfo.setFileSize(file.getSize());
        fileInfo.setFileType(FilenameUtils.getExtension(fileName));


        fileInfo.setUrl(filePath + "/" + fileName);
        fileInfo.setFilePath(filePath + "/" + fileName);
        fileInfo.setParentId(params.getParentId());

        try {
            File directory = new File(path + filePath + "/");
            //如果找不到目标文件夹自动创建文件夹
            if (!directory.exists()) {
                directory.mkdirs();
            }
            //格式 上传路径 + 上传文件名称
            File dest = new File(path + filePath + "/" + fileName);
            //文件写入路径
            file.transferTo(dest);

            // 将上传的文件保存到目标文件

            this.saveOrUpdate(fileInfo);

            return Result.success(fileInfo);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败");
        }
    }

}