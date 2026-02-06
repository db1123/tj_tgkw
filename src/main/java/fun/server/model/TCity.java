package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_city")
public class TCity implements Serializable {
    /**
     * 
     * 表字段 : t_city.f_key
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKey;

    /**
     * 
     * 表字段 : t_city.f_city
     * @mbg.generated
     */
    private String fCity;

    /**
     * 
     * 表字段 : t_city.f_province_id
     * @mbg.generated
     */
    private Long fProvinceId;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_city
     * @mbg.generated
     */
    public TCity(Long fKey, String fCity, Long fProvinceId) {
        this.fKey = fKey;
        this.fCity = fCity;
        this.fProvinceId = fProvinceId;
    }

    /**
     * 构造查询条件
     * t_city
     * @mbg.generated
     */
    public TCity() {
        super();
    }

    /**
     * 获取  字段:t_city.f_key
     * @return t_city.f_key, 
     * @mbg.generated
     */
    public Long getfKey() {
        return fKey;
    }

    /**
     * 设置  字段:t_city.f_key
     * @param fKey the value for t_city.f_key, 
     * @mbg.generated
     */
    public void setfKey(Long fKey) {
        this.fKey = fKey;
    }

    /**
     * 获取  字段:t_city.f_city
     * @return t_city.f_city, 
     * @mbg.generated
     */
    public String getfCity() {
        return fCity;
    }

    /**
     * 设置  字段:t_city.f_city
     * @param fCity the value for t_city.f_city, 
     * @mbg.generated
     */
    public void setfCity(String fCity) {
        this.fCity = fCity == null ? null : fCity.trim();
    }

    /**
     * 获取  字段:t_city.f_province_id
     * @return t_city.f_province_id, 
     * @mbg.generated
     */
    public Long getfProvinceId() {
        return fProvinceId;
    }

    /**
     * 设置  字段:t_city.f_province_id
     * @param fProvinceId the value for t_city.f_province_id, 
     * @mbg.generated
     */
    public void setfProvinceId(Long fProvinceId) {
        this.fProvinceId = fProvinceId;
    }

    /**
     * 实例输出为字符串
     * t_city
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fKey=").append(fKey);
        sb.append(", fCity=").append(fCity);
        sb.append(", fProvinceId=").append(fProvinceId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_city
     * @param that
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TCity other = (TCity) that;
        return (this.getfKey() == null ? other.getfKey() == null : this.getfKey().equals(other.getfKey()))
            && (this.getfCity() == null ? other.getfCity() == null : this.getfCity().equals(other.getfCity()))
            && (this.getfProvinceId() == null ? other.getfProvinceId() == null : this.getfProvinceId().equals(other.getfProvinceId()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_city
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKey() == null) ? 0 : getfKey().hashCode());
        result = prime * result + ((getfCity() == null) ? 0 : getfCity().hashCode());
        result = prime * result + ((getfProvinceId() == null) ? 0 : getfProvinceId().hashCode());
        return result;
    }
}