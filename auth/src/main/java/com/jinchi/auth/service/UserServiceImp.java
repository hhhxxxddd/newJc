package com.jinchi.auth.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.auth.domain.MyUser;
import com.jinchi.auth.domain.User;
import com.jinchi.auth.dto.PasswordChangeDTO;
import com.jinchi.auth.dto.UserDTO;
import com.jinchi.auth.exceptions.EnumExceptions;
import com.jinchi.auth.exceptions.JcExceptions;
import com.jinchi.auth.mapper.DepartmentMapper;
import com.jinchi.auth.mapper.UserMapper;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:23:49 2018/10/28
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 登陆
     */
    @Override
    public User login(MyUser myUser){
        // 验证用户是否存在
        String username = myUser.getUsername();
        User user = userMapper.findByFullName(username);
        if(user==null){
            throw new JcExceptions(EnumExceptions.LOGIN_FAILED_USER_NOT_EXISTS);
        }
        if(!bCryptPasswordEncoder.matches(myUser.getPassword(),user.getPassword())){
            throw  new JcExceptions((EnumExceptions.LOGIN_FAILED_ERROR));
        }
        return user;
    }

    /**
     * 修改密码
     * @param passwordChangeDTO
     * @return
     */
    @Override
    public Map<Object, Object> passwordChange(PasswordChangeDTO passwordChangeDTO) {
        String oldPassword = passwordChangeDTO.getOldPassword();//明文
        Integer userId = passwordChangeDTO.getUserId();
        UserDTO oldUserDTO = userMapper.find(userId);//密文
        Assert.isTrue(bCryptPasswordEncoder.matches(oldPassword,oldUserDTO.getPassword()),"原始密码错误");
        UserDTO newUserDTO = oldUserDTO.setPassword(bCryptPasswordEncoder.encode(passwordChangeDTO.getNewPassword()));
        update(newUserDTO);
        Map<Object,Object> map = new HashMap<>();
        map.put("账号",newUserDTO.getUsername());
        map.put("用户名",newUserDTO.getName());
        return map;
    }

    @Override
    public String reset(Integer id) {
        UserDTO oldUserDTO = userMapper.find(id);//密文
        UserDTO newUserDTO = oldUserDTO.setPassword(bCryptPasswordEncoder.encode("123456"));
        update(newUserDTO);
        return "操作成功";
    }

    /**
     * 新增
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO save(UserDTO userDTO) {
        if(userMapper.findByFullName(userDTO.getUsername())!=null){
            throw new JcExceptions(EnumExceptions.ADD_FAILED_NAME_DUPLICATED);
        }
        User user = setting(userDTO);
        //加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        userDTO.setDepartmentName(departmentMapper.byId(userDTO.getDepartmentId()).getDepartmentName());
        userDTO.setPassword(null);
        return userDTO;
    }

    /**
     * 更新
     * @param userDTO
     * @return
     */
    @Override
    public User update(UserDTO userDTO) {
        User user = setting(userDTO);
        userMapper.update(user);
        return user;
    }

    private User setting(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setId(userDTO.getId());
        user.setPassword(userDTO.getPassword());
        if (("123456").equals(user.getPassword()) && user.getId() != null){
            user.setPassword(null);
        }
        user.setDepartmentId(userDTO.getDepartmentId());
        user.setPhone(userDTO.getPhone());
        user.setName(userDTO.getName());
        user.setIdCardNumber(userDTO.getIdCardNumber());
        return user;
    }

    @Override
    public UserDTO find(Integer id) {
        UserDTO userDTO = userMapper.find(id);

        return userDTO;
    }

    @Override
    public User findByFullName(String username) {
        return userMapper.findByFullName(username);
    }

    /**
     * 查找用户及其角色
     */
    @Override
    public User findRolesByUsername(String username) {
        return userMapper.findRolesByUserName(username);
    }

    @Override
    public Set<String> findUserAuths(Integer id) {
        return userMapper.findUserAuthsString(id);
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS = userMapper.findAll();
        return userDTOS;
    }

    private void departmentSetting(List<UserDTO> userDTOS){
        for(int i=0;i<userDTOS.size();i++){
            Integer departmentId = userDTOS.get(i).getDepartmentId();
            String departmentName = departmentId==null?"":departmentMapper.byId(departmentId).getDepartmentName();
            userDTOS.get(i)
                    .setDepartmentName(departmentName)
                    .setDepartmentId(departmentId);
        }
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public PageInfo findAllByPage(Integer page, Integer size, String orderField, String orderType) {
        String order = orderField + " " + orderType;
        PageHelper.startPage(page,size,order);
        List<UserDTO> userdtos = userMapper.findAll();
        departmentSetting(userdtos);
        PageInfo<UserDTO> pageInfo = new PageInfo<UserDTO>(userdtos);
        return pageInfo;
    }

    @Override
    public PageInfo findAllByPageByName(Integer page, Integer size, String orderField, String orderType,String name) {
        String order = orderField + " " + orderType;
        PageHelper.startPage(page,size,order);
        List<UserDTO> userDTOS = userMapper.findByName(name);
        departmentSetting(userDTOS);
        PageInfo<UserDTO> pageInfo = new PageInfo<UserDTO>(userDTOS);
        return pageInfo;
    }

}
