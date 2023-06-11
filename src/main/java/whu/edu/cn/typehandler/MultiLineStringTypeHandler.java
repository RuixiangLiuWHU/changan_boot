package whu.edu.cn.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.postgis.LineString;
import org.postgis.MultiLineString;

/**
 * 线，子类
 * @author kevin
 *
 */
@MappedTypes(MultiLineString.class)
public class MultiLineStringTypeHandler extends AbstractGeometryTypeHandler<MultiLineString> {

}
