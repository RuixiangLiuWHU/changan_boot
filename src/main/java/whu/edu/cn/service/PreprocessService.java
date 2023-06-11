package whu.edu.cn.service;

import org.springframework.stereotype.Service;
import whu.edu.cn.mapper.PreprocessMapper;

import javax.annotation.Resource;

@Service
public class PreprocessService {
    @Resource
    PreprocessMapper preprocessMapper;

}
