package transaction.dao.mybatis.pay;

import org.springframework.stereotype.Repository;

import transaction.domain.pay.Pay;

@Repository
public interface PayMapper {
    int deleteByPrimaryKey(String userid);

    int insert(Pay record);

    int insertSelective(Pay record);

    Pay selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(Pay record);

    int updateByPrimaryKey(Pay record);
}