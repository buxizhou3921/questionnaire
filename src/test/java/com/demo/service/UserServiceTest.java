package com.demo.service;

import com.demo.dao.UserEntityMapper;
import com.demo.dao.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserEntityMapper mockUserEntityMapper;

    @InjectMocks
    private UserService userServiceUnderTest;

    @Test
    void testSelectUserInfo() {

        final UserEntity userEntity = new UserEntity();
        userEntity.setId("id");
        userEntity.setUsername("username");
        userEntity.setPassword("password");
        userEntity.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("id");
        userEntity1.setUsername("username");
        userEntity1.setPassword("password");
        userEntity1.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity1.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<UserEntity> expectedResult = Arrays.asList(userEntity1);

        final UserEntity userEntity2 = new UserEntity();
        userEntity2.setId("id");
        userEntity2.setUsername("username");
        userEntity2.setPassword("password");
        userEntity2.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity2.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<UserEntity> userEntities = Arrays.asList(userEntity2);
        final UserEntity userEntity3 = new UserEntity();
        userEntity3.setId("id");
        userEntity3.setUsername("username");
        userEntity3.setPassword("password");
        userEntity3.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity3.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockUserEntityMapper.selectUserInfo(userEntity3)).thenReturn(userEntities);

        final List<UserEntity> result = userServiceUnderTest.selectUserInfo(userEntity);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectUserInfo_UserEntityMapperReturnsNoItems() {

        final UserEntity userEntity = new UserEntity();
        userEntity.setId("id");
        userEntity.setUsername("username");
        userEntity.setPassword("password");
        userEntity.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("id");
        userEntity1.setUsername("username");
        userEntity1.setPassword("password");
        userEntity1.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity1.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockUserEntityMapper.selectUserInfo(userEntity1)).thenReturn(Collections.emptyList());

        final List<UserEntity> result = userServiceUnderTest.selectUserInfo(userEntity);

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testQueryUserList() {

        final UserEntity userEntity = new UserEntity();
        userEntity.setId("id");
        userEntity.setUsername("username");
        userEntity.setPassword("password");
        userEntity.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("id");
        userEntity1.setUsername("username");
        userEntity1.setPassword("password");
        userEntity1.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity1.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<UserEntity> expectedResult = Arrays.asList(userEntity1);

        final UserEntity userEntity2 = new UserEntity();
        userEntity2.setId("id");
        userEntity2.setUsername("username");
        userEntity2.setPassword("password");
        userEntity2.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity2.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<UserEntity> userEntities = Arrays.asList(userEntity2);
        final UserEntity userEntity3 = new UserEntity();
        userEntity3.setId("id");
        userEntity3.setUsername("username");
        userEntity3.setPassword("password");
        userEntity3.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity3.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockUserEntityMapper.queryUserList(userEntity3)).thenReturn(userEntities);

        final List<UserEntity> result = userServiceUnderTest.queryUserList(userEntity);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testQueryUserList_UserEntityMapperReturnsNoItems() {

        final UserEntity userEntity = new UserEntity();
        userEntity.setId("id");
        userEntity.setUsername("username");
        userEntity.setPassword("password");
        userEntity.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("id");
        userEntity1.setUsername("username");
        userEntity1.setPassword("password");
        userEntity1.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity1.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockUserEntityMapper.queryUserList(userEntity1)).thenReturn(Collections.emptyList());

        final List<UserEntity> result = userServiceUnderTest.queryUserList(userEntity);

        assertThat(result).isEqualTo(Collections.emptyList());
    }

//    @Test
//    void testAddUserInfo() {
//
//        final UserEntity userEntity = new UserEntity();
//        userEntity.setId("id");
//        userEntity.setUsername("username");
//        userEntity.setPassword("password");
//        userEntity.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
//        userEntity.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
//
//        when(mockUserEntityMapper.queryUserListByName("username")).thenReturn(0);
//
//        final UserEntity userEntity1 = new UserEntity();
//        userEntity1.setId("id");
//        userEntity1.setUsername("username");
//        userEntity1.setPassword("password");
//        userEntity1.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
//        userEntity1.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
//        when(mockUserEntityMapper.insert(userEntity1)).thenReturn(0);
//
//        final int result = userServiceUnderTest.addUserInfo(userEntity);
//
//        assertThat(result).isEqualTo(0);
//    }

    @Test
    void testModifyUserInfo() {

        final UserEntity userEntity = new UserEntity();
        userEntity.setId("id");
        userEntity.setUsername("username");
        userEntity.setPassword("password");
        userEntity.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("id");
        userEntity1.setUsername("username");
        userEntity1.setPassword("password");
        userEntity1.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity1.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockUserEntityMapper.updateByPrimaryKeySelective(userEntity1)).thenReturn(0);

        final int result = userServiceUnderTest.modifyUserInfo(userEntity);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void testDeleteUserById() {

        final UserEntity userEntity = new UserEntity();
        userEntity.setId("id");
        userEntity.setUsername("username");
        userEntity.setPassword("password");
        userEntity.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("id");
        userEntity1.setUsername("username");
        userEntity1.setPassword("password");
        userEntity1.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity1.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockUserEntityMapper.deleteUserById(userEntity1)).thenReturn(0);

        final int result = userServiceUnderTest.deleteUserById(userEntity);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void testCloseUser() {

        final UserEntity userEntity = new UserEntity();
        userEntity.setId("id");
        userEntity.setUsername("username");
        userEntity.setPassword("password");
        userEntity.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("id");
        userEntity1.setUsername("username");
        userEntity1.setPassword("password");
        userEntity1.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userEntity1.setStopTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockUserEntityMapper.updateStatus(userEntity1)).thenReturn(0);

        final int result = userServiceUnderTest.closeUser(userEntity);

        assertThat(result).isEqualTo(0);
    }
}
