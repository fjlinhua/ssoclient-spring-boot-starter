package cloud.om00857.sso.config;
/**
 * @author MybatisPlusConfiguration
 * @date 2023/11/28
 */

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <p>Title: MybatisPlusConfiguration</p>
 * <p>Description: MybatisPlusConfiguration</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/11/28</p>
 *
 * @author TD00857
 * <p>Update Time: 09:58</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
@Configuration
public class MybatisPlusConfiguration {

    @ConditionalOnMissingBean(ISqlInjector.class)
    @Bean
    public DefaultSqlInjector insertBatchSqlInjector() {
        return new DefaultSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
                List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
                methodList.add(new InsertBatchSomeColumn(i -> i.getInsertStrategy() != FieldStrategy.NEVER));
                return methodList;
            }
        };
    }

}
