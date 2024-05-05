package com.example.files.convert;

import com.example.framework.common.PageList;
import com.example.files.dal.dto.file.FileSaveDTO;
import com.example.files.dal.entity.FileEntity;
import com.example.files.dal.vo.file.FileDetailVO;
import com.example.files.dal.vo.file.FileExportVO;
import com.example.files.dal.vo.file.FileListVO;
import com.example.files.dal.vo.file.FilePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FileConvert {
    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);

    PageList<FilePageVO> page(PageList<FileEntity> file);

    List<FileListVO> list(List<FileEntity> file);

    FileDetailVO detail(FileEntity file);

    FileEntity save(FileSaveDTO file);

    List<FileEntity> saveList(List<FileSaveDTO> file);

    List<FileExportVO> export(List<FileEntity> file);

    List<FileSaveDTO> imports(List<FileExportVO> file);
}