package example.service.impl;

import example.mapper.SysUserMapper;
import example.pojo.SysUser;
import example.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser getById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
}
