package fun.server.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BaseService<T> {
    void save(T model); //持久化
    void updateByExample(T model, Object example); // 更新 根据查询条件
    void update(T model); // 更新
    void deleteById(Long id); // 通过主鍵刪除
    void deleteByByExample(Object example); // 批量刪除 根据条件查找
    T findById(Long id); // 通过 ID 查找
    List<T> findBy(String property, Object value) throws TooManyResultsException; //通过某个成员属性查找,value需符合unique约束
    PageInfo<T> findByExampleMapper(Object example, int offset, int limit); // 根据条件查找(配合分页插件PageHelper实现物理分页)
    List<T> findByExampleRowBounds(Object example, RowBounds rowBounds); // 根据条件查找(配合分页插件PageHelper实现物理分页)
    List<T> findByExample(Object example); // 根据条件查找
    List<T> findAll(); // 获取所有
    int count(Object example); // 按条件获取记录数量
}