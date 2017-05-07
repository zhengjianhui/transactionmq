package transaction.dao.mybatis.balance;

import org.springframework.stereotype.Repository;

import transaction.domain.balance.Balance;

@Repository
public interface BalanceMapper {
    int deleteByPrimaryKey(String userid);

    int insert(Balance record);

    int insertSelective(Balance record);

    Balance selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(Balance record);

    int updateByPrimaryKey(Balance record);
}