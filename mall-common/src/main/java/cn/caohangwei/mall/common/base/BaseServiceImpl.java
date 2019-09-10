package cn.caohangwei.mall.common.base;

import cn.caohangwei.mall.common.db.DataSourceEnum;
import cn.caohangwei.mall.common.db.DynamicDataSource;
import cn.caohangwei.mall.common.util.SpringContextUtil;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * BaseService implement.
 * @author PinuoC
 */
public abstract class BaseServiceImpl<Mapper,Record,Example> implements BaseService<Record,Example>{

    public Mapper mapper;

    @Override
    public int countByExample(Example example) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
            Method countByExample = mapper.getClass().getDeclaredMethod("countByExample",example.getClass());
            Object result = countByExample.invoke(mapper,example);
            return Integer.valueOf(String.valueOf(result));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int deleteByExample(Example example) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method method = mapper.getClass().getDeclaredMethod("deleteByExample",example.getClass());
            Object result = method.invoke(mapper,example);
            return Integer.valueOf(String.valueOf(result));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method method = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey",id.getClass());
            Object result = method.invoke(mapper,id);
            return Integer.valueOf(String.valueOf(result));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int insert(Record record) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method method = mapper.getClass().getDeclaredMethod("insert",record.getClass());
            Object result = method.invoke(mapper,record);
            return Integer.valueOf(String.valueOf(result));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int insertSelective(Record record) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method method = mapper.getClass().getDeclaredMethod("insertSelective",record.getClass());
            Object result = method.invoke(mapper,record);
            return Integer.valueOf(String.valueOf(result));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public List<Record> selectByExampleWithBLOBs(Example example) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
            Method method = mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs",example.getClass());
            Object result = method.invoke(mapper,example);
            return (List<Record>) result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public List<Record> selectByExample(Example example) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
            Method method = mapper.getClass().getDeclaredMethod("selectByExample",example.getClass());
            Object result = method.invoke(mapper,example);
            return (List<Record>) result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public Record selectByPrimaryKey(Long id) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
            Method method = mapper.getClass().getDeclaredMethod("selectByPrimaryKey",id.getClass());
            Object result = method.invoke(mapper,id);
            return (Record) result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method updateByExampleSelective = mapper.getClass().getDeclaredMethod("updateByExampleSelective", record.getClass(), example.getClass());
            Object result = updateByExampleSelective.invoke(mapper, record, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int updateByExampleWithBLOBs(@Param("record") Record record, @Param("example") Example example) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method updateByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("updateByExampleWithBLOBs", record.getClass(), example.getClass());
            Object result = updateByExampleWithBLOBs.invoke(mapper, record, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int updateByExample(@Param("record") Record record, @Param("example") Example example) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method updateByExample = mapper.getClass().getDeclaredMethod("updateByExample", record.getClass(), example.getClass());
            Object result = updateByExample.invoke(mapper, record, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Record record) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method method = mapper.getClass().getDeclaredMethod("updateByExample",record.getClass());
            Object result = method.invoke(mapper,record);
            return Integer.valueOf(String.valueOf(result));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Record record) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method method = mapper.getClass().getDeclaredMethod("updateByPrimaryKeyWithBLOBs",record.getClass());
            Object result = method.invoke(mapper,record);
            return Integer.valueOf(String.valueOf(result));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method method = mapper.getClass().getDeclaredMethod("updateByPrimaryKey",record.getClass());
            Object result = method.invoke(mapper,record);
            return Integer.valueOf(String.valueOf(result));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public void initMapper() {
        this.mapper = SpringContextUtil.getBean(getMapperClass());
    }

    public Class<Mapper> getMapperClass(){
        return (Class<Mapper>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}