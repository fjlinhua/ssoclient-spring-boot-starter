package io.github.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <p>Title: AbstractBaseMapper</p>
 * <p>Description: AbstractBaseMapper</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/03/22</p>
 *
 * @author TD00857
 * <p>Update Time: 10:58</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
public interface AbstractBaseMapper<T> extends BaseMapper<T> {

    int insertBatchSomeColumn(@Param("list") Collection<T> batchList);

}
