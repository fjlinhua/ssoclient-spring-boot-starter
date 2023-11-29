package cloud.om00857.sso.service;

import cloud.om00857.sso.mapper.AbstractBaseMapper;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>Title: AbstractBaseService</p>
 * <p>Description: AbstractBaseService</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/03/22</p>
 *
 * @author TD00857
 * <p>Update Time: 11:17</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
public abstract class AbstractBaseService<M extends AbstractBaseMapper<T>, T> extends ServiceImpl<M, T> {

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean saveBatch(Collection<T> entityList) {
        return saveBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        List<List<T>> group = CollectionUtil.split(entityList, batchSize);
        Integer result = group.stream().map(g -> baseMapper.insertBatchSomeColumn(g)).reduce(0, (a, b) -> a + b);
        return result == entityList.size();
    }

}
