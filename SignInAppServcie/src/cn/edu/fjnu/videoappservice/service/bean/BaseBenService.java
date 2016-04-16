package cn.edu.fjnu.videoappservice.service.bean;

import java.util.List;

/**
 * 基本的Bean服务
 * @author GaoFei
 * @param <T>
 */
public interface BaseBenService<T> {
	//保存
    void save(T object);
    //删除
    void delete(T object);
    //更新
    void update(T object);
    //获取所有数据
    List<T> getAll();
    //通过ID获取记录
    T getObjectById(Object id);
    //判断某个对象是否存在
    boolean isExist(T object);
    //保存某个列表
    void saveAll(List<T> objects);
    //保存或更新某个列表
    void saveOrUpdateAll(List<T> objects);
}
